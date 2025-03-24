package br.com.meetime.service;

import br.com.meetime.controller.HubSpotWebhookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HubSpotWeebHookService {
    private static final Logger logger = LoggerFactory.getLogger(HubSpotWeebHookService.class);

    public void objectCreation(String payload) {
        logger.info("Recebendo Webhook do HubSpot: {}", payload);
        logger.info("Processando evento do HubSpot: {}", payload);
    }

}
