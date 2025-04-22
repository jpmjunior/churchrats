package br.com.pinheiroapps.churchrats.controller;

import br.com.pinheiroapps.churchrats.dto.MessageDto;
import br.com.pinheiroapps.churchrats.service.MessengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messenger")
public class MessengerController {

    private MessengerService messengerService;

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody MessageDto message) {

        messengerService.send(message);

        return ResponseEntity.ok().build();

    }

}
