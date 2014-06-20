package com.andersab.camel.paho;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.Service;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.spi.HeaderFilterStrategyAware;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;

/**
 * Represents a PahoMQTT endpoint.
 */
@ManagedResource(description = "Managed Paho Client")
@UriEndpoint(scheme = "mqtt", consumerClass = PahoMQTTConsumer.class)
public class PahoMQTTEndpoint extends DefaultEndpoint implements HeaderFilterStrategyAware, Service {

    @UriPath
    private String connectionName;


    public PahoMQTTEndpoint() {
    }

    public PahoMQTTEndpoint(String uri, PahoMQTTComponent component) {
        super(uri, component);
    }

    public PahoMQTTEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new PahoMQTTProducer(this);
    }

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
    public void setHeaderFilterStrategy(HeaderFilterStrategy headerFilterStrategy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
