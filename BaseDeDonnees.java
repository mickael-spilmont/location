/**
 * <b></>Cette classe est la base données du programme, elle contient à la fois les données stockées sous forme de
 * tableau et les outils permettant d'exploiter celles-ci</b>
 *
 * @author Duhamel Alexandre ; Spilmont Mickael
 */

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class BaseDeDonnees {
    String s = "";
    Scanner scan = new Scanner(System.in);
//    base locataires

    /**
     * Cles primaires identifiant les locataires
     */
    int idLocataire[] = new int[500];

    /**
     * tableau contenant dans l'ordre :
     *  0 - le nom
     *  1 - le prenom
     *  2 - l'adresse
     *  3 - le numero de téléphone
     * du locataire
     */
    String donneesLocataire[][] = new String[500][4];

    /**
     * Tableau de deux dimmentions contenant jusqu'à 5 identifiants de bien loués par le locataire
     */
    int idBienLocataire[][] = new int[500][5];

//    base type

    /**
     * Cles primaires identifiant les types de biens
     */
    int idType[] = new int[20];

    /**
     * Noms des types de biens
     */
    String nomType[] = new String[20];

//    base biens

    /**
     * Tableau contenant les cles primaire et étrangères des biens dans cet ordre :
     *  0 - cle primaire du bien
     *  1 - cle etrangere du type de bien
     *  2 - cle etrangere du locataire
     */
    int clesBien[][] = new int[100][3];

    /**
     * Tableau contenant les données de type chaine de caractère du bien, dans cet ordre :
     *  0 - adresse du bien
     *  1 - etat du bien
     */
    String donneesBien[][] = new String[100][2];

    /**
     * Montant du loyer du bien
     */
    int loyerBien[] = new int[100];

//    compteurID

    /**
     * Tableau avec l'emplacement du dernier indice contenant des données, puis le plus grand ID locataire
     */
    int compteurLocataire[] = {-1, 0};

    /**
     * Tableau avec l'emplacement du dernier indice contenant des données, puis le plus grand ID type
     */
    int compteurType[] = {-1, 0};

    /**
     * Tableau avec l'emplacement du dernier indice contenant des données, puis le plus grand ID bien
     */
    int compteurBien[] = {-1, 0};

//    Méthodes d'ajout

    /**
     * Métode permetant de créer un nouveau locataire
     * @param nom
     * Le nom du locataire
     * @param prenom
     * Le prenom du locataire
     * @param adresse
     * l'adresse du locataire
     * @param telephone
     * le numéro de téléphone du locataire
     */
    void ajouterLocataire(String nom, String prenom, String adresse, String telephone) {
        if (compteurLocataire[0] + 1 < idLocataire.length) {
            compteurLocataire[0]++;
            compteurLocataire[1]++;
            int numCase = compteurLocataire[0];

            idLocataire[numCase] = compteurLocataire[1];
            donneesLocataire[numCase][0] = nom;
            donneesLocataire[numCase][1] = prenom;
            donneesLocataire[numCase][2] = adresse;
            donneesLocataire[numCase][3] = telephone;
            System.out.println("nouveau locataire ajoute");
        } else System.out.println("base des locataires pleine");
    }

    /**
     * Méthode permetant l'ajout d'un type de bien
     * @param nom
     * le nom du type de bien ex : villa, appartement...
     */
    void ajouterTypeBien(String nom) {
        if (compteurType[0] + 1 < idType.length) {
            compteurType[0]++;
            compteurType[1]++;
            int numCase = compteurType[0];

            idType[numCase] = compteurType[1];
            nomType[numCase] = nom;
        } else System.out.println("base des types pleine");
    }

    /**
     * Méthode permetant l'ajout d'un bien
     * @param type
     * la clef étrangère correspondant au type du bien
     * @param adresse
     * l'adresse du bien
     * @param etat
     * l'état du bien ex : neuf, a rafraichir...
     */
    void ajouterBien(int type, String adresse, String etat, int loyer) {
        if (compteurBien[0] + 1 < clesBien.length) {
            compteurBien[0]++;
            compteurBien[1]++;
            int numCase = compteurBien[0];

            clesBien[numCase][0] = compteurBien[1];
            clesBien[numCase][1] = type;
            donneesBien[numCase][0] = adresse;
            donneesBien[numCase][1] = etat;
            loyerBien[numCase] = loyer;
        } else System.out.println("base des Biens pleine");
    }

//    Méthodes de suppression

    /**
     * Méthode permettant la supression d'un locataire désigné par son emplacement dans le tableau.
     * La méthode remplace le locataire à supprimer par le dernier locataire du tableau, ainsi le trou dans la base de
     * données est comblé.
     * @param numCase
     * numéro de la case du tableau contenant du locataire concerné
     */
    void supprimerLocataire(int numCase) {

        int dernierLoacataire = compteurLocataire[0];
        compteurLocataire[0]--;

        idLocataire[numCase] = idLocataire[dernierLoacataire];
        idLocataire[dernierLoacataire] = 0;

        for (int i = 0; i < 4; i++) {
            donneesLocataire[numCase][i] = donneesLocataire[dernierLoacataire][i];
            donneesLocataire[dernierLoacataire][i] = null;
        }
        for (int j = 0; j < 5; j++) {
            idBienLocataire[numCase][j] = idBienLocataire[dernierLoacataire][j];
            idBienLocataire[dernierLoacataire][j] = 0;
        }
        System.out.println("locataire supprime");
    }

    /**
     * Méthode qui permet de supprimer le bien désigne par son nuéro de case rentré en paramètre, elle vérifie si le bien
     * est en location et empêche sa suppression le cas échéant
     * @param numCase
     *  Numéro de la case du bien
     */
    void supprimerBien(int numCase) {
        if (clesBien[numCase][2] == 0) {
            int dernierBien = compteurBien[0];
            compteurBien[0]--;

            clesBien[numCase][0] = clesBien[dernierBien][0];
            clesBien[numCase][1] = clesBien[dernierBien][1];
            clesBien[numCase][2] = clesBien[dernierBien][2];
            clesBien[dernierBien][0] = 0;
            clesBien[dernierBien][1] = 0;
            clesBien[dernierBien][2] = 0;

            for (int i = 0; i < 2; i++) {
                donneesBien[numCase][i] = donneesBien[dernierBien][i];
                donneesBien[dernierBien][i] = null;
            }

            loyerBien[numCase] = loyerBien[dernierBien];
            loyerBien[dernierBien] = 0;
        } else System.out.println("suppression impossible, le bien est en location");
    }

    /**
     * Méthode qui permet de supprimer un type de bien désigné par son numéro de case
     * @param numCase
     *  numéro de case du type de bien
     */
    void supprimerType(int numCase) {

        int dernierType = compteurType[0];
        compteurType[0]--;

        idType[numCase] = idType[dernierType];
        idType[dernierType] = 0;
        nomType[numCase] = nomType[dernierType];
        nomType[dernierType] = null;
    }

//    Méthodes de requêtes

    /**
     * Méthode qui permet de formatage de l'Id entré sous forme de de chaine, afin d'alligner tout les id lors de
     * l'affichage
     * @param id
     *  Un Id sous forme de chaine
     * @return
     *  Le même Id formaté
     */
    String formatageId(String id){
        if (id.length() < 2){
            id = "  " + id;
        }
        else if (id.length() < 3){
            id = " " + id;
        }
        return id;
    }

    /**
     * Méthode qui vérifie si une chaine ne dépasse pas 12 caractères, dans le cas contraire elle ne garde que les 9
     * premiers caractères et place "..." à la fin.
     * Si la chaine est plus petite que 12 caractères elle comble avec des espaces
     * Elle est utilisé pour formater les nom prénom et état des bien avant affichage
     * @param chaine
     *  La chaine à formater
     * @return
     *  La chaine fixée à 12 caractères
     */
    String formatageCourt(String chaine){
        int taille = chaine.length();

        if (taille > 12){
            chaine = chaine.substring(0, 9) + "...";
        }
        else if (taille < 12){
            while (taille < 12){
                chaine += " ";
                taille = chaine.length();
            }
        }
        return chaine;
    }

    /**
     * Méthode qui vérifie si une chaine ne dépasse pas 51 caractères, dans le cas contraire elle ne garde que les 24
     * premiers et 24 dernier, puis elle place "..." au millieu.
     * Elle est utilisé pour formater les adresse avant affichage
     * @param chaine
     * @return
     */
    String formatageLong(String chaine){
        int taille = chaine.length();

        if (taille > 51){
            chaine = chaine.substring(0, 23) + "..." + chaine.substring(chaine.length() - 24);
        }
        else if (taille < 51){
            while (chaine.length() < 51){
                chaine += " ";
            }
        }
        return chaine;
    }

    String formatageLoyer(String loyer){
        if (loyer.length() < 4){
            loyer = " " + loyer + "€";
        }
        else{
            loyer += "€";
        }
        return loyer;
    }

    /**
     * Affiche les info du locataire ciblé
     * @param numCase
     * numéro de la case du tableau contenant du locataire concerné
     * @return
     * retourne la fiche du locataire sous forme de chaine de caractères
     */
    String afficherInfosLocataire(int numCase) {
        String infos = "";

        infos += "ID : " + idLocataire[numCase] + "\n";
        infos += "Nom : " + donneesLocataire[numCase][0] + "\n";
        infos += "Prenom : " + donneesLocataire[numCase][1] + "\n";
        infos += "Adresse : " + donneesLocataire[numCase][2] + "\n";
        infos += "Téléphone : " + donneesLocataire[numCase][3] + "\n";

        return infos;
    }

    /**
     * Affiche les infos détaillées du bien ciblé
     * @param numCase
     *  numéro de case du bien
     * @return
     *  Les infos concernant le bien sous forme de string
     */
    String afficherInfosBien(int numCase) {
        String infos = "";

        infos += "ID : " + clesBien[numCase][0] + "\t";
        infos += "Description : " + donneesBien[numCase][0] + "\t";
        infos += "Loyer : " + loyerBien[numCase] + "\t";
        infos += "Etat : " + donneesBien[numCase][1] + "\n";

        return infos;
    }


    /**
     * Affiche la liste des locataire ayant loué le type de bien passé en parametre
     * @param indiceType
     *  indice du type de bien
     * Affiche la liste directement sur le terminal
     */
    void afficherListeLocataireLoueType(int indiceType) {
        for (int i = 0; i <= clesBien[0].length; i++) {
            if (clesBien[i][1] == indiceType) {
                afficherInfosLocataire(clesBien[i][2]);
            }
        }
    }

    /**
     * Méthode qui affiche la liste des locataires de biens
     */
    void afficherListeLocBien() {
        for (int i = 0; i < idBienLocataire.length; i++) {
            if (idBienLocataire[i][0] != 0 || idBienLocataire[i][1] != 0 || idBienLocataire[i][2] != 0 || idBienLocataire[i][3] != 0 || idBienLocataire[i][4] != 0) {
                System.out.print(afficherInfosLocataire(i));
            }
        }
    }

    /**
     * Méthode qui affiche la liste des locataire louant un type de bien particulier
     * @param idLoc
     */
    void afficherListeLocPart(int idLoc) {
        int numCaseLoc = rechCaseIdLoc(idLoc);
        int numCaseBien = 0;
        for (int i = 0; i < 5; i++) {
            System.out.print(idBienLocataire[numCaseLoc][i]);
            numCaseBien = rechCaseIdBien(idBienLocataire[numCaseLoc][i]);
            System.out.print("\t" + donneesBien[numCaseBien][0]);
        }
    }

    /**
     * Affiche la liste des locataire par ordre alphabetique, ainsi que le totalité de leurs identifiants
     * Affiche la liste directement sur le terminal
     */
    String afficherAlphaLoc() {
        String data[][] = new String[(compteurLocataire[0]) + 1][5];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = donneesLocataire[i][0];
            data[i][1] = donneesLocataire[i][1];
            data[i][2] = donneesLocataire[i][2];
            data[i][3] = donneesLocataire[i][3];
            data[i][4] = Integer.toString(idLocataire[i]);
        }
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[0];
                final String time2 = entry2[0];
                return time1.compareTo(time2);
            }
        });
        //System.out.println("Id\tNom\tPrenom\tadresse\ttelephone");
        String resultat = "";
        for (final String[] s : data) {
            String id = formatageId(s[4]);
            String nom = formatageCourt(s[0]);
            String prenom = formatageCourt(s[1]);
            String adreresse = formatageLong(s[2]);
            String telephone = s[3];
            resultat += id + "\t" + nom + "\t" + prenom + "\t" + adreresse + "\t" + telephone + "\n";
        }
        return resultat;
    }

    /**
     * Affiche la liste des biens par ordre alphabétique
     * Elle commence par générer un tableau temporaire qui serras trié, celà évite de mélanger toute les information des
     * tableaux constituants la base
     */
    void afficherAlphaBien() {
        // Création du tableau temporaire
        String data[][] = new String[(compteurBien[0]) + 1][4];
        for (int i = 0; i < data.length; i++) {
            data[i][0]=nomType[rechCaseIdType(clesBien[i][1])];
            data[i][1] = donneesBien[i][0];
            data[i][2] = donneesBien[i][1];
            data[i][3] = Integer.toString(clesBien[i][0]);            
        }

        // le tableau temporaire est trié alphabétiquement par type
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[0];
                final String time2 = entry2[0];
                return time1.compareTo(time2);
            }
        });

        // affichage du tableau résultant
        System.out.println("type\tId\tadresse\tetat");
        for (final String[] s : data) {
            System.out.println(s[0] + "\t" + s[3] + "\t" + s[1] + "\t" + s[2]);
        }
    }

    /**
     * Affiche tous les types de bien
     */
    void afficherType() {
        System.out.println("id\tdesignation");
        for (int i = 0; i < compteurType[0] + 1; i++) {
            System.out.println(idType[i] + "\t" + nomType[i]);
        }
    }

    /**
     * Affiche tout les biens en cour de location
     */
    void afficherBienLoue() {
        System.out.println("Id\tadresse");
        for (int i = 0; i < clesBien.length; i++) {
            if (clesBien[i][2] != 0) {
                System.out.println(clesBien[i][0] + "\t" + donneesBien[i][0]);
            }
        }
    }

    String afficherBienParType(int idType, boolean afficherLocataire){
        String bienTrouve = "";
        String donnees = "";

        for (int i = 0 ;  i <= compteurBien[0] ; i++){
            if (clesBien[i][1] == idType) {
                String id = Integer.toString(clesBien[i][0]);
                id = formatageId(id);
                String adresse = formatageLong(donneesBien[i][0]);
                String etat = formatageCourt(donneesBien[i][1]);
                String loyer = Integer.toString(loyerBien[i]);
                loyer = formatageLoyer(loyer);

                donnees += id + "\t" + adresse + "\t" + etat + "\t" + loyer;

                if (afficherLocataire) {
                    donnees += "\tLocataire : ";
                    int cleLocataireBien = clesBien[i][2];
                    if (cleLocataireBien != 0) {
                        for (int j = 0; j <= compteurLocataire[0]; j++) {
                            if (cleLocataireBien == idLocataire[j]) {
                                String nom = formatageCourt(donneesLocataire[j][0]);
                                String prenom = formatageCourt(donneesLocataire[j][1]);
                                donnees += nom + "\t" + prenom + "\n";
                            }
                        }
                    }
                    else {
                        donnees += "aucun\n";
                    }
                }
                else{
                    donnees += "\n";
                }
            }
        }
        return donnees;
    }

