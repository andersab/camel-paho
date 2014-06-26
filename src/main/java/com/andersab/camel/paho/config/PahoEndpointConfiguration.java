package com.andersab.camel.paho.config;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class PahoEndpointConfiguration implements Cloneable {
    private PahoConnectionConfiguration connectionConfiguration;
    private String topicName;
    private TopicType topicType;
    private boolean mutable;
    private int qos;
    private boolean retained;
    private boolean dup;

    public PahoEndpointConfiguration copy() {
        try {
            return (PahoEndpointConfiguration) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
    }

    public PahoEndpointConfiguration(TopicType topicType, String topicName) {
        this.topicType = topicType;
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicType getTopicType() {
        return topicType;
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public boolean isRetained() {
        return retained;
    }

    public void setRetained(boolean retained) {
        this.retained = retained;
    }

    public boolean isDup() {
        return dup;
    }

    public void setDup(boolean dup) {
        this.dup = dup;
    }

    public PahoConnectionConfiguration getConnectionConfiguration() {
        return connectionConfiguration;
    }

    public void setConnectionConfiguration(PahoConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }

    public enum TopicType {
        PUB,
        SUB;
    }
}
