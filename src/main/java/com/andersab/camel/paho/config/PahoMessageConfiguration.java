package com.andersab.camel.paho.config;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParams;

@UriParams
public class PahoMessageConfiguration implements Cloneable{
    private boolean mutable;
    private int qos;
    private boolean retained;
    private boolean dup;

    public PahoMessageConfiguration copy() {
        try {
            return (PahoMessageConfiguration) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
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
}
