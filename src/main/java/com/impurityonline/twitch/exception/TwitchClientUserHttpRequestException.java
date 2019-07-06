package com.impurityonline.twitch.exception;

import org.springframework.http.HttpStatus;

/**
 * @author impurity
 */
public class TwitchClientUserHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = -2434054520504668053L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause Causing exception
     */
    public TwitchClientUserHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
