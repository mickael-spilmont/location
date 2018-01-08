import java.util.Scanner;
import java.io.*;

class Menu{
    Scanner scan = new Scanner(System.in);
    int indice=0;
    
    BaseDeDonnees base;
    Menu (BaseDeDonnees x) {
        base = x;
    }
    
    int id = 0;
    String nomLocataire = "";
    String prenomLocataire = "";
    String adresseLocataire = "";
    String telephoneLocataire = "";

//    Opérations sur la base

    void rechercheBien(){
        System.out.println("Recherche du bien à louer :\n" +
                "Entrez jusqu'à 4 mot clés (numero de rue, ville, CP...), laissez vide pour arrêter\n");

        System.out.println("Mot 01 : ");
        String mot01 = scan.nextLine();
        if (mot01.equals("")){
            System.out.println("Opération annulée, vous n'avaez entrez aucun mot clé.");
            return;
        }

        System.out.println("Mot 02 : ");
        String mot02 = scan.nextLine();
        if (mot02.equals("")){
            base.rechercheBien(mot01);
            return;
        }

        System.out.println("Mot 03 : ");
        String mot03 = scan.nextLine();
        if (mot03.equals("")){
            base.rechercheBien(mot01, mot02);
            return;
        }

        System.out.println("Mot 04 : ");
        String mot04 = scan.nextLine();
        if (mot04.equals("")){
            base.rechercheBien(mot01, mot02, mot03);
            return;
        }
        else{
            base.rechercheBien(mot01, mot02, mot03, mot04);
            return;
        }


    }


    
//    Menu

