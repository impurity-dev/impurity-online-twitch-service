package com.impurityonline.twitch.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchUser;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchUserResponse {
    @JsonProperty("user")
    private TwitchUser user;
}
