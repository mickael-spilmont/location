import java.util.Scanner;
import java.io.*;

class Menu {
    BaseDeDonnees base = new BaseDeDonnees();
    GestionFichier fichier;

    /**
     * Variable qui permet de stocker le nom du fichier de base qui à été chargé afin de le proposer par défaut lors de
     * la sauvegarde
     */
    String fichierDeTravailEnCour = "";

    /**
     *objet scanner
     */
    Scanner scan = new Scanner(System.in);

    /**
     *contient l'indice d'un locataire,bien ou type de bien
     */
    int indice = 0;

    /**
     *contient le nom du locataire
     */
    String nomLocataire = "";

    /**
     *contient le prenom du locataire
     */
    String prenomLocataire = "";

    /**
     *contient l'adresse d'un locataire
     */
    String adresseLocataire = "";

    /**
     *contient le numero de telephone d'un locataire
     */
    String telephoneLocataire = "";

//    Opérations sur la base

    /**
     * Méthode permettanmt le chargement d'un fichier de sauvegarde, elle remplie la variable fichierDeTravailEnCour,
     * afin de proposer le même fichier lors de la sauvegarde
     * @throws IOException
     */
    void chargement() throws IOException {
        GestionFichier fichier = new GestionFichier();
        System.out.println("liste des fichiers");
        fichier.listerFichier();
        System.out.print("entrez le nom du fichier a charger : ");
        scan.nextLine();
        String nomFichier = scan.nextLine();

        fichierDeTravailEnCour = nomFichier;

        fichier.setCheminFichier(nomFichier);
        System.out.println(fichier.lire(base));
    }

    /**
     * Méthode qui appel prépare et appel la sauvegarde de la classe "GestionFichier".
     * Elle commence par vérifier si le programme à chargé un fichier de base, dans ce cas ce fichier est proposé par
     * défaut lors de la sauvegarde.
     * @throws IOException
     */
    void sauvegarde() throws IOException{
        GestionFichier fichier = new GestionFichier();

        // Vérifie si il y'a un fichier qui à été chargé
        if (fichierDeTravailEnCour.equals("")) {
            System.out.println("Dans quel fichier souhaitez vous sauvegarder la base ?");
        }
        else{
            System.out.println("Dans quel fichier souhaitez vous sauvegarder la base ? ( '" + fichierDeTravailEnCour +
                    "' par défaut)");
        }

        scan.nextLine();
        String nomFichier = scan.nextLine();

        // Vérification de l'entré de l'utilisateur
        if (nomFichier.equals("")){
            fichier.setCheminFichier(fichierDeTravailEnCour);
        }
        else{
            fichier.setCheminFichier(nomFichier);
        }

        // Lancement de la sauvegarde
        System.out.println(fichier.ecrire(base));
    }

//    Menu

    /**
     *menu principal du progamme qui permet de choir entre les sous-menus du progamme, c'est a dire : gestion des locataires; gestion des biens; gestion des types de biens; gestion des locations; sauvegarde/restauration des données; quitter le programme
     * @throws IOException
     */
    void menuPrincipal() throws IOException {
        System.out.println("\n----------------------------------\nLoSC (Locataires Sympas et Calmes)\n----------------------------------");
        System.out.println("1) Gestion des locataires (defaut)\n2) Gestion des biens\n3) Gestion des types de biens\n4) Gestion des locations\n5) sauvegarde/restauration des données\n0) Quitter le progamme");
        switch (scan.nextInt()) {
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
            case 0://quitter le programme
                System.exit(1);
                break;
            default://gestion des locataires
                menu1();
        }
    }

