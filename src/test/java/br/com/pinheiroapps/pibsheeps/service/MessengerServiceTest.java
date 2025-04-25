package br.com.pinheiroapps.pibsheeps.service;

import br.com.pinheiroapps.pibsheeps.domain.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("test")
class MessengerServiceTest {

    @Autowired
    private MessengerService messengerService;

    @Test
    void send() {

        String numberPhoneTo = "559891809059";
        String textBody = "Messagem enviada via JUnit Test";

        MessageDto message = new MessageDto(numberPhoneTo, textBody);

        assertDoesNotThrow(() -> messengerService.send(message));

    }
}