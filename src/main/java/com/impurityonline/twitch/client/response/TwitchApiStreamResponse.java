package com.impurityonline.twitch.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurityonline.twitch.domain.TwitchPagination;
import com.impurityonline.twitch.domain.TwitchStream;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiStreamResponse {
    @JsonProperty("data")
    private TwitchStream[] streams;
    @JsonProperty("pagination")
    private TwitchPagination pagination;
}