package com.andersab.camel.paho;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a PahoMQTT endpoint.
 */
public class PahoMQTTEndpoint extends DefaultEndpoint {

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
}
