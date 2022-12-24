import java.io.Serializable;
import java.security.interfaces.RSAPublicKey;

public class TestSend  implements Serializable {

    int d ;
    Block blo;
    Transaction trans ;
    public String sender;
    byte[] data2;
    // Wallet w ;
    RSAPublicKey w;


    public TestSend ( RSAPublicKey w ,Block blo, int d )  {
        this.w=w;
        this.d=d;
        this.blo=blo;

      // this.wal=wal;
    }
    public TestSend ( Transaction trans , int d , String sender  , byte[] data2  , RSAPublicKey w) {

        this.d=d;
        this.trans=trans;
        this.sender=sender;
        this.data2 =data2;
        this.w=w;
    }
    public TestSend ( Transaction trans , int d , String sender  ) {

        this.d=d;
        this.trans=trans;
        this.sender=sender;

    }
    public TestSend(RSAPublicKey w , int d) {
        this.w=w;
        this.d=d;


    }
    public TestSend(Block blo, int d) {
        this.blo=blo;
        this.d=d;


    }

    public TestSend() {



    }


}
