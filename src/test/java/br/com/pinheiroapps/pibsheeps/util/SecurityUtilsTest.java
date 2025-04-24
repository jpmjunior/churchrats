package br.com.pinheiroapps.pibsheeps.util;

import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    @Test
    void generateXHub256Sig() throws NoSuchAlgorithmException, InvalidKeyException {

        String json = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"1337254764011514\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15556429991\",\"phone_number_id\":\"550315404841934\"},\"contacts\":[{\"profile\":{\"name\":\"Pinheiro Jr.\"},\"wa_id\":\"559891809059\"}],\"messages\":[{\"from\":\"559891809059\",\"id\":\"wamid.HBgMNTU5ODkxODA5MDU5FQIAEhggODU2NTczRjMxM0I0RUQ3N0FFMDVFNkI2RTgwM0VEODgA\",\"timestamp\":\"1745066436\",\"text\":{\"body\":\"Teste com 2 webhooks\"},\"type\":\"text\"}]},\"field\":\"messages\"}]}]}";
        String expectedSignature = "f77ede1d4948db9efedc7593471ab8725326a6c942dffc354692e91a3cfb93f1";
        String appScret = "sAA2JH7selE94leLEdpY9Wdw4lSM6s7G";

        String generatedSignature = SecurityUtils.generateXHub256Sig(json, appScret);

        assertEquals(expectedSignature, generatedSignature);

    }
}