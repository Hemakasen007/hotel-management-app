package com.travel.lodge.authservice.util;

import com.travel.lodge.authservice.exception.KeyGenerationException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class EncryptionUtil {

    @Value("${pin.encryption.initVector}")
    private String initVector;

    @Value("${pin.encryption.key}")
    private String key;


    public String decrypt(String encrypted) {


        var iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        var secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher;
        var original = new byte[0];
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            original = cipher.doFinal(Base64.decodeBase64(encrypted));
        } catch (Exception e) {
            throw new KeyGenerationException(e.getMessage());
        }

        return new String(original);

    }

}
