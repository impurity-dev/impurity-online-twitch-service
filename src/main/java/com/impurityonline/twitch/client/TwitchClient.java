package com.impurityonline.twitch.client;

import com.impurityonline.twitch.client.response.TwitchApiFollowerResponse;
import com.impurityonline.twitch.client.response.TwitchApiStreamResponse;
import com.impurityonline.twitch.client.response.TwitchApiUserResponse;
import com.impurityonline.twitch.exception.RestTemplateClientException;
import com.impurityonline.twitch.exception.TwitchClientFollowersHttpRequestException;
import com.impurityonline.twitch.exception.TwitchClientStreamHttpRequestException;
import com.impurityonline.twitch.exception.TwitchClientUserHttpRequestException;
import com.impurityonline.twitch.properties.TwitchProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.impurityonline.twitch.builder.TwitchUrlBuilder.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author impurity
 */
@Slf4j
@Component
public class TwitchClient extends RestTemplateClient {

    @Autowired
    private TwitchProperties twitchProperties;

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", APPLICATION_JSON_VALUE);
        headers.set("Client-ID", twitchProperties.getClientId());
        return headers;
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiUserResponse> getUser(@NonNull final String channel) {
        try {
            return getRequest(
                    buildUserURL(channel).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity<>(this.getHeaders()),
                    TwitchApiUserResponse.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Twitch client issue retrieving user: {}", ex.getMessage());
            throw new TwitchClientUserHttpRequestException("Cannot get user", ex.getStatus(), ex);
        }
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Stream
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiStreamResponse> getStream(@NonNull final String channel) {
        try {
            return getRequest(
                    buildStreamURL(channel).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity<>(this.getHeaders()),
                    TwitchApiStreamResponse.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Twitch client issue retrieving stream: {}", ex.getMessage());
            throw new TwitchClientStreamHttpRequestException("Cannot get stream", ex.getStatus(), ex);
        }
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Users Followers
     *
     * @param twitchUserId - The Twitch User id to perform the look up on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiFollowerResponse> getFollowers(@NonNull final String twitchUserId) {
        try {
            return getRequest(
                    buildFollowersURL(twitchUserId).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity<>(this.getHeaders()),
                    TwitchApiFollowerResponse.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Twitch client issue retrieving followers: {}", ex.getMessage());
            throw new TwitchClientFollowersHttpRequestException("Cannot get followers", ex.getStatus(), ex);
        }
    }
}
