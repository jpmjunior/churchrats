package br.com.pinheiroapps.churchrats.service;

import br.com.pinheiroapps.churchrats.domain.dto.MessageDto;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class MessengerService {

    @Value("${whatsapp.graph.api.url}")
    private String graphApiUrl;

    @Value("${whatsapp.graph.api.business.phone.id}")
    private String graphApiBusinessPhoneId;

    @Value("${whatsapp.graph.api.token}")
    private String graphApiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public void send(MessageDto message) {
        String url = graphApiUrl + graphApiBusinessPhoneId + "/messages";

        Map<String, Object> messageData = Map.of(
                "messaging_product", "whatsapp",
                "recipient_type", "individual",
                "to", message.getNumberTo(),
                "type", "text",
                "text", Map.of(
                        "preview_url", false,
                        "body", message.getMessage()
                )
        );

        log.info("Enviando mensagem para o Whatsapp Business API: \n{}", new GsonBuilder().setPrettyPrinting().create().toJson(messageData));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(graphApiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.postForEntity(url, new HttpEntity<>(messageData, headers), String.class);
    }

}
