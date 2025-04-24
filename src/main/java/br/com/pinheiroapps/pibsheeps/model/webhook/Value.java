
package br.com.pinheiroapps.pibsheeps.model.webhook;

import lombok.Data;

import java.util.List;

@Data
public class Value {

    private String messagingProduct;
    private Metadata metadata;
    private List<Contact> contacts;
    private List<Message> messages;

}
