package com.andersab.camel.paho;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PahoMQTT producer.
 */
public class PahoMQTTProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(PahoMQTTProducer.class);
    private PahoMQTTEndpoint endpoint;

    public PahoMQTTProducer(PahoMQTTEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