    /**
     *menu de gestion des locataires, permet de choisir entre : Ajouter, modifier ou supprimer un locataire; afficher la liste des locataire par ordre alphabétique; afficher la liste des locataires ayant loué un type de bien pariculier; rechercher le liste des locations d'un locataire particulier; retour
     * @throws IOException
     */
    void menu1() throws IOException {
        System.out.println("\n----------------------\nGestion des locataires\n----------------------\n1) Ajouter, modifier ou supprimer un locataire (defaut)\n2) afficher la liste des locataire par ordre alphabétique\n3) afficher la liste des locataires ayant loué un type de bien pariculier\n4) rechercher le liste des locations d'un locataire particulier\n0) retour");
        switch (scan.nextInt()) {
            case 2://afficher la liste des locataire par ordre alphabétique
                System.out.print(base.afficherAlphaLoc());
                menu1();
                break;
            case 3://afficher la liste des locataires ayant loué un type de bien pariculier
                System.out.println("afficher la liste des locataires ayant loué un type de bien pariculier");
                System.out.print("indice du type de bien a rechercher : ");
                indice = scan.nextInt();
                base.afficherListeLocataireLoueType(indice);
                menu1();
                break;
            case 4://rechercher le liste des locations d'un locataire particulier
                System.out.print("id du type de bien a rechercher");
                int idLoc = scan.nextInt();
                base.afficherListeLocPart(idLoc);
                menu5();
                break;
            case 0://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un locataire
                menu11();
        }
    }

    /**
     *menu de gestion des biens, permet de choisir entre : Ajouter, modifier ou supprimer un bien; afficher la liste des biens par ordre alphabétique; afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu; retour
     * @throws IOException
     */
    void menu2() throws IOException {
        System.out.println("\n-----------------\nGestion des biens\n-----------------\n1) Ajouter, modifier ou supprimer un bien (defaut)\n2) afficher la liste des biens par ordre alphabétique\n3) afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu\n0) retour");
        switch (scan.nextInt()) {
            case 2://afficher la liste des biens par ordre alphabétique
                base.afficherAlphaBien();
                menu2();
                break;
            case 3://afficher la liste des biens d'un type particulier pariculier avec le nom du locataire s'il y a lieu
                base.afficherType();
                System.out.print("Id du type : ");
                int idType = scan.nextInt();
                System.out.println(base.afficherBienParType(idType, true));
                menu2();
                break;
            case 0://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un bien
                menu21();
        }
    }

    /**
     *menu de gestion des types de biens, permet de choisir entre : Ajouter, modifier ou supprimer un type de bien; afficher la liste des types de biens; retour
     * @throws IOException
     */
    void menu3() throws IOException {
        System.out.println("\n--------------------------\nGestion des types de biens\n--------------------------\n1) Ajouter, modifier ou supprimer un type de bien (defaut)\n2) afficher la liste des types de biens\n0) retour");
        switch (scan.nextInt()) {
            case 2://afficher la liste des types de biens
                base.afficherType();
                menu3();
                break;
            case 0://retour
                menuPrincipal();
                break;
            default://Ajouter, modifier ou supprimer un type de bien
                menu31();
        }
    }

    /**
     *menu de gestion des locations, permet de choisir entre : Louer un bien; libérer un bien; afficher la liste des biens loués; afficher la liste des locataires de biens; afficher la liste des locataires ayant au moin un bien en cours de location; retour
     * @throws IOException
     */
    void menu4() throws IOException {
        System.out.println("\n---------------------\nGestion des locations\n---------------------\n1) Louer un bien (defaut)\n2) libérer un bien\n3) afficher la liste des biens loués\n4) afficher la liste des locataires de biens\n5) afficher la liste des locataires ayant au moin un bien en cours de location\n0) retour");
        switch (scan.nextInt()) {
            case 2://libérer un bien
                base.libBien();
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
            case 0://retour
                menuPrincipal();
                break;
            default://Louer un bien
                base.loueBien();
                menu4();
        }
    }

    /**
     *menu de sauvegarde/restauration, permet de choisir entre : Sauvegarde des données dans un ou plusieurs fichiers binaires, restauration des données dans les structures choisies à partir des fichiers de sauvegarde, retour
     * @throws IOException
     */
    void menu5() throws IOException {
        System.out.println("\n------------------------------------------------------\nsauvegarde/restauration des données\n------------------------------------------------------\n1) Sauvegarde (defaut)\n2) Restauration\n0) retour");
        switch (scan.nextInt()) {
            case 2://restauration des données dans les structures choisies à partir des fichiers de sauvegarde
                chargement();
                menuPrincipal();
                break;
            case 0://retour
                menuPrincipal();
                break;
            default://Sauvegarde des données dans un ou plusieurs fichiers binaires
                sauvegarde();
                menuPrincipal();
        }
    }

