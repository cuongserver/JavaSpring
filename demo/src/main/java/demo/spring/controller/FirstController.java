package demo.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import demo.spring.annotations.AllowAnonymous;
import demo.spring.utilities.GoogleAuthUtility;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping(value = "first", produces = MediaType.APPLICATION_JSON_VALUE)
public class FirstController {
    private final GoogleAuthUtility googleAuthUtility;

    public FirstController(GoogleAuthUtility googleAuthUtility){
        this.googleAuthUtility = googleAuthUtility;
    }

    @GetMapping(value = "/sample-get")
    public String getSample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var X = new Object() {
            public final boolean success = true;
        };
        return mapper.writeValueAsString(X);
    }

    @AllowAnonymous
    @GetMapping(value = "/qr-get")
    public String[] getQR() throws IOException, WriterException {
        var key = UUID.randomUUID().toString();
        var secret = googleAuthUtility.generateBase32Encoding(key);
        return new String[]{googleAuthUtility.generateQRCode(key, "superuser", "demo.spring"), secret};
    }


    @AllowAnonymous
    @GetMapping(value = "/pin-validate")
    public boolean validatePIN(String PIN){
        return googleAuthUtility.validatePIN(PIN, "GFSWCOLBGRSTMMBWHAZGMMZZGAYDMYJYHE4WKMJRGU4WGZRVMQ3GIMRVMEYDIZLE");
    }

    @AllowAnonymous
    @GetMapping(value = "/two-pin-validate")
    public boolean validate2PIN(String PIN1, String PIN2){
        return googleAuthUtility.validateTwoConsecutivePIN(PIN1, PIN2, "GFSWCOLBGRSTMMBWHAZGMMZZGAYDMYJYHE4WKMJRGU4WGZRVMQ3GIMRVMEYDIZLE");
    }
}
