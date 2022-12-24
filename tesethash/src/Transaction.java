import com.google.common.hash.Hashing;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transaction  implements Serializable {

    public String idT;
    public String idhash ;
    public String version;

    public RSAPublicKey sender;
    public RSAPublicKey reciepient;

    public String SenderHash;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    String timnestamp = dateFormat.format(date);


    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public Transaction( String version) {
     //   this.idT = String.valueOf(idT);


        this.version = version;


        this.inputs=inputs ;
        this.outputs=outputs ;
        this.timnestamp=timnestamp;
        this.idhash = sha256( timnestamp, version );


    }

    public String sha256(String input , String input2) {
        String output = Hashing.sha256().hashString((CharSequence) input + input2 , StandardCharsets.UTF_8) .toString();
        return output;


    }





    public class TransactionInput {

        public int inCounter;
        public ArrayList listIn;

        public TransactionInput( int inCounter ) {
            this.inCounter = inCounter++ ;

        }

    }

    public String str() {
        String input = timnestamp+ version + idhash;
        return input;
    }

    public class TransactionOutput {

        public String out_counter;
        public ArrayList listOut;

        public String idT2;

        public float value;
        public String parentTransactionId;

        //Constructor
        public TransactionOutput(float value) {

            this.value = value;


          //  this.idT2 = Hashing.sha256().hashString((CharSequence) parentTransactionId, StandardCharsets.UTF_8).toString();




        }

            @Override
            public String toString() {
                return "ID=" + value  ;
            }




    }


    @Override
    public String toString() {
        return "Time =" + timnestamp + ' '
               + "V=" + version+ ' ' +"\n"+
                 "idhash=" + idhash
                + "input =" + inputs + ' '
                + "output =" + outputs + ' '


                ;
    }



}



