import java.util.Scanner;
class LoSC{
    public static void main (String [] args){
        
        Scanner Scan = new Scanner(System.in);
        
        BaseDeDonnees base=new BaseDeDonnees();
        
        Menu monMenu=new Menu(base);
        
       
        

        System.out.println("Ajout de 3 locataires");
        String nomLocataire = "Nolan";
        String prenomLocataire = "Jhon";
        String adresseLocataire = "13 Bvd Louis XIV";
        String telephoneLocataire = "0658632459";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        nomLocataire = "Smith";
        prenomLocataire = "Victoria";
        adresseLocataire = "14 rue Charle V";
        telephoneLocataire = "0656841236";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        nomLocataire = "Aren";
        prenomLocataire = "Victor";
        adresseLocataire = "14 rue du moulin";
        telephoneLocataire = "0658632145";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        System.out.println(base.afficherInfosLocataire(0));
        System.out.println(base.afficherInfosLocataire(1));
        System.out.println(base.afficherInfosLocataire(2));

        //System.out.println("Supression du locataire ID 2");

        //base.supprimerLocataire(2);

        System.out.println(base.afficherInfosLocataire(0));
        System.out.println(base.afficherInfosLocataire(1));
        System.out.println(base.afficherInfosLocataire(2));
        
        //System.out.println(base.compteurLocataire[0]);
        base.afficherAlphaLoc();
        
        
        
        
        
          
        
        
         monMenu.menuPrincipal();
        
        
        
        }
        
    }
