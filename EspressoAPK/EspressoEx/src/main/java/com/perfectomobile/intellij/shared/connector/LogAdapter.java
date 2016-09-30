package com.perfectomobile.intellij.shared.connector;

public interface LogAdapter {
    void error(String message);

    void error(String message, Throwable cause);

    void warn(String message);

    void warn(String message, Throwable cause);

    void debug(String message);

    void debug(String message, Throwable cause);

    void info(String message);

    void info(String message, Throwable cause);
}
