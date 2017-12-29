import java.util.Scanner;

class Menu{
    BaseDeDonnees base = new BaseDeDonnees();
    Scanner Scan = new Scanner(System.in);

    void menuPrincipal(){
        System.out.println("\n----------------------------------\nLoSC (Locataires Sympas et Calmes)\n----------------------------------");
        System.out.println("1) Gestion des locataires (defaut)\n2) Gestion des biens\n3) Gestion des types de biens\n4) Gestion des locations\n5) sauvegarde/restauration des données dans des fichiers\n6) Quitter le progamme");
        switch (Scan.nextInt()){
            case 2:
                menu2();
                break;
            case 3:
                menu3();
                break;
            case 4:
                menu4();
                break;
            case 5:
                menu5();
                break;
            case 6:
                System.exit(1);
                break;
            default:
                menu1();
        }
    }
    void menu1(){
        System.out.println("\n----------------------\nGestion des locataires\n----------------------\n1) Ajouter, modifier ou supprimer un locataire (defaut)\n2) afficher la liste des locataire par ordre alphabétique\n3) afficher la liste des locataires ayant loué un type de bien pariculier\n4) rechercher le liste des locations d'un locataire particulier\n5) retour");  
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menu5();
                break;
            case 5:
                menuPrincipal();
                break;
            default:
                menu11();
        }
    }
    void menu2(){
        System.out.println("\n-----------------\nGestion des biens\n-----------------\n1) Ajouter, modifier ou supprimer un bien (defaut)\n2) afficher la liste des biens par ordre alphabétique\n3) afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu\n4) retour"); 
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menuPrincipal();
                break;
            default:
                menu21();
        }
    }
    void menu3(){
        System.out.println("\n--------------------------\nGestion des types de biens\n--------------------------\n1) Ajouter, modifier ou supprimer un type de bien (defaut)\n2) afficher la liste des types de biens\n3) retour");   
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menuPrincipal();
                break;
            default:
                menu31();
        }
    }
    void menu4(){
        System.out.println("\n---------------------\nGestion des locations\n---------------------\n1) Louer un bien (defaut)\n2) libérer un bien\n3) afficher la liste des biens loués\n4) afficher la liste des locataires de biens\n5) afficher la liste des locataires ayant au moin un bien en cours de location\n6) retour");  
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menu5();
                break;
            case 5:
                menu5();
                break;
            case 6:
                menuPrincipal();
                break;
            default:
                menu5();
        }
    }
    void menu5(){
        System.out.println("\n-----------------------------------------------------\nsauvegarde/restauration des données dans des fichiers\n-----------------------------------------------------\n1) Sauvegarde des données dans un ou plusieurs fichiers binaires (defaut)\n2) restauration des données dans les structures choisies à partir des fichiers de sauvegarde\n3) retour");         
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menuPrincipal();
                break;
            default:
                menu5();
        }
    }
    void menu11(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un locataire\n-----------------------------------------------------\n1) Ajouter un locataire (defaut)\n2) Modifier un locataire\n3) Supprimer un locataire\n4)retour");  
        
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menu1();
                break;
            default:
//                Juste un test que tu peux supprimer, le but étais de vérifier que l'on avais bien accès à la base depuis
//                le menu, c'est le cas.
//
//                System.out.println("Ajout de 3 locataires");
//                String nomLocataire = "Smith";
//                String prenomLocataire = "Jhon";
//                String adresseLocataire = "13 Bvd Louis XIV";
//                String telephoneLocataire = "0658632459";
//
//                base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);
//
//                nomLocataire = "Nolan";
//                prenomLocataire = "Victor";
//                adresseLocataire = "14 rue Charle V";
//                telephoneLocataire = "0656841236";
//
//                base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);
//
//                nomLocataire = "Laren";
//                prenomLocataire = "Victoria";
//                adresseLocataire = "14 rue du moulin";
//                telephoneLocataire = "0658632145";
//
//                base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);
//
//                System.out.println(base.afficherLocataire(0));
//                System.out.println(base.afficherLocataire(1));
//                System.out.println(base.afficherLocataire(2));
//
//                System.out.println("Supression du locataire ID 2");
//
//                base.supprimerLocataire(2);
//
//                System.out.println(base.afficherLocataire(0));
//                System.out.println(base.afficherLocataire(1));
//                System.out.println(base.afficherLocataire(2));
                menu5();
        }  
    }
    void menu21(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un bien\n-----------------------------------------------------\n1) Ajouter un bien (defaut)\n2) Modifier un bien\n3) Supprimer un bien\n4)retour");  
        
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menu2();
                break;
            default:
                menu5();
        }  
    }
    void menu31(){
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un type de bien\n-----------------------------------------------------\n1) Ajouter un type de bien (defaut)\n2) Modifier un type de bien\n3) Supprimer un type de bien \n4)retour");  
        
        switch (Scan.nextInt()){
            case 2:
                menu5();
                break;
            case 3:
                menu5();
                break;
            case 4:
                menu2();
                break;
            default:
                menu5();
        }  
    }

    /**
     * Permet d'effectuer des tests sur la classe Menu afin de s'assurer de son bon fonctionnement
     * @param args
     */
    public static void main(String[] args){
        Menu monMenu = new Menu();
        monMenu.menu1();
    }
}

