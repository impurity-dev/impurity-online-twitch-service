package com.impurityonline.twitch.exception;

/**
 * @author impurity
 */
public class TwitchFollowersNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -9165104650581074562L;

    /**
     * Generic Twitch Exception
     *
     * @param message Explanatory message
     */
    public TwitchFollowersNotFoundException(String message) {
        super(message);
    }
}
