package com.impurityonline.twitch.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.impurityonline.twitch.client.response.TwitchApiFollowerResponse;
import com.impurityonline.twitch.client.response.TwitchApiStreamResponse;
import com.impurityonline.twitch.client.response.TwitchApiUserResponse;
import com.impurityonline.twitch.exception.RestTemplateServerException;
import com.impurityonline.twitch.exception.TwitchClientFollowersHttpRequestException;
import com.impurityonline.twitch.exception.TwitchClientStreamHttpRequestException;
import com.impurityonline.twitch.exception.TwitchClientUserHttpRequestException;
import com.impurityonline.twitch.properties.TwitchProperties;
import com.impurityonline.twitch.utils.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static com.impurityonline.twitch.builder.TwitchUrlBuilder.*;
import static com.impurityonline.twitch.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchClientTests extends AbstractTest {

    @Autowired
    private TwitchProperties twitchProperties;
    @Autowired
    private TwitchClient twitchClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setup() {
        mockServer = MockRestServiceServer.createServer(twitchClient.getRestTemplate());
    }

    @Test
    @DisplayName("When the twitch client follower has null user id, throw null pointer")
    public void twitchClient_followers_null_userId() {
        assertThrows(NullPointerException.class, () -> twitchClient.getFollowers(null));
    }

    @Test
    @DisplayName("When the twitch client stream has null channel, throw null pointer")
    public void twitchClient_stream_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchClient.getStream(null));
    }

    @Test
    @DisplayName("When the twitch client user has null channel, throw null pointer")
    public void twitchClient_user_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchClient.getUser(null));
    }

    @Test
    @DisplayName("When the twitch client gets user, return response")
    public void twitchClient_with_OK_twitchApiUserResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        mockServer.expect(once(), requestTo(buildUserURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiUserResponse))
                );
        assertEquals(twitchApiUserResponse, twitchClient.getUser(channel).getBody());
    }

    @Test
    @DisplayName("When the twitch client user has client error, throw TwitchClientUserHttpRequestException")
    public void twitchClient_with_CLIENTERROR_twitchApiUserResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        mockServer.expect(once(), requestTo(buildUserURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiUserResponse))
                );
        assertThrows(TwitchClientUserHttpRequestException.class, () -> twitchClient.getUser(channel));
    }

    @Test
    @DisplayName("When the twitch client user has server error, throw RestTemplateServerException")
    public void twitchClient_with_SERVERERROR_twitchApiUserResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        mockServer.expect(once(), requestTo(buildUserURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiUserResponse))
                );
        assertThrows(RestTemplateServerException.class, () -> twitchClient.getUser(channel));
    }

    @Test
    @DisplayName("When the twitch client gets stream, return response")
    public void twitchClient_with_OK_twitchApiStreamResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        mockServer.expect(once(), requestTo(buildStreamURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiStreamResponse))
                );
        assertEquals(twitchApiStreamResponse, twitchClient.getStream(channel).getBody());
    }

    @Test
    @DisplayName("When the twitch client stream has client error, throw TwitchClientStreamHttpRequestException")
    public void twitchClient_with_CLIENTERROR_twitchApiStreamResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        mockServer.expect(once(), requestTo(buildStreamURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiStreamResponse))
                );
        assertThrows(TwitchClientStreamHttpRequestException.class, () -> twitchClient.getStream(channel));
    }

    @Test
    @DisplayName("When the twitch client stream has server error, throw RestTemplateServerException")
    public void twitchClient_with_SERVERERROR_twitchApiStreamResponse() throws JsonProcessingException {
        String channel = "123";
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        mockServer.expect(once(), requestTo(buildStreamURL(channel).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiStreamResponse))
                );
        assertThrows(RestTemplateServerException.class, () -> twitchClient.getStream(channel));
    }

    @Test
    @DisplayName("When the twitch client gets followers, return response")
    public void twitchClient_with_OK_twitchApiFollowerResponse() throws JsonProcessingException {
        String userId = "123";
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        mockServer.expect(once(), requestTo(buildFollowersURL(userId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiFollowerResponse))
                );
        assertEquals(twitchApiFollowerResponse, twitchClient.getFollowers(userId).getBody());
    }

    @Test
    @DisplayName("When the twitch client followers has client error, throw TwitchClientFollowersHttpRequestException")
    public void twitchClient_with_CLIENTERROR_twitchApiFollowerResponse() throws JsonProcessingException {
        String userId = "123";
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        mockServer.expect(once(), requestTo(buildFollowersURL(userId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiFollowerResponse))
                );
        assertThrows(TwitchClientFollowersHttpRequestException.class, () -> twitchClient.getFollowers(userId));
    }

    @Test
    @DisplayName("When the twitch client followers has server error, throw RestTemplateServerException")
    public void twitchClient_with_SERVERERROR_twitchApiFollowerResponse() throws JsonProcessingException {
        String userId = "123";
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        mockServer.expect(once(), requestTo(buildFollowersURL(userId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(twitchApiFollowerResponse))
                );
        assertThrows(RestTemplateServerException.class, () -> twitchClient.getFollowers(userId));
    }
}
