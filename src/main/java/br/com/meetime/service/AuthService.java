package br.com.meetime.service;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    @Value("${hubspot.auth-uri}")
    private String authUri;

    @Value("${hubspot.token-uri}")
    private String tokenUri;

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;
    //private static final String SCOPES = "&scope=crm.objects.contacts.write%20crm.objects.contacts.read%20crm.schemas.contacts.write";

    private String accessToken;

    private static final String SCOPES = "crm.objects.contacts.write crm.schemas.contacts.write oauth crm.objects.contacts.read";

    public String getAuthorizationUrl() {
        return authUri + "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=" + SCOPES +
                "&response_type=code";
    }


    public void exchangeAuthorizationCode(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.accessToken = response.getBody();
        } else {
            throw new RuntimeException("Erro ao trocar código pelo token!");
        }
    }

    public String getAccessToken() {
        String responseJson = accessToken; // Resposta JSON da API
        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
        return jsonObject.get("access_token").getAsString();
    }

}
