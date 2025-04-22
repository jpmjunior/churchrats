package br.com.pinheiroapps.churchrats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {

    private String numberTo;
    private String message;

}
