package editor.crypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    private static final String ALGORITIMO = "MD5";
//    private static final int BIN = 02;
//    private static final int DEC = 10;
    private static final int HEX = 16;
    
    /**
     * Retorna uma String de entrada criptografada. 
     * @param senha
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String criptografar(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITIMO);
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        return hash.toString(HEX);
    }
}
