package tech.loga.vendor;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class Crypto
{
    private static Crypto crypto;
    private SecretKey key;
    private IvParameterSpec ivps;
    private final String algorithm = "AES/CBC/PKCS5Padding";
    
    private Crypto() {
        try {
            this.key = this.generateKey(128);
            this.ivps = this.generateIv();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    public static Crypto getInstance() {
        if (Crypto.crypto == null) {
            Crypto.crypto = new Crypto();
        }
        return Crypto.crypto;
    }
    
    public String generateToken(final int nmb) {
        final Random random = new Random();
        final String generatedPassword = random.ints(48, 123).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(nmb).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedPassword;
    }
    
    public String encrypt(final String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(1, this.key, this.ivps);
        final byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    public String decrypt(final String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(2, this.key, this.ivps);
        final byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }
    
    public SecretKey generateKey(final int n) throws NoSuchAlgorithmException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }
    
    public IvParameterSpec generateIv() {
        final byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
