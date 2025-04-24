package br.com.pinheiroapps.pibsheeps.controller;

import br.com.pinheiroapps.pibsheeps.model.webhook.WebhookEvent;
import br.com.pinheiroapps.pibsheeps.service.EventProcessor;
import br.com.pinheiroapps.pibsheeps.util.SecurityUtils;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    @Value("${whatsapp.webhook.verify.token}")
    private String verifyToken;

    @Value("${m4d.app.secret}")
    private String appSecret;

    @NonNull
    private EventProcessor eventProcessor;

    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.verify_token") String token,
            @RequestParam(name = "hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            log.info("Webhook verificado com sucesso. Challenge: {}", challenge);
            return ResponseEntity.ok(challenge);
        } else {
            log.info("Verificação do Webhook falhou.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestHeader("x-hub-signature-256") String signature,
            @RequestBody String body
    ) {

        log.debug("Headers: {}", new Gson().toJson(headers));
        log.info("Body recebido: {}", body);

        try {

            String xHubSignature = signature.replace("sha256=", "");
            String generatedSignature = SecurityUtils.generateXHub256Sig(body, appSecret);

            if (xHubSignature.equalsIgnoreCase(generatedSignature)) {

                log.info("Cabeçalho x-hub-signature-256 validado");

                WebhookEvent event = new Gson().fromJson(body, WebhookEvent.class);
                eventProcessor.process(event);

                return ResponseEntity.ok("Valid signature");

            } else {
                log.warn("Cabeçalho x-hub-signature-256 inválido");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
            }

        } catch (Exception e) {
            log.error("Erro ao processar Webhook", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing webhook");
        }
    }
}
