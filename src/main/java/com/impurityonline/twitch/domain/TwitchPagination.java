package com.impurityonline.twitch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author impurity
 */
@Data
public class TwitchPagination {
    @JsonProperty("cursor")
    private String cursor;
}
