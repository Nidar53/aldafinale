        import jade.core.AID;
        import jade.core.Agent;
        import jade.core.behaviours.CyclicBehaviour;
        import jade.core.behaviours.OneShotBehaviour;
        import jade.core.behaviours.ParallelBehaviour;
        import jade.lang.acl.ACLMessage;
        import jade.lang.acl.UnreadableException;

        import javax.swing.*;
        import javax.swing.JFrame;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.IOException;
        import java.io.Serializable;
        import java.io.UnsupportedEncodingException;
        import java.nio.charset.StandardCharsets;
        import java.security.InvalidKeyException;
        import java.security.NoSuchAlgorithmException;
        import java.security.Signature;
        import java.security.SignatureException;
        import java.security.interfaces.RSAPrivateKey;
        import java.security.interfaces.RSAPublicKey;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.*;

        import com.google.common.hash.Hashing;
        import sun.misc.BASE64Encoder;


        public class  Nœud extends Agent  {



    JPanel p1 = new JPanel();
    JButton b=new JButton("Créer Une Transaction");
    JButton b2=new JButton("Envoyer Une Transaction");
    JButton b3=new JButton("Créer Un Block");
    JButton b5=new JButton("Envoyer Un Block");
    JButton b4=new JButton("Envoyer public key");
    JButton b6=new JButton("menage");
    JButton b7=new JButton("get History");
    JTextArea t3 = new JTextArea("  List des Transaction");
    JTextArea t4 = new JTextArea("logs");
    JTextArea t2 = new JTextArea("  List des Block");
    JTextArea t1 = new JTextArea("System information");


    LinkedList<Transaction> list = new LinkedList<>();
    HashMap<String , RSAPublicKey> ListKey = new HashMap<>();
    //LinkedList<String> list_key = new LinkedList< String , String>( );
    String [] [] tableKey ;

    ArrayList<Transaction> listTran = new ArrayList<>();

    LinkedList<String> list3 = new LinkedList<>();
    public String idt2 ;
    public  boolean mené=false;
            int Dificulti=4 ;

   // public HashMap<Integer , Transaction> list10 ;
   // public HashMap<String, Transaction> list10 ;

 // public LinkedList<Transaction> list = new LinkedList<>();
    LinkedList<Block> list2 = new LinkedList<>();

    // JTextArea t2 = new TextArea(" ");


    String ag;
    String ag2;
    String ag3;
    String ag4;
    String ag5;
    String ag6;
    String [] tab = new String[5];



    public void setup() {

        Object[] args = getArguments();
        if (args != null)

            for (int i = 0; i <5; i++)
            {
                tab[i] = (String) args[i];

            }



        JFrame f=new JFrame("Interface de L'agent : " + getLocalName() +"");



        b.setBounds(20,20,200,45);
        b2.setBounds(20,65,200,45);
        b3.setBounds(20,110,200,45);
        b4.setBounds(20,155,200,45);
        b5.setBounds(20,200,200,45);
        b6.setBounds(20,245,200,45);
        b7.setBounds(20,290,200,45);

        t1.setBounds(230 ,20,440,70 );
        t2.setBounds(230 ,100,220,240);
        t3.setBounds(460 ,100,220,240);
       // t4.setBounds(410,200,200,100);


        t2.setBackground(Color.GRAY);
        t2.setForeground(Color.WHITE);



        f.add(b);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(b7) ;
        f.add(t1);
        f.add(t2);
        f.add(t3);
       // f.add(t4);
        f.setSize(720,450);
        f.setLayout(null);
        f.setVisible(true);




        ParallelBehaviour parallel = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);

        parallel.addSubBehaviour(new Send() );
        parallel.addSubBehaviour(new ConsulterBoite());

        addBehaviour(parallel);


    }



    public class Send extends OneShotBehaviour {


        public String sha256(String input) {
            String output = Hashing.sha256().hashString((CharSequence) input, StandardCharsets.UTF_8) .toString();
            return output;

        }
        public void action() {


            b7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t2.setText(" history " +"\n" + list2);
                }} );

            // Block.tran2;

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {



                            //   System.out.println("block created");


            t1.setText("block created");

                }});


                    b5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Random r = new Random();


                            Block msgContenu = new Block(r.nextInt(11), getLocalName(), "1", list3 );

                       //     Block.mineBlock(1);

                            list2.add(msgContenu);







                            Wallet w2 = null;

                            try {
                                w2 = new Wallet();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            RSAPublicKey w3 = w2.pubKey;


                            final TestSend test4 = new TestSend(w3, msgContenu, 1);
                            String keyW ;
                            keyW = w3.toString();

//                                int i = 0 ;
//                                tableKey[i][0] = keyW;
//                                i++ ;



                            System.out.println( " l'agent: " + getLocalName() + " my list tran " + list);

                            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);


                            message.addReceiver(new AID(tab[0], AID.ISLOCALNAME));
                            message.addReceiver(new AID(tab[1], AID.ISLOCALNAME));
                            message.addReceiver(new AID(tab[2], AID.ISLOCALNAME));
                            message.addReceiver(new AID(tab[3], AID.ISLOCALNAME));
                            message.addReceiver(new AID(tab[4], AID.ISLOCALNAME));

                            try {

                                message.setContentObject(test4);
                                send(message);


                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


                        }






    });



            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    t1.setText("Transaction  created");




            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Random r2 =new Random();



                    Transaction msgTran = new Transaction("1");


                    try {

                        byte[] outbuff = new byte[1000];
                        byte[] data = msgTran.toString().getBytes("UTF8");
                        Wallet w6 = new Wallet();

                        RSAPrivateKey w9 = w6.prvKey;
                        RSAPublicKey w10 = w6.pubKey;
                        try {
                            Signature sr =  Signature.getInstance("SHA1WithRSA");

                            sr.initSign(w9);
                            sr.update(data);
                            byte[] data2 = sr.sign();
                         //  String data3 = new BASE64Encoder().encode(data2);
                            System.out.println("Signature:" + new BASE64Encoder().encode(data2));

                            System.out.println("data 1 is " + data);
                            System.out.println("data 2 is " + data2);



                            TestSend test99 = new TestSend(msgTran  , 0 , getLocalName() , data2 , w10);


                            idt2 = String.valueOf(msgTran);

                            list.add(msgTran);

                            list3.add(idt2);

                            /// list10.put( idt2  , msgTran);

                            // list11.add(msgTran);

                            System.out.println( " l'agent: " + getLocalName() + "  mi liked list "+ list3 );



                            String an2 = t3.getText();
                            t3.setText(an2 + "\n" + "I send tran : "+String.valueOf(msgTran));


                            System.out.println( " l'agent: " + getLocalName() + "my list tran " + list );



                    ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

                    message.addReceiver(new AID(tab[0], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[1], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[2], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[3], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[4], AID.ISLOCALNAME));


                    try {

                        message.setContentObject(test99);
                        send(message);
                        System.out.println("i send it");


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                        } catch (NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }





                }
            });  }});


            b4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    Wallet w2 = null;
                    try {
                        w2 = new Wallet();
                        RSAPublicKey w3 = w2.pubKey;
                        TestSend test = new TestSend( w3, 2);




                        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

                        message.addReceiver(new AID(tab[0], AID.ISLOCALNAME));
                        message.addReceiver(new AID(tab[1], AID.ISLOCALNAME));
                        message.addReceiver(new AID(tab[2], AID.ISLOCALNAME));
                        message.addReceiver(new AID(tab[3], AID.ISLOCALNAME));
                        message.addReceiver(new AID(tab[4], AID.ISLOCALNAME));

                        try {

                            message.setContentObject(test);
                            send(message);


                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                   // TestSend test5 = new TestSend( w.pubKey, 2);









                }
            });




        }}


    public class ConsulterBoite extends CyclicBehaviour {

        ACLMessage msgRecu = receive();
        public void action() {


            msgRecu=receive();

            //String s1 = msgRecu.toString();
            //System.out.println(s1);






            if(msgRecu!=null) {





                try {

                   // Transaction block = (Transaction) msgRecu.getContentObject();
                    TestSend test3 = (TestSend) msgRecu.getContentObject();

                    if (test3.d==0){
                   // int blockId = test3.idT;
                   // String blockversion = test3.version;

                    String an = t3.getText();

                    System.out.println( " * i resived tran segnsture  " + test3.data2);
                    System.out.println( " *(* i key public   " + test3.w);





                            try {

                                byte[] outbuff2 = new byte[1000];
                                byte[] data9 = test3.trans.toString().getBytes("UTF8");

                                try {

                                    Signature sr =  Signature.getInstance("SHA1WithRSA");

                            sr.initVerify(test3.w);
                            sr.update(data9);

                          //  byte[] signatureBytes = sr2.sign();
                           System.out.println("//// data 3 "+data9);
                         //   System.out.println("Signature:" + new BASE64Encoder().encode(signatureBytes));

                            boolean data22 = sr.verify(test3.data2);

                            System.out.println(sr.verify(test3.data2));


                          //   if (test3.data2==64) {


                                 t3.setText(an + "\n" + "I received tran " + test3.trans + " from " + test3.sender);

                                 list.add(test3.trans);
                            // }

                            } catch (NoSuchAlgorithmException ex) {
                                ex.printStackTrace();
                            }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }





                    //   list10.put(0, test3.trans);
                      //  System.out.println("list des hash map " + list10);





                  //  System.out.println("Je suis l'agent: " + getLocalName()  );
                } else if (test3.d==1){

                        String an = t2.getText();
                        t2.setText( an + "\n"   + test3.blo  );
                        t1.setText(  " msg ID "  + test3.w  );
                        list2.add(test3.blo);


                        System.out.println("     History   "  +"\n"+ list2);

                } else if (test3.d==4){


                        String an = t2.getText();
                        t2.setText( an + "\n"   + test3.blo  );
                    //    t1.setText(  " msg ID "  + test3.w  );
                        list2.add(test3.blo);

                        System.out.println("    i am " + getLocalName() +  "  and i received a Block   ") ;

                }

                else {
                       // String an = t1.getText();
                        t1.setText(  " msg ID "  + test3.w  );

                        System.out.println( test3.w  );

                        ListKey.put(getLocalName() , test3.w );

                        System.out.println("la list des key is " + ListKey);



                    }

                }catch (UnreadableException e) {
                    e.printStackTrace();
                }


            }


            if (mené==false) {
                boolean a = true ;
                if(a==true) {



                    Random r = new Random();


                    // Block msgContenu = new Block(r.nextInt(11), getLocalName(), "1", list3 );

                    Block b = new Block(r.nextInt(11), getLocalName(), "1", list3);
                    Block b100;

                    b = Block.firstBlockCreation();//block jdid

                    b100 = b.proofOfWork(Dificulti, mené);//block miné
                    TestSend test78 = new TestSend(b100, 4);

                    ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

                    message.addReceiver(new AID(tab[0], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[1], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[2], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[3], AID.ISLOCALNAME));
                    message.addReceiver(new AID(tab[4], AID.ISLOCALNAME));

                    try {

                        message.setContentObject(test78);
                        send(message);


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    DateFormat dateFormat = new SimpleDateFormat("H H:mm:ss:");
                    Date date = new Date();
                    String timestamp2 = dateFormat.format(date);
                    System.out.println("    minned finished    " + getLocalName() +  "  AT   "  +timestamp2) ;
                    System.out.println("    i send it from    " + getLocalName() +  "  to ALL   ") ;

                    a = false ;
                    mené = true;
                    Dificulti++;
                    Block.setTarget(Dificulti);

                }
            }
            //System.out.println(b100.toString());


        }
    }







}