//    Méthodes de modification

    /**
     * Méthode qui permet la modification des informations du locataire désigé par son identifiant passé en paramètre
     * @param id
     *  Id du locataire
     */
    void modifierLocataire(int id) {
        int numCase = rechCaseIdLoc(id);
        System.out.println("Modification du locataire (Laisser vide pour ne pas modifier)");
        System.out.print("nouveau nom du locataire : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesLocataire[numCase][0] = s;
        System.out.print("nouveau prenom du locataire : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesLocataire[numCase][1] = s;
        System.out.print("nouvelle adresse du locataire : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesLocataire[numCase][2] = s;
        System.out.print("nouveau numero de telephone du locataire : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesLocataire[numCase][3] = s;
        System.out.println("changements enregistés");
    }

    /**
     * Méthode qui permet la modification des informations du type désigé par son identifiant passé en paramètre
     * @param id
     *  id du type
     */
    void modifierType(int id) {
        System.out.println(rechCaseIdType(id));
        int numCase = rechCaseIdType(id);
        System.out.print("nouveau nom du Type : ");
        nomType[numCase] = scan.nextLine();
    }

    /**
     * Méthode qui permet la modification des informations du bien désigé par son identifiant passé en paramètre
     * @param id
     *  id du bien
     */
    void modifierBien(int id) {
        int numCase = rechCaseIdBien(id);
        System.out.println("Modification du bien (Laisser vide pour ne pas modifier)");
        System.out.print("modification de l'adresse : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesBien[numCase][0] = s;

        System.out.print("nouvel etat du bien : ");
        s = scan.nextLine();
        if (!s.equals("")) donneesBien[numCase][1] = s;

        System.out.print("nouveau loyer du bien : ");
        s = scan.nextLine();
        if (!s.equals("")) loyerBien[numCase] = Integer.parseInt(s);
    }

//     Méthode de recherche

    /**
     * Méthode qui permet de trouver l'emplacement d'un bien dans le tableau, à partir de son id
     * @param id
     *  id du bien
     * @return
     *  Indice du bien, ou -9 si non trouvé
     */
    int rechCaseIdBien(int id) {
        for (int i = 0; i < clesBien.length; i++) {
            if (id == clesBien[i][0]) return i;
        }
        return -9;//id non trouvé
    }

    /**
     * Méthode qui permet de trouver l'emplacement d'un type dans le tableau, à partir de son id
     * @param id
     *  id du type
     * @return
     *  Indice du type, ou -9 si non trouvé
     */
    int rechCaseIdType(int id) {
        for (int i = 0; i < idType.length; i++) {
            if (id == idType[i]) return i;
        }
        return -9;//id non trouvé
    }

    /**
     * Méthode qui permet de trouver l'emplacement d'un locataire dans le tableau, à partir de son id
     * @param id
     *  id du locataire
     * @return
     *  Indice du locataire, ou -9 si non trouvé
     */
    int rechCaseIdLoc(int id) {
        for (int i = 0; i < idLocataire.length; i++) {
            if (id == idLocataire[i]) return i;
        }
        return -9;//id non trouvé
    }

    /**
     * Méthode permettant de trouver un locataire grace à son nom et prenom.
     * Elle commence par créer un tableau de la taille du nombre d'utilisateur contenus dans la base, puis elle remplis
     * ce tableau avec les indices d'emplacements des locataire trouvés.
     * Elle appelle ensuite la méthode "compactageTableauRecherche" pour avoir un tableau qui fait uniquement la taille
     * Si aucun locataire n'est trouvé le tableau retourné est de taille 0
     * @param nomLocataire
     *  Le nom du locataire
     * @param prenomLocataire
     *  Le prénom du locataire
     * @return
     *  La liste des emplacements des locataires trouvés
     */
    String rechercheLocataireParNom(String nomLocataire, String prenomLocataire) {
        String locataireTrouve = "";

        for (int i = 0; i <= compteurLocataire[0]; i++) {
            if (donneesLocataire[i][0].equals(nomLocataire) || donneesLocataire[i][1].equals(prenomLocataire)) {
                String id = Integer.toString(idLocataire[i]);
                id = formatageId(id);
                String nom = formatageCourt(donneesLocataire[i][0]);
                String prenom = formatageCourt(donneesLocataire[i][1]);
                String adresse = formatageLong(donneesLocataire[i][2]);
                String telephone = donneesLocataire[i][3];
                locataireTrouve += id + "\t" + nom + "\t" + prenom + "\t" + adresse + "\t" + telephone + "\n";
            }
        }
        return locataireTrouve;
    }

//      methode de location

    /**
     * Méthode qui permet la mise en location d'un bien.
     * Elle commence par effectuer un filtrage des locataire par nom à l'aide de la méthode "rechercheLocataireParNom"
     * Ensuite le locataire désigné par l'utilisateur à l'aide de son id, est controllé afin de  afin de vérifier qu'il
     * ne loue pas déja 5 biens
     * Puis elle affiche la liste des type de bien afin d'éffectuer un nouveau filtrage par type de bien.
     * Enfin si le bien est disponible à la location, le traitement de la demande est effectué
     */
    void loueBien() {
        System.out.println("Entrez le nom et/ou prenom du locataire (si inconu laissez vide) : ");
        System.out.print("Nom : ");
        String nom = scan.nextLine();
        System.out.print("Prenom : ");
        String prenom = scan.nextLine();
        String locataireTrouve = rechercheLocataireParNom(nom, prenom);

        if (locataireTrouve.equals("")) {
            System.out.println("Aucun locataire trouvé");
            return;
        }
        else {
            System.out.println(locataireTrouve);
        }

        System.out.print("Id du locataire : ");
        int idLoc = scan.nextInt();
        int numCaseLoc = rechCaseIdLoc(idLoc);
        if (idLocValide(idLoc)) {
            if (idBienLocataire[numCaseLoc][4] == 0) {
                System.out.println("Quel type de bien souhaitez vous louer ?");
                afficherType();

                System.out.println("Entrez l'id du type recherché : ");
                int idTypeBien = scan.nextInt();
                String bienTrouve = afficherBienParType(idTypeBien, false);

                if (bienTrouve.equals("")){
                    System.out.println("Acun bien de ce type");
                    return;
                }
                else {
                    System.out.println(bienTrouve);
                }


                System.out.print("Id du bien a louer : ");
                int idBien = scan.nextInt();

                int numCaseBien = rechCaseIdBien(idBien);
                if (idBienValide(idBien)) {
                    clesBien[numCaseBien][2] = idLoc;
                    for (int i = 0; i < 5; i++) {
                        if (idBienLocataire[numCaseLoc][i] == 0) {
                            idBienLocataire[numCaseLoc][i] = idBien;
                            i = 5;
                        }
                    }
                    System.out.println(donneesLocataire[numCaseLoc][0] + " " + donneesLocataire[numCaseLoc][1] + "\nloue maintenant\n" + donneesBien[numCaseBien][0]);
                }
            } else System.out.print("ce locataire loue deja 5 biens");
        }
    }

    /**
     * Méthode permetant de libéré un bien de son locataire
     */
    void libBien() {
        afficherBienLoue();
        System.out.print("Id du bien a liberer : ");
        int idBien = scan.nextInt();
        int numCaseBien = rechCaseIdBien(idBien);
        if (idBienValide(idBien)) {
            int numCaseLoc = rechCaseIdLoc(clesBien[numCaseBien][2]);
            for (int i = 0; i < 5; i++) {
                if (idBienLocataire[numCaseLoc][i] == idBien) {
                    idBienLocataire[numCaseLoc][i] = 0;
                    i = 5;
                }
            }
            clesBien[numCaseBien][2] = 0;
        }
    }

    /**
     * Méthode qui permet la mise en location d'un bien en ajoutant les clé necessaire du coté bien et loataires
     * @param numCaseBien
     *  Emplacement du bien dans la base
     * @param numCaseLocataire
     *  Emplacement du locataire dans la base
     * @param emplacmentLibreLocataire
     *  Emplacement libre du tableau "idBienLocataire"
     * @return
     */
    String louerBienGenerateur(int numCaseBien, int numCaseLocataire, int emplacmentLibreLocataire) {
        clesBien[numCaseBien][2] = idLocataire[numCaseLocataire];
        idBienLocataire[numCaseLocataire][emplacmentLibreLocataire] = clesBien[numCaseBien][0];
        return "Le bien à correctement été mis en location";
    }

    /**
     * Méthode permettant de vérifier si un bien est en location
     * @param numCaseBien
     *  L'emplacement du bien dans la base
     * @return
     *  Un boolean de valeur true si le bien est loué et false dans le cas contraire
     */
    boolean estLoue(int numCaseBien) {
        return (clesBien[numCaseBien][2] != 0);
    }

    /**
     * Une méthode qui retourne le numéro d'un emplacement de location vide, et -1 dans le cas où le locataire a déja
     * 5 biens en location
     * @param numCaseLocataire
     *  L'emplacement du locataire dans la base
     * @return
     *  L'indice d'un emplacement vide ou -1 dans le cas contraire
     */
    int emplacementVideBienLocataire(int numCaseLocataire) {
        int emplacementVide = -1;
        int idBien = 0;

        for (int i = 0; i < 5; i++) {
            idBien = idBienLocataire[numCaseLocataire][i];
            if (idBien == 0) {
                emplacementVide = i;
            }
        }
        return emplacementVide;
    }

    /**
     * Méthode qui vérifie si un id de type est valide, et affiche "id du type non trouvé" si elle ne le trouve pas
     * @param idType
     *  L'id du type à valider
     * @return
     *  true si l'id est valide et false dans la cas contraire
     */
    boolean idTypeValide(int idType) {
        if (rechCaseIdType(idType) != -9) return true;
        else {
            System.out.println("id du type non trouvé");
            return false;
        }
    }

    /**
     * Méthode qui vérifie si un id de bien est valide, et affiche "id du bien non trouvé" si elle ne le trouve pas
     * @param idBien
     *  L'id du bien à valider
     * @return
     *  true si l'id est valide et false dans la cas contraire
     */
    boolean idBienValide(int idBien) {
        if (rechCaseIdBien(idBien) != -9) return true;
        else {
            System.out.println("id du bien non trouvé");
            return false;
        }
    }

    /**
     * Méthode qui vérifie si un id de locataire est valide, et affiche "id du locataire non trouvé" si elle ne le trouve pas
     * @param idLoc
     *  L'id du locataire à valider
     * @return
     *  true si l'id est valide et false dans la cas contraire
     */
    boolean idLocValide(int idLoc) {
        if (rechCaseIdLoc(idLoc) != -9) return true;
        else {
            System.out.println("id du locataire non trouvé");
            return false;
        }
    }
}
