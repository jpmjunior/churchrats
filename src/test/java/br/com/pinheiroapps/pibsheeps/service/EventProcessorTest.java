package br.com.pinheiroapps.pibsheeps.service;

import br.com.pinheiroapps.pibsheeps.model.webhook.WebhookEvent;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EventProcessorTest {

    @MockitoBean
    private MessengerService messengerService;

    @Autowired
    private EventProcessor eventProcessor;

    @Test
    @DisplayName("Deve chamar o método send ao enviar o campo body")
    void process_cenario1() {

        String json = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"1337254764011514\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15556429991\",\"phone_number_id\":\"550315404841934\"},\"contacts\":[{\"profile\":{\"name\":\"Pinheiro Jr.\"},\"wa_id\":\"559891809059\"}],\"messages\":[{\"from\":\"559891809059\",\"id\":\"wamid.HBgMNTU5ODkxODA5MDU5FQIAEhggMzI0NkM4NDExQ0E0Rjk5OUI0QkNFQjI1QTg1NjFENTYA\",\"timestamp\":\"1745067051\",\"text\":{\"body\":\"Teste json bruto\"},\"type\":\"text\"}]},\"field\":\"messages\"}]}]}";
        WebhookEvent event = new Gson().fromJson(json, WebhookEvent.class);

        eventProcessor.process(event);
        verify(messengerService).send(any());
    }

    @Test
    @DisplayName("Não deve chamar o método send quando o campo body for nulo")
    void process_cenario2() {

        String json = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"1337254764011514\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15556429991\",\"phone_number_id\":\"550315404841934\"},\"contacts\":[{\"profile\":{\"name\":\"Pinheiro Jr.\"},\"wa_id\":\"559891809059\"}],\"messages\":[{\"from\":\"559891809059\",\"id\":\"wamid.HBgMNTU5ODkxODA5MDU5FQIAEhggMzI0NkM4NDExQ0E0Rjk5OUI0QkNFQjI1QTg1NjFENTYA\",\"timestamp\":\"1745067051\",\"type\":\"text\"}]},\"field\":\"messages\"}]}]}";
        WebhookEvent event = new Gson().fromJson(json, WebhookEvent.class);

        eventProcessor.process(event);
        verify(messengerService, never()).send(any());

    }

}