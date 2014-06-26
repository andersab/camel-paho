package com.andersab.camel.paho;

import com.andersab.camel.paho.config.PahoEndpointConfiguration;
import org.apache.camel.*;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.*;

/**
 * Represents a PahoMQTT endpoint.
 */
@ManagedResource(description = "Managed Paho Client")
@UriEndpoint(scheme = "mqtt", consumerClass = PahoMQTTConsumer.class)
public class PahoMQTTEndpoint extends DefaultEndpoint implements HeaderFilterStrategyAware, Service, MultipleConsumersSupport {

    private String connectionName;

    @UriParam
    private PahoEndpointConfiguration configuration;

    public PahoMQTTEndpoint(String endpointUri, Component component, PahoEndpointConfiguration configuration) {
        super(endpointUri, component);
        this.configuration = configuration;
    }

    public PahoMQTTEndpoint(PahoEndpointConfiguration configuration) {
        this.configuration = configuration;
    }

    public Producer createProducer() throws Exception {
        return new PahoMQTTProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new PahoMQTTConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    @Override
    public HeaderFilterStrategy getHeaderFilterStrategy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setHeaderFilterStrategy(HeaderFilterStrategy strategy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMultipleConsumersSupported() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PahoEndpointConfiguration getConfiguration() {
        return configuration;
    }
}
