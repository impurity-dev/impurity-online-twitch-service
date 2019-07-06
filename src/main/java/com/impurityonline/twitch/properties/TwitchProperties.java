package com.impurityonline.twitch.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

/**
 * @author tmk2003
 */
@Data
@Component
@ConfigurationProperties(prefix = "twitch")
public class TwitchProperties {
    @NotBlank
    private String clientId;
    @NotBlank
    private String clientSecret;
}
