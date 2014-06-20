package com.andersab.camel.paho;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class PahoMQTTComponentTest extends CamelTestSupport {

    @Test
    public void testPahoMQTT() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
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