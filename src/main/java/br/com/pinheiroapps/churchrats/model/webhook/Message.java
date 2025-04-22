
package br.com.pinheiroapps.churchrats.model.webhook;

import lombok.Data;

@Data
public class Message {

    private String from;
    private String id;
    private String timestamp;
    private Text text;
    private String type;

}
