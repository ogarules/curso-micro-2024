package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JWTResourceServerTests {
    
    private static final String redirectUrl = "http://localhost:8080";
    private static final String authorizeUrlPattern = "http://localhost:8089/realms/baeldung/protocol/openid-connect/auth?response_type=code&client_id=fooClient&scope=%s&redirect_uri="+redirectUrl;
    private static final String tokenUrl = "http://localhost:8089/realms/baeldung/protocol/openid-connect/token";
    private static final String resourceUrl = "http://localhost:8082/foos";
    
    @Test
    public void givenUserWithScope_whenGetFooResource_thenSuccess(){
        String accessToken = obtainAccesstoken("read");

        Response response = RestAssured.given()
                              .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                              .get(resourceUrl);

                              assertThat(response.as(List.class)).hasSizeGreaterThan(0);
    }

    private String obtainAccesstoken(String scopes) {
        Response response = RestAssured.given()
                              .redirects()
                              .follow(false)
                              .get(String.format(authorizeUrlPattern, scopes));

        String authSessionId = response.getCookie("AUTH_SESSION_ID");
        String kcPostAuthenticationUrl = response.asString()
                             .split("action=\"")[1].split("\"")[0].replace("&amp;", "&");

        response = RestAssured.given()
                          .redirects()
                          .follow(false)
                          .cookie("AUTH_SESSION_ID", authSessionId)
                          .formParams("username", "john@test.com", "password", "123", "credentialId", "")
                          .post(kcPostAuthenticationUrl);

        String location = response.getHeader(HttpHeaders.LOCATION);
        String code = location.split("code=")[1].split("&")[0];

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("client_id", "fooClient");
        params.put("redirect_uri", redirectUrl);
        params.put("client_secret", "IBrX4WrfCTEn4ci0DVXSBhFbASADSdUQ");
        response = RestAssured.given()
                     .formParams(params)
                     .post(tokenUrl);

        String responseToken = response.asString();

        return response.jsonPath().getString("access_token");
    }
}
