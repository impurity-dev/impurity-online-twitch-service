package com.impurityonline.twitch.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchFollower;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchFollowersResponse {
    @JsonProperty("followers")
    private TwitchFollower[] followers;
}
