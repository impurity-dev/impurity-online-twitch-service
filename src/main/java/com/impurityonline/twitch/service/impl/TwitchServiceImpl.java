package com.impurityonline.twitch.service.impl;

import com.impurityonline.twitch.client.TwitchClient;
import com.impurityonline.twitch.client.response.TwitchApiFollowerResponse;
import com.impurityonline.twitch.client.response.TwitchApiStreamResponse;
import com.impurityonline.twitch.client.response.TwitchApiUserResponse;
import com.impurityonline.twitch.domain.TwitchFollower;
import com.impurityonline.twitch.domain.TwitchStream;
import com.impurityonline.twitch.domain.TwitchUser;
import com.impurityonline.twitch.exception.TwitchFollowersNotFoundException;
import com.impurityonline.twitch.exception.TwitchStreamNotFoundException;
import com.impurityonline.twitch.exception.TwitchUserNotFoundException;
import com.impurityonline.twitch.service.TwitchService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author impurity
 */
@Slf4j
@Service
public class TwitchServiceImpl implements TwitchService {

    @Autowired
    private TwitchClient twitchClient;

    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchUser getUser(@NonNull String channel) {
        ResponseEntity<TwitchApiUserResponse> responseEntity = twitchClient.getUser(channel);

        TwitchApiUserResponse twitchApiUserResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> {
                    log.error("There was no response entity found while getting twitch user");
                    return new TwitchUserNotFoundException("No user response body found");
                });

        TwitchUser[] twitchUsers = Optional
                .ofNullable(twitchApiUserResponse.getUsers())
                .orElse(new TwitchUser[0]);

        if (twitchUsers.length == 0) {
            log.error("There were no users found while getting twitch user");
            throw new TwitchUserNotFoundException("No users found");
        }

        return Optional
                .ofNullable(twitchUsers[0])
                .orElseThrow(() -> {
                    log.error("The first user found was not instantiated");
                    return new TwitchUserNotFoundException("The first user found was not instantiated");
                });
    }


    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchStream getStream(@NonNull String channel) {
        ResponseEntity<TwitchApiStreamResponse> responseEntity = twitchClient.getStream(channel);

        TwitchApiStreamResponse twitchApiStreamResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> {
                    log.error("There was no response entity found while getting twitch stream");
                    return new TwitchStreamNotFoundException("No stream response body found");
                });

        TwitchStream[] twitchStreams = Optional
                .ofNullable(twitchApiStreamResponse.getStreams())
                .orElse(new TwitchStream[0]);

        if (twitchStreams.length == 0) {
            log.error("There were no streams found while getting twitch user");
            throw new TwitchStreamNotFoundException("No streams found");
        }

        return Optional
                .ofNullable(twitchStreams[0])
                .orElseThrow(() -> {
                    log.error("The first stream found was not instantiated");
                    return new TwitchStreamNotFoundException("The first stream found was not instantiated");
                });
    }

    /**
     * Get the users followers
     *
     * @param channel - Channel to grab the followers for
     * @return - The API response from twitch
     */
    private TwitchApiFollowerResponse getFollowers(@NonNull String channel) {
        String userId = this.getUser(channel).getId();
        ResponseEntity<TwitchApiFollowerResponse> responseEntity = twitchClient.getFollowers(userId);

        return Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new TwitchFollowersNotFoundException("No recent followers response body found"));
    }

    /**
     * Get the users total followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public Long getTotalFollowers(String channel) {
        return Optional
                .ofNullable(getFollowers(channel).getTotal())
                .orElse(0L);
    }

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public TwitchFollower[] getRecentFollowers(@NonNull String channel) {
        return Optional
                .ofNullable(getFollowers(channel).getFollowers())
                .orElse(new TwitchFollower[0]);
    }
}
