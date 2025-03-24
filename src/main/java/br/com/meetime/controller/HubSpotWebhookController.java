package br.com.meetime.controller;

import br.com.meetime.exception.WebhookProcessingException;
import br.com.meetime.service.HubSpotWeebHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class HubSpotWebhookController {

    private final HubSpotWeebHookService webhookService;

    public HubSpotWebhookController(HubSpotWeebHookService webhookService) {
        this.webhookService = webhookService;
    }
    @PostMapping("/object-creation")
    public ResponseEntity<String> handleHubSpotWebhook(@RequestBody String payload) {

        try {
            webhookService.objectCreation(payload);
        } catch (Exception e) {
            throw new WebhookProcessingException("Erro ao processar webhook: " + e.getMessage());
        }

        return ResponseEntity.ok("Webhook recebido com sucesso!");
    }


}
