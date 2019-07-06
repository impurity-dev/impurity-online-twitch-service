package com.impurityonline.twitch.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchUser;
import lombok.Data;

/**
 * @author impurity
 */
@Data
public class TwitchUserResponse {
    @JsonProperty("user")
    private TwitchUser user;
}
