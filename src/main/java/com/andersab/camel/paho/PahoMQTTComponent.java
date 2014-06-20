package com.andersab.camel.paho;

import java.util.Map;

import com.andersab.camel.paho.config.PahoMessageConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.spi.HeaderFilterStrategyAware;

/**
 * Represents the component that manages {@link PahoMQTTEndpoint}.
 */
public class PahoMQTTComponent extends UriEndpointComponent implements HeaderFilterStrategyAware {
    public static final String PAHO_PREFIX = "mqtt:";
    private PahoMessageConfiguration messageConfiguration;

    public PahoMQTTComponent(Class<? extends Endpoint> endpointClass) {
        super(endpointClass);
    }

    public PahoMQTTComponent(CamelContext context, Class<? extends Endpoint> endpointClass) {
        super(context, endpointClass);
    }


    public PahoMessageConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }

    public void setMessageConfiguration(PahoMessageConfiguration messageConfiguration) {
        this.messageConfiguration = messageConfiguration;
    }

    protected PahoMessageConfiguration createMessageConfiguration() {
        return new PahoMessageConfiguration();
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        PahoMessageConfiguration newMessageConfiguration = getMessageConfiguration();
        String[] remainings = remaining.split(":");

        Endpoint endpoint = new PahoMQTTEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }

    @Override
    public HeaderFilterStrategy getHeaderFilterStrategy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setHeaderFilterStrategy(HeaderFilterStrategy headerFilterStrategy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
