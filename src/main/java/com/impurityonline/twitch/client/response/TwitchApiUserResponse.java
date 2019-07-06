package com.impurityonline.twitch.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchUser;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiUserResponse {
    @JsonProperty("data")
    private TwitchUser[] users;
}