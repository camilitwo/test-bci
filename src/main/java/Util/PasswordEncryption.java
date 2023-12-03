package Util;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Component
public class PasswordEncryption {

    private static SecretKeySpec secretKey;
    private static final String ALGORITHM = "AES";
    private static final String SECRET = "camilogonzalezvillalobospruebabcijavajwt";

    private static void setKey() throws Exception {
        MessageDigest md;
        byte[] key = SECRET.getBytes(StandardCharsets.UTF_8);
        md = MessageDigest.getInstance("SHA-1");
        key = md.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, ALGORITHM);
    }

    @SneakyThrows
    public static String encrypt(String strToEncrypt){
        try {
            setKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new Exception("Error al encriptar: " + e);
        }
    }
}
