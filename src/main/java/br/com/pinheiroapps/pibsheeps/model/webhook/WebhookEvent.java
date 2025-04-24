
package br.com.pinheiroapps.pibsheeps.model.webhook;

import lombok.Data;

import java.util.List;

@Data
public class WebhookEvent {

    private String object;
    private List<Entry> entry;

}
