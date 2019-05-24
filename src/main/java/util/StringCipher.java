package util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * encrypt and decrypt a string simply<br>
 * encrypt and decrypt with 'AES/CBC/PKCS5Padding', encode and decode with Base64url
 */
public class StringCipher {


    private static final String CHARSET = "UTF-8";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private final SecretKeySpec key;
    private final IvParameterSpec iv;

    /**
     * Constructor
     * 
     * @param key
     * @param iv
     */
    public StringCipher(final String key, final String iv) {
        this.key = new SecretKeySpec(key.getBytes(), "AES");
        this.iv = new IvParameterSpec(iv.getBytes());
    }

    /**
     * encrypt
     * 
     * @param str
     * @return
     */
    public String encrypt(final String str) {
        return CheckedExceptionWrapper.wrap(() -> {
            Cipher encrypter = Cipher.getInstance(TRANSFORMATION);
            encrypter.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] byteResult = encrypter.doFinal(str.getBytes(CHARSET));
            return new String(Base64.getUrlEncoder().encode(byteResult), CHARSET).replace("=", "");
        });
    }

    /**
     * decrypt
     * 
     * @param str
     * @return
     */
    public String decrypt(final String str) {
        return CheckedExceptionWrapper.wrap(() -> {
            Cipher decrypter = Cipher.getInstance(TRANSFORMATION);
            decrypter.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] byteResult =
                    decrypter.doFinal(Base64.getUrlDecoder().decode(str.getBytes(CHARSET)));
            return new String(byteResult, CHARSET);
        });
    }
}
