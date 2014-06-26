package com.andersab.camel.paho;

import com.andersab.camel.paho.config.PahoConnectionConfiguration;
import com.andersab.camel.paho.config.PahoEndpointConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class PahoMQTTComponentTest extends CamelTestSupport {

    private static final String SUB_TEST_URI = "mqtt:connectionName:sub=camel/paho/sub?qos=2";
    private static final String PUB_TEST_URI = "mqtt:connectionName:pub=camel/paho/pub?qos=2&retained=true";
    private String componentName = "paho";
    private PahoMQTTEndpoint endpoint;

    @Test
    public void testCreateSubscriptionEndpointConfiguration() throws Exception {
        CamelContext context = super.createCamelContext();
        PahoMQTTComponent component = new PahoMQTTComponent(context, PahoMQTTEndpoint.class);
        Endpoint resultEndpoint = component.createEndpoint(SUB_TEST_URI);
        assertNotNull(resultEndpoint);
        assertTrue(resultEndpoint instanceof PahoMQTTEndpoint );
        PahoMQTTEndpoint pahoEndpoint = (PahoMQTTEndpoint) resultEndpoint;
        assertEquals(2, pahoEndpoint.getConfiguration().getQos());
        assertEquals("camel/paho/sub", pahoEndpoint.getConfiguration().getTopicName());
        assertEquals(PahoEndpointConfiguration.TopicType.SUB, pahoEndpoint.getConfiguration().getTopicType());
    }

    @Test
    public void testCreatePublicationEndpointConfiguration() throws Exception {
        CamelContext context = super.createCamelContext();
        PahoMQTTComponent component = new PahoMQTTComponent(context, PahoMQTTEndpoint.class);
        Endpoint resultEndpoint = component.createEndpoint(PUB_TEST_URI);
        assertNotNull(resultEndpoint);
        assertTrue(resultEndpoint instanceof PahoMQTTEndpoint );
        PahoMQTTEndpoint pahoEndpoint = (PahoMQTTEndpoint) resultEndpoint;
        assertEquals(2, pahoEndpoint.getConfiguration().getQos());
        assertEquals("camel/paho/pub", pahoEndpoint.getConfiguration().getTopicName());
        assertTrue(pahoEndpoint.getConfiguration().isRetained());
        assertEquals(PahoEndpointConfiguration.TopicType.PUB, pahoEndpoint.getConfiguration().getTopicType());
    }

   /*@Test
    public void testPahoMQTT() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }*/

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        PahoMQTTComponent component = new PahoMQTTComponent(camelContext, PahoMQTTEndpoint.class);
        camelContext.addComponent(componentName, component);

        this.endpoint = (PahoMQTTEndpoint) component.createEndpoint(SUB_TEST_URI);
        return camelContext;
    }

    private PahoConnectionConfiguration createConnectionConfiguration() {
        PahoConnectionConfiguration connectionConfig = new PahoConnectionConfiguration();
        connectionConfig.setConnectionName("connectionName");
        connectionConfig.setBrokerUri("tcp://localhost:1883");
        connectionConfig.setClientId("test");
        connectionConfig.setWill("will/topic", "ungraceful disconnect".getBytes(), 1, true);
        MemoryPersistence persistence = new MemoryPersistence();
        connectionConfig.setPersistence(persistence);
        return connectionConfig;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("mqtt://foo")
                  .to("mqtt://bar")
                  .to("mock:result");
            }
        };
    }
}
