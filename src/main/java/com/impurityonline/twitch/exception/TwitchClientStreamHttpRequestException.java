package com.impurityonline.twitch.exception;

import org.springframework.http.HttpStatus;

/**
 * @author impurity
 */
public class TwitchClientStreamHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = -7639579083797350975L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param status http status
     * @param cause Causing exception
     */
    public TwitchClientStreamHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
