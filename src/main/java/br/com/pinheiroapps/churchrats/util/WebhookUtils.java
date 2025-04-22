package br.com.pinheiroapps.churchrats.util;

import br.com.pinheiroapps.churchrats.model.webhook.WebhookEvent;
import br.com.pinheiroapps.churchrats.model.webhook.Entry;
import br.com.pinheiroapps.churchrats.model.webhook.Change;
import br.com.pinheiroapps.churchrats.model.webhook.Value;
import br.com.pinheiroapps.churchrats.model.webhook.Message;
import br.com.pinheiroapps.churchrats.model.webhook.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WebhookUtils {

    private WebhookUtils() {
        // Evita instanciamento da classe utilitária
    }

    /**
     * Extrai o campo de text.body de um WebhookEvent.
     *
     * @param webhookEvent objeto desserializado da requisição Webhook
     * @return string (text.body), ou string vazia se não for encontrada
     */
    public static String getTextBody(WebhookEvent webhookEvent) {
        if (webhookEvent == null) return "";

        var bodyOpt = Optional.ofNullable(webhookEvent.getEntry())
                .stream()
                .flatMap(List::stream)
                .map(Entry::getChanges)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(Change::getValue)
                .filter(Objects::nonNull)
                .map(Value::getMessages)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(Message::getText)
                .filter(Objects::nonNull)
                .map(Text::getBody)
                .filter(Objects::nonNull)
                .findFirst();

        return bodyOpt.orElse("");

    }
}
