package com.andersab.camel.paho.config;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import javax.net.SocketFactory;
import java.io.Serializable;
import java.util.Properties;

public class PahoConnectionConfiguration implements Serializable {

    private static final long serialVersionUID = -9191338928051980051L;
    private String connectionName;
    private String brokerUri;
    private String clientId;
    private MqttClientPersistence persistence;
    private MqttConnectOptions connectOptions;

    public PahoConnectionConfiguration() {
        this.connectOptions = new MqttConnectOptions();
    }

    public String getPassword() {
        return String.valueOf(this.connectOptions.getPassword());
    }

    public void setPassword(String password) {
        this.connectOptions.setPassword(password.toCharArray());
    }

    public String getUserName() {
        return this.connectOptions.getUserName();
    }

    public void setUserName(String userName) {
        this.connectOptions.setUserName(userName);
    }

    public void setWill(MqttTopic topic, byte[] payload, int qos, boolean retained) {
        this.connectOptions.setWill(topic, payload, qos, retained);
    }

    public void setWill(String topic, byte[] payload, int qos, boolean retained) {
        this.connectOptions.setWill(topic, payload, qos, retained);
    }

    public int getKeepAliveInterval() {
        return this.connectOptions.getKeepAliveInterval();
    }

    public void setKeepAliveInterval(int keepAliveInterval) throws IllegalArgumentException {
        this.connectOptions.setKeepAliveInterval(keepAliveInterval);
    }

    public int getConnectionTimeout() {
        return this.connectOptions.getConnectionTimeout();
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectOptions.setConnectionTimeout(connectionTimeout);
    }

    public SocketFactory getSocketFactory() {
        return this.connectOptions.getSocketFactory();
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.connectOptions.setSocketFactory(socketFactory);
    }

    public String getWillDestination() {
        return this.connectOptions.getWillDestination();
    }

    public MqttMessage getWillMessage() {
        return this.connectOptions.getWillMessage();
    }

    public Properties getSSLProperties() {
        return this.connectOptions.getSSLProperties();
    }

    public void setSSLProperties(Properties props) {
        this.connectOptions.setSSLProperties(props);
    }

    public boolean isCleanSession() {
        return this.connectOptions.isCleanSession();
    }

    public void setCleanSession(boolean cleanSession) {
        this.connectOptions.setCleanSession(cleanSession);
    }

    public Properties getDebug() {
        return this.connectOptions.getDebug();
    }
}
