public class Test {


    public static void  main(String[] args) {



        String[] commande = new String[3];
        String argument = "";


        argument = argument+"s1:Nœud(s2,s3,s4,s5,s6)";
        argument =argument + ";";

        argument = argument+"s4:Nœud(s1,s3,s2,s5,s6)";
        argument =argument + ";";

        argument = argument+"s2:Nœud(s1,s3,s4,s5,s6)";
        argument =argument + ";";

        argument = argument+"s3:Nœud(s1,s2,s4,s5,s6)";
        argument =argument + ";";

        argument = argument+"s5:Nœud(s1,s2,s3,s4,s6)";
        argument =argument + ";";

        argument = argument+"s6:Nœud(s1,s2,s3,s4,s5)";
        argument =argument + ";";







        commande[0]="-cp";
        commande[1]="jade.boot";
        commande[2]=argument;

        jade.Boot.main(commande);

    }}
