package com.perfectomobile.intellij.shared.connector;

public class ConnectorConfiguration {
    private String host;
    private String executionId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public String toString() {
        return "ConnectorConfiguration{" +
                "host='" + host + '\'' +
                ", executionId='" + executionId + '\'' +
                '}';
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectorConfiguration that = (ConnectorConfiguration) o;

        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        return executionId != null ? executionId.equals(that.executionId) : that.executionId == null;

    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (executionId != null ? executionId.hashCode() : 0);
        return result;
    }
}
