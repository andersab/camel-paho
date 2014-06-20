package com.andersab.camel.paho;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.junit.Test;

public class PahoMQTTComponentTest extends CamelTestSupport {

    private static final String SUB_TEST_URI = "mqtt:connectionName:subscribeTopic=camel/paho/sub";
    private static final String PUB_TEST_URI = "mqtt:connectionName:subscribeTopic=camel/paho/pub";
    private String componentName = "paho";
    private PahoMQTTEndpoint endpoint;

    @Test
    public void testPahoMQTT() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        PahoMQTTComponent component = new PahoMQTTComponent(camelContext, PahoMQTTEndpoint.class);
        camelContext.addComponent(componentName, component);




        this.endpoint = (PahoMQTTEndpoint) component.createEndpoint(SUB_TEST_URI);
        return camelContext;
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
