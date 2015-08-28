package com.equivi.channel.exception;

/**
 * Created by eldridaditya on 28/8/15.
 */
public class InvalidConfigException extends RuntimeException {
    public InvalidConfigException(String errorMessage) {
        super(errorMessage);
    }
}