    /**
     *sous-menu Ajouter, modifier ou supprimer un locataire, permet de choisire entre : Ajouter un locataire; Modifier un locataire; Supprimer un locataire; retour
     * @throws IOException
     */
    void menu11() throws IOException {
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un locataire\n-----------------------------------------------------\n1) Ajouter un locataire (defaut)\n2) Modifier un locataire\n3) Supprimer un locataire\n0)retour");

        switch (scan.nextInt()) {
            case 2://Modifier un locataire
                System.out.println("Modifier un locataire");
                System.out.print("id du locataire a modifier : ");
                int idLoc = scan.nextInt();
                if (base.idLocValide(idLoc)) base.modifierLocataire(idLoc);
                menu11();
                break;
            case 3://Supprimer un locataire
                System.out.println("Supprimer un locataire");
                System.out.print("ID du locataire a supprimer : ");
                idLoc = scan.nextInt();
                if (base.idLocValide(idLoc)) base.supprimerLocataire(base.rechCaseIdLoc(idLoc));
                menu11();
                break;
            case 0://retour
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

    /**
     *sous-menu Ajouter, modifier ou supprimer un bien, permet de choisire entre : Ajouter un bien; Modifier un bien; Supprimer un bien; retour
     * @throws IOException
     */
    void menu21() throws IOException {
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un bien\n-----------------------------------------------------\n1) Ajouter un bien (defaut)\n2) Modifier un bien\n3) Supprimer un bien\n0)retour");

        switch (scan.nextInt()) {
            case 2://Modifier un bien
                System.out.println("Modifier un bien : ");
                System.out.print("id du bien a modifier : ");
                int idBien = scan.nextInt();
                if (base.idBienValide(idBien)) base.modifierBien(idBien);
                menu21();
                break;
            case 3://Supprimer un bien
                System.out.println("Supprimer un bien");
                System.out.print("id du bien a supprimer : ");
                idBien = scan.nextInt();
                if (base.idBienValide(idBien)) base.supprimerBien(base.rechCaseIdBien(idBien));
                menu21();
                break;
            case 0://retour
                menu2();
                break;
            default://Ajouter un bien
                System.out.println("Ajouter un bien");
                System.out.print("adresse du bien : ");
                scan.nextLine();
                String adresseBien = scan.nextLine();
                System.out.print("etat du bien : ");
                String etatBien = scan.nextLine();
                System.out.print("id du type de bien correspondant : ");
                int type = scan.nextInt();
                System.out.println("Montant du loyer : ");
                int loyerBien = scan.nextInt();
                base.ajouterBien(type, adresseBien, etatBien, loyerBien);
                menu21();
        }
    }

    /**
     *sous-menu Ajouter, modifier ou supprimer un type de bien, permet de choisire entre : Ajouter un bien; Modifier un type de bien; Supprimer un type de bien; retour
     * @throws IOException
     */
    void menu31() throws IOException {
        System.out.println("\n-----------------------------------------------------\nAjouter, modifier ou supprimer un type de bien\n-----------------------------------------------------\n1) Ajouter un type de bien (defaut)\n2) Modifier un type de bien\n3) Supprimer un type de bien \n0)retour");

        switch (scan.nextInt()) {
            case 2://Modifier un type de bien
                System.out.println("Modifier un type de bien");
                System.out.print("Id du type de bien a modifier : ");
                int idType = scan.nextInt();
                if (base.idTypeValide(idType)) base.modifierType(idType);
                menu31();
                break;
            case 3://Supprimer un type de bien 
                System.out.println("Supprimer un type de bien");
                System.out.print("id du Type a supprimer : ");
                idType = scan.nextInt();
                if (base.idTypeValide(idType)) base.supprimerType(base.rechCaseIdType(idType));
                menu31();
                break;
            case 0://retour
                menu3();
                break;
            default://Ajouter un type de bien 
                System.out.println("Ajouter un type de bien");
                System.out.print("nom du type de bien : ");
                scan.nextLine();
                String nomType = scan.nextLine();
                base.ajouterTypeBien(nomType);
                menu31();
        }
    }

    /**
     *main du projet, lance le progamme en appelant le menu principal du projet
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Menu monMenu = new Menu();
        /*BaseDeDonnees base=new BaseDeDonnees;
        GestionFichier fich=new Gestion fichier;*/
        monMenu.menuPrincipal();

    }
}

