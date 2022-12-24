import com.google.common.hash.Hashing;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.text.DateFormat;
import java.util.*;


public class Block implements Serializable {
    private static String mk3;
    public String hash;
    public int id ;
    public String sender ;
    public String mk ;
    public String mk2 ;
    public static String previousHash;
    public String test ;
    public  String input ;
    public static int Target;



   // public String hash ;



    public String version  = "1" ;
    public String hashprevblock ;
    public int nonce ;
    public String target ;
    public String merkleroot ;

    public String hashheaderblock;

// hash map
     //public HashMap<Integer , Transaction> donnes2 ;
    public LinkedList<Transaction> donnes ;
  //  public HashMap < Integer , Transaction> donnes  ;


    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


    Date date = new Date();
    String timestamp = dateFormat.format(date);
    LinkedList<String> list3 = new LinkedList<>();





    public Block(int id, String sender, String version , LinkedList<String> list3 ) {
        this.id = id ;
        this.sender = sender ;
        this.version = version;
        this.hashheaderblock = hashprevblock;
        this.donnes=donnes;
        this.timestamp= timestamp;
        this.nonce= nonce;
        this.target=target;




//        for(int i=0; i<list3.size(); i++){
//            String list33 = list3.get(i).concat(list3.get(i+1));
//
//        }

        this.list3= list3;



     //   this.mk= MymerkleRoot(list3);

      this.mk= "f3850c939af4e67b6c6d365cccc4479b6ed3d1daed0bc4fca2405ac9d9562e";

      //  markeltreee t1=new markeltreee();
       // String rootMerkle=t1.merkleTree(list3,0,1);


        System.out.println(" block creted fron block class ");

       // this.merkleroot = sha256(String.valueOf(list3));



        //this.merkleroot= merkleroot;
    }


    @Override
    public String toString() {
        return   "id=" + id+ ' ' +
                " sender=" + sender + ' '+
                " V="+version+ "\n"   +
                " Time ="+timestamp+"\n"   +
                " the markelroot "+" \n "+ mk + "\n"   +
              //  " hash is "+" \n "+ donnes + "\n"   +
                "------------------------------------"
        ;


    }


    public String sha256(String input) {
        String output = Hashing.sha256().hashString((CharSequence) input, StandardCharsets.UTF_8) .toString();
        return output;

    }



    public  String MymerkleRoot(LinkedList<String> txnLists) {
        LinkedList<String> merkleRoot = merkleTree(txnLists);
        return merkleRoot.get(0);
    }

    public LinkedList<String>  merkleTree(LinkedList<String> List1 ){


        if(List1.size() == 1){
            return List1;
        }


        LinkedList<String> List2 =new LinkedList<>();


        for(int i=0; i<List1.size(); i+=2){
            String hashedString = sha256(List1.get(i).concat(List1.get(i+1)));
            List2.add(hashedString);
        }

        if(List1.size() % 2 == 1){
            String lastHash=List1.get(List1.size()-1);
            String hashedString = sha256(lastHash.concat(lastHash));
            List2.add(hashedString);
        }
        return merkleTree(List2);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String str() {
        input = timestamp + nonce;//str se change a chaue fois que nonce et timestamp se change = nonce est var globale = le hash se change
        return input;
    }

    public static String calculateHash(Block blo) {
        String hash = Hashing.sha256().hashString(blo.str(), StandardCharsets.UTF_8).toString();
        return hash;
    }
    public  String getHash() {
        String hash=this.calculateHash(this);
        return hash;
    }
    public static void setTarget(int target) {
        Target=target;
    }


    public static Block firstBlockCreation() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp;
        timestamp = new Timestamp(datetime);
        Transaction T1 =new Transaction("first");//vals aleatoires
        Transaction T2 =new Transaction("second");

        LinkedList<String> data2 = new LinkedList<>() ;
        String idt2 = String.valueOf(T1);
        String idt3 = String.valueOf(T1);

        data2.add(idt2);
        data2.add(idt3);


        LinkedList<String> data = new LinkedList<>() ;//liste des transactions string pour la passer a la methode merkelroot
      //  mk3 = MymerkleRoot(data);
        data.add(T1.str());
        data.add(T2.str());

        Random r = new Random();
        Block firstblock =new  Block(r.nextInt(11),"agent","2", data2);//previous hash null prcq hwa lwl
        return firstblock;
    }




    public  Block proofOfWork(int difficulty,boolean meni ) {
        nonce = 0;

        while (!meni==true) {
            if(this.getHash().substring(0, difficulty).equals(zeros(difficulty))) {
                meni=true;
                System.out.println("the target hash " +this.getHash());
                System.out.println(this.toString());
                System.out.println( " the difficulty is " +difficulty);
                System.out.println( " the nonce is " +nonce);


                break;
            }else {
                nonce++;
                hash = this.getHash();

            }
        }

        return this;

    }

    public static String zeros(int length) {//methode predefinie qui genere des 0 avec le nombre precis
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append("0");
        }

        return builder.toString();
    }







}





//    public String calculateHash() {
//        String calculatedhash = sha256(
//                        timestamp
//
//                     // +  Integer.toString(nonce) +
//                       // mk
//        );
//        return calculatedhash;
//    }
//
//
//    public  String DificultyString(int difficulty) {
//        return new String(new char[difficulty]).replace('\0', '0');
//    }
//
//    public void mineBlock(int difficulty) {
//       // MymerkleRoot =  MymerkleRoot(transactions);
//        String target = DificultyString(difficulty); //Create a string with difficulty * "0"
//        while(! hash.substring( 0, difficulty).equals(target)) {
//            nonce ++;
//            hash = calculateHash();
//        }
//        System.out.println("Block Mined!!! : " + hash);
//
//    }


//    public String hetHash(){
//        String hash = this.sha256(this);
//        return hash;
//    }
//



//
//
//    public Block pf(int difficulty , boolean meni ) {
//
//        nonce =0 ;
//        while (\meni==true) {
//            if (this.getrHash().substring)
//        }
//
//    }
















/* public String hashtree(ArrayList<String> t2) {
        ArrayList<String> tran = new ArrayList<String>();
        ArrayList<String> tran2 = new ArrayList<String>();
        for (int i = 0; i < t2.size(); i += 2) {
            tran.add(sha256(t2.get(i).concat(t2.get(i + 1))));
        }

        while (tran.size()!=1) {

            if (tran.size()%2!=0){
              tran.add(tran.get(tran.size()-1));
            }
            for (int j=0 ; j<tran.size()-1;j=j+1){
                tran2.add(sha256(tran.get(j).concat(tran.get(j+1))));
            }
            tran.clear();
            tran.addAll(tran2);
            tran2.clear();
        }
        String s =tran.get(0);
        return s;
    }  */
