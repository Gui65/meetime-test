package br.com.meetime.service;

import br.com.meetime.controller.request.ContactRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContactService {

    @Value("${hubspot.api-url}")
    private String apiUrl;

    private final AuthService authService;

    public ContactService(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<String> createContact(ContactRequest contact) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        String jsonBody = "{\n" +
                "  \"properties\": {\n" +
                "    \"email\": \"" + contact.getEmail() + "\",\n" +
                "    \"firstname\": \"" + contact.getFirstName() + "\",\n" +
                "    \"lastname\": \"" + contact.getLastName() + "\"\n" +
                "  }\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, request, String.class);

        return response;
    }
}
