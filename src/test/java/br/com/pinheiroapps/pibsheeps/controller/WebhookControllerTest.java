package br.com.pinheiroapps.pibsheeps.controller;

import br.com.pinheiroapps.pibsheeps.service.EventProcessor;
import br.com.pinheiroapps.pibsheeps.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WebhookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private EventProcessor eventProcessor;

    @Value("${whatsapp.webhook.verify.token}")
    private String verifyToken;

    @Value("${m4d.app.secret}")
    private String appSecret;

    @Test
    void handleWebhook() throws Exception {

        String json = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"1337254764011514\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15556429991\",\"phone_number_id\":\"550315404841934\"},\"contacts\":[{\"profile\":{\"name\":\"Pinheiro Jr.\"},\"wa_id\":\"559891809059\"}],\"messages\":[{\"from\":\"559891809059\",\"id\":\"wamid.HBgMNTU5ODkxODA5MDU5FQIAEhggMzI0NkM4NDExQ0E0Rjk5OUI0QkNFQjI1QTg1NjFENTYA\",\"timestamp\":\"1745067051\",\"text\":{\"body\":\"Teste json bruto\"},\"type\":\"text\"}]},\"field\":\"messages\"}]}]}";
        String signature = "sha256=".concat(SecurityUtils.generateXHub256Sig(json, appSecret));

        mvc.perform(
                post("/webhook")
                        .header("x-hub-signature-256", signature)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(eventProcessor).process(any());

    }
}