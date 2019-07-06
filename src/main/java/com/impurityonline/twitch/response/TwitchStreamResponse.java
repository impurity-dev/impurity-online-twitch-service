package com.impurityonline.twitch.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchStream;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchStreamResponse {
    @JsonProperty("stream")
    private TwitchStream stream;
}
