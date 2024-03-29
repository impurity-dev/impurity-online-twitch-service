package com.impurityonline.twitch.exception;

/**
 * @author impurity
 */
public class TwitchUserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 851189875199457444L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchUserNotFoundException(String message) {
        super(message);
    }
}
