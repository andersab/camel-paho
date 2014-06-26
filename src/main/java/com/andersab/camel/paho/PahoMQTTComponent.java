package com.andersab.camel.paho;

import java.util.Map;

import com.andersab.camel.paho.config.PahoConnectionConfiguration;
import com.andersab.camel.paho.config.PahoEndpointConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.impl.UriEndpointComponent;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.spi.HeaderFilterStrategyAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Represents the component that manages {@link PahoMQTTEndpoint}.
 */
public class PahoMQTTComponent extends UriEndpointComponent implements ApplicationContextAware, HeaderFilterStrategyAware {
    public static final String PAHO_PREFIX = "mqtt:";
    public static final String PAHO_SUB_TOPIC = "sub=";
    public static final String PAHO_PUB_TOPIC = "pub=";
    public static final String QOS_NAME = "qos";

    private PahoEndpointConfiguration messageConfiguration;
    private ApplicationContext applicationContext;

    public PahoMQTTComponent(Class<? extends Endpoint> endpointClass) {
        super(endpointClass);
    }

    public PahoMQTTComponent(CamelContext context, Class<? extends Endpoint> endpointClass) {
        super(context, endpointClass);
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        String[] remainings = remaining.split(":");
        String connectionName = remainings[0];
        String destination = remainings[1];
        String[] topicInfo = destination.split("=");
        String topicName = topicInfo[1];
        PahoEndpointConfiguration newMessageConfiguration = createConfiguration(parameters, destination, topicName);
        PahoMQTTEndpoint endpoint = new PahoMQTTEndpoint(uri, this, newMessageConfiguration);
        return endpoint;
    }

    private PahoEndpointConfiguration createConfiguration(Map<String, Object> parameters, String destination, String topicName) throws Exception {
        PahoEndpointConfiguration newMessageConfiguration = getPahoEndpointConfiguration();
        if (destination.startsWith(PAHO_SUB_TOPIC))
        {
            newMessageConfiguration = new PahoEndpointConfiguration(PahoEndpointConfiguration.TopicType.SUB, topicName);
        }
        else if (destination.startsWith(PAHO_PUB_TOPIC))
        {
            newMessageConfiguration = new PahoEndpointConfiguration(PahoEndpointConfiguration.TopicType.PUB, topicName);
        }
        else {
            throw new RuntimeCamelException("Paho Endpoint not defined correctly for pub/sub");
        }
        PahoConnectionConfiguration connectionConfiguration = getConnectionConfiguration();
        setProperties(newMessageConfiguration, parameters);
        return newMessageConfiguration;
    }

    private PahoConnectionConfiguration getConnectionConfiguration() {



        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public HeaderFilterStrategy getHeaderFilterStrategy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setHeaderFilterStrategy(HeaderFilterStrategy headerFilterStrategy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        
    }

    public PahoEndpointConfiguration getPahoEndpointConfiguration() {
        return this.messageConfiguration;
    }
}
