import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Wallet {
    public RSAPublicKey pubKey;
    public RSAPrivateKey prvKey;
    int value;
    public Wallet(  ) throws Exception {
        KeyPairGenerator rsaGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        rsaGenerator.initialize(512, random);
        KeyPair rsaKeyPair = rsaGenerator.generateKeyPair();
        this.prvKey = (RSAPrivateKey) rsaKeyPair.getPrivate();
        this.pubKey = (RSAPublicKey) rsaKeyPair.getPublic();
        value=0;

    }
    @Override
    public String toString() {
        return   "key " + pubKey  ;
    }

}
