package br.com.pinheiroapps.churchrats.service;

import br.com.pinheiroapps.churchrats.domain.dto.MessageDto;
import br.com.pinheiroapps.churchrats.model.webhook.WebhookEvent;
import br.com.pinheiroapps.churchrats.util.MenuChatUtils;
import br.com.pinheiroapps.churchrats.util.WebhookUtils;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProcessor {

    @NonNull
    private MessengerService messengerService;

    public void process(WebhookEvent event) {

        String body = WebhookUtils.getTextBody(event);

        if (!body.isEmpty()){
            String numberTo = event.getEntry().getFirst().getChanges().getFirst().getValue().getMessages().getFirst().getFrom();
            MessageDto message = new MessageDto(numberTo, MenuChatUtils.MENU_PRINCIPAL);

            log.info("Enviando mensagem: {}", new Gson().toJson(message));

            messengerService.send(message);

        }

    }

}
