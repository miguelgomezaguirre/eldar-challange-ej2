package com.eldar.challange.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class EncryptService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private SecretKey secretKey;

    public EncryptService() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        secretKey = keyGen.generateKey();
    }

    public String encrypt(String valueToEncrypt) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(valueToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

}
