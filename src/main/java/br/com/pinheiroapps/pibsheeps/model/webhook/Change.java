
package br.com.pinheiroapps.pibsheeps.model.webhook;


import lombok.Data;

@Data
public class Change {

    private Value value;
    private String field;

}
