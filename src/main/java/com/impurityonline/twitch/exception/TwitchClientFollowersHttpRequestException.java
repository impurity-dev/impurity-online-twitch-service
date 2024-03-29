package com.impurityonline.twitch.exception;

import org.springframework.http.HttpStatus;

/**
 * @author impurity
 */
public class TwitchClientFollowersHttpRequestException extends HttpRequestException {
    private static final long serialVersionUID = 8013263773819717060L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     * @param status Http status
     * @param cause Causing exception
     */
    public TwitchClientFollowersHttpRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, status, cause);
    }
}
