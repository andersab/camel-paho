package com.andersab.camel.paho;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link PahoMQTTEndpoint}.
 */
public class PahoMQTTComponent extends DefaultComponent {

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new PahoMQTTEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