    void menuPrincipal(){
        System.out.println("\n----------------------------------\nLoSC (Locataires Sympas et Calmes)\n----------------------------------");
        System.out.println("1) Gestion des locataires (defaut)\n2) Gestion des biens\n3) Gestion des types de biens\n4) Gestion des locations\n5) sauvegarde/restauration des données dans des fichiers\n6) Quitter le progamme");
        switch (scan.nextInt()){
            case 2://gestion des biens
                menu2();
                break;
            case 3://gestion des types de biens
                menu3();
                break;
            case 4://gestion des locations
                menu4();
                break;
            case 5://sauvegarde/restauration des données
                menu5();
                break;
            case 6://quitter le programme
                System.exit(1);
                break;
            default://gestion des locataires
                menu1();
        }
    }
    void menu1(){
        System.out.println("\n----------------------\nGestion des locataires\n----------------------\n1) Ajouter, modifier ou supprimer un locataire (defaut)\n2) afficher la liste des locataire par ordre alphabétique\n3) afficher la liste des locataires ayant loué un type de bien pariculier\n4) rechercher le liste des locations d'un locataire particulier\n5) retour");  
        switch (scan.nextInt()){
            case 2://afficher la liste des locataire par ordre alphabétique
                base.afficherAlphaLoc();
                menu1();
                break;
            case 3://afficher la liste des locataires ayant loué un type de bien pariculier
                System.out.println("afficher la liste des locataires ayant loué un type de bien pariculier");
                System.out.print("indice du type de bien a rechercher : ");
                indice= scan.nextInt();
                base.afficherListeLocataireLoueType(indice);
                menu1();
                break;
            case 4://rechercher le liste des locations d'un locataire particulier
                System.out.print("");
                menu5();
                break;
            case 5://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un locataire
                menu11();
        }
    }
    void menu2(){
        System.out.println("\n-----------------\nGestion des biens\n-----------------\n1) Ajouter, modifier ou supprimer un bien (defaut)\n2) afficher la liste des biens par ordre alphabétique\n3) afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu\n4) retour"); 
        switch (scan.nextInt()){
            case 2://afficher la liste des biens par ordre alphabétique
                base.afficherAlphaBien();
                menu2();
                break;
            case 3://afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu
                System.out.print("Id du type : ");
                int idType= scan.nextInt();
                base.afficherListeTypeLoc(idType);
                menu2();
                break;
            case 4://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un bien
                menu21();
        }
    }
    void menu3(){
        System.out.println("\n--------------------------\nGestion des types de biens\n--------------------------\n1) Ajouter, modifier ou supprimer un type de bien (defaut)\n2) afficher la liste des types de biens\n3) retour");   
        switch (scan.nextInt()){
            case 2://afficher la liste des types de biens
                base.afficherType();
                menu3();
                break;
            case 3://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un type de bien
                menu31();
        }
    }
    void menu4(){
        System.out.println("\n---------------------\nGestion des locations\n---------------------\n1) Louer un bien (defaut)\n2) libérer un bien\n3) afficher la liste des biens loués\n4) afficher la liste des locataires de biens\n5) afficher la liste des locataires ayant au moin un bien en cours de location\n6) retour");  
        switch (scan.nextInt()){
            case 2://libérer un bien
//                base.libBien();
                menu4();
                break;
            case 3://afficher la liste des biens loués
                base.afficherBienLoue();
                menu4();
                break;
            case 4://afficher la liste des locataires de biens
                base.afficherListeLocBien();
                menu4();
                break;
            case 5://afficher la liste des locataires ayant au moin un bien en cours de location
                base.afficherListeLocBien();
                menu4();
                break;
            case 6://retour
                menuPrincipal();
                break;
            default://Louer un bien
//                base.loueBien();
                menu4();
        }
    }
    void menu5(){
        System.out.println("\n-----------------------------------------------------\nsauvegarde/restauration des données dans des fichiers\n-----------------------------------------------------\n1) Sauvegarde des données dans un ou plusieurs fichiers binaires (defaut)\n2) restauration des données dans les structures choisies à partir des fichiers de sauvegarde\n3) retour");         
        switch (scan.nextInt()){
            case 2://restauration des données dans les structures choisies à partir des fichiers de sauvegarde
                menu5();
                break;
            case 3://retour
                menuPrincipal();
                break;
            default://Sauvegarde des données dans un ou plusieurs fichiers binaires
                menu5();
        }
    }
    void menu11(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un locataire\n-----------------------------------------------------\n1) Ajouter un locataire (defaut)\n2) Modifier un locataire\n3) Supprimer un locataire\n4)retour");  
        
        switch (scan.nextInt()){
            case 2://Modifier un locataire
                System.out.println("Modifier un locataire");
                System.out.print("id du locataire a modifier : ");
                id= scan.nextInt();
                base.modifierLocataire(id);
                menu11();
                break;
            case 3://Supprimer un locataire
                System.out.println("Supprimer un locataire");
                System.out.print("ID du locataire a supprimer : ");
                id= scan.nextInt();
                base.supprimerLocataire(id);
                menu11();
                break;
            case 4://retour
                menu1();
                break;
            default://Ajouter un locataire
                System.out.println("Ajouter un locataire");
                System.out.print("nom du locataire : ");
                scan.nextLine();
                nomLocataire = scan.nextLine();
                System.out.print("prenom du locataire : ");
                prenomLocataire = scan.nextLine();
                System.out.print("adresse du locataire : ");
                adresseLocataire = scan.nextLine();
                System.out.print("numero de telephone du locataire : ");
                telephoneLocataire = scan.nextLine();
                base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);
                menu11();
        }  
    }
    void menu21(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un bien\n-----------------------------------------------------\n1) Ajouter un bien (defaut)\n2) Modifier un bien\n3) Supprimer un bien\n4)retour");  
        
        switch (scan.nextInt()){
            case 2://Modifier un bien
                System.out.println("Modifier un bien : ");
                System.out.print("id du bien a modifier : ");
                int idBien= scan.nextInt();
                base.modifierBien(base.rechCaseIdBien(idBien));
                menu21();
                break;
            case 3://Supprimer un bien
                System.out.println("Supprimer un bien");
                System.out.print("id du bien a supprimer : ");
                idBien= scan.nextInt();
                base.supprimerBien(base.rechCaseIdBien(idBien));
                menu21();
                break;
            case 4://retour
                menu2();
                break;
            default://Ajouter un bien
                System.out.println("Ajouter un bien");
                System.out.print("adresse du bien : ");
                scan.nextLine();
                String adresseBien= scan.nextLine();
                System.out.print("etat du bien : ");
                String etatBien= scan.nextLine();
                System.out.print("id du type de bien correspondant : ");
                int type= scan.nextInt();
                System.out.println("Montant du loyer : ");
                int loyerBien = scan.nextInt();
                base.ajouterBien(type,adresseBien,etatBien,loyerBien);
                menu21();
        }  
    }
    void menu31(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un type de bien\n-----------------------------------------------------\n1) Ajouter un type de bien (defaut)\n2) Modifier un type de bien\n3) Supprimer un type de bien \n4)retour");  
        
        switch (scan.nextInt()){
            case 2://Modifier un type de bien
                System.out.println("Modifier un type de bien");
                System.out.print("Id du type de bien a modifier : ");
                int idType= scan.nextInt();
                base.modifierType(idType);
                menu31();
                break;
            case 3://Supprimer un type de bien 
                System.out.println("Supprimer un type de bien");
                System.out.print("id du Type a supprimer : ");
                idType= scan.nextInt();
                base.supprimerType(base.rechCaseIdType(idType));
                menu31();
                break;
            case 4://retour
                menu3();
                break;
            default://Ajouter un type de bien 
                System.out.println("Ajouter un type de bien");
                System.out.print("nom du type de bien : ");
                scan.nextLine();
                String nomType= scan.nextLine();
                base.ajouterTypeBien(nomType);
                menu31();
        }  
    }

    public static void main(String[] args) throws IOException{

    }
}

