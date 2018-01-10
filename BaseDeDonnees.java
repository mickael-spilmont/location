/**
 * <b></>Cette classe est la base données du programme, elle contient à la fois les données stockées sous forme de
 * tableau et les outils permettant d'exploiter celles-ci</b>
 *
 * @author Duhamel Alexandre ; Spilmont Mickael
 */

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
     * Affiche les info du locataire ciblé
     * @param numCase
     * numéro de la case du tableau contenant du locataire concerné
     * @return
     * retourne la fiche du locataire sous forme de chaine de caractères
     */
    String afficherInfosLocataire(int numCase) {
        String infos = "";

        infos += "ID : " + idLocataire[numCase] + "\t";
        infos += "Nom : " + donneesLocataire[numCase][0] + "\t";
        infos += "Prenom : " + donneesLocataire[numCase][1] + "\t";
        infos += "Adresse : " + donneesLocataire[numCase][2] + "\t";
        infos += "Téléphone : " + donneesLocataire[numCase][3] + "\n";

        return infos;
    }

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

    void afficherListeLocBien() {
        for (int i = 0; i < idBienLocataire.length; i++) {
            if (idBienLocataire[i][0] != 0 || idBienLocataire[i][1] != 0 || idBienLocataire[i][2] != 0 || idBienLocataire[i][3] != 0 || idBienLocataire[i][4] != 0) {
                System.out.print(afficherInfosLocataire(i));
            }
        }
    }

    void afficherListeLocPart(int idLoc) {
        int numCaseLoc = rechCaseIdLoc(idLoc);
        int numCaseBien = 0;
        for (int i = 0; i < 5; i++) {
            System.out.print(idBienLocataire[numCaseLoc][i]);
            numCaseBien = rechCaseIdBien(idBienLocataire[numCaseLoc][i]);
            System.out.print("\t" + donneesBien[numCaseBien][0]);
        }
    }

    void afficherListeTypeLoc(int idType) {
        System.out.println("Adresse\tlocataire");
        for (int i = 0; i < clesBien.length; i++) {
            if (clesBien[i][1] == idType) {
                System.out.println(donneesBien[i][0] + "\t" + donneesLocataire[rechCaseIdLoc(clesBien[i][2])][0]);
            }
        }
    }

    /**
     * Affiche la liste des locataire par ordre alphabetique, ainçi que le totalité de leurs identifiants
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
            resultat += ("Id : " + s[4] + "\tNom : " + s[0] + "\tPrenom : " + s[1] + "\tAdresse : " + s[2] + "\tTelephone : " + s[3] + "\n");
        }
        return resultat;
    }

    void afficherAlphaBien() {
        String data[][] = new String[(compteurBien[0]) + 1][3];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = donneesBien[i][0];
            data[i][1] = donneesBien[i][1];
            data[i][2] = Integer.toString(clesBien[i][0]);
            //data[i][3]=nomType[rechCaseIdType(clesBien[i][1])];
        }
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[0];
                final String time2 = entry2[0];
                return time1.compareTo(time2);
            }
        });
        System.out.println("adresse+\tId\tadresse\tetat");
        for (final String[] s : data) {
            System.out.println(s[2] + "\t" + s[0] + "\t" + s[1]);
        }
    }

    void afficherType() {
        System.out.println("id\tdesignation");
        for (int i = 0; i < compteurType[0] + 1; i++) {
            System.out.println(idType[i] + "\t" + nomType[i]);
        }
    }

    void afficherBienLoue() {
        System.out.println("Id\tadresse");
        for (int i = 0; i < clesBien.length; i++) {
            if (clesBien[i][2] != 0) {
                System.out.println(clesBien[i][0] + "\t" + donneesBien[i][0]);
            }
        }
    }

//    Méthodes de modification

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

    void modifierType(int id) {
        System.out.println(rechCaseIdType(id));
        int numCase = rechCaseIdType(id);
        System.out.print("nouveau nom du Type : ");
        nomType[numCase] = scan.nextLine();
    }

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

    int rechCaseIdBien(int id) {
        for (int i = 0; i < clesBien.length; i++) {
            if (id == clesBien[i][0]) return i;
        }
        return -9;//id non trouvé
    }

    int rechCaseIdType(int id) {
        for (int i = 0; i < idType.length; i++) {
            if (id == idType[i]) return i;
        }
        return -9;//id non trouvé
    }

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
     * @param nom
     *  Le nom du locataire
     * @param prenom
     *  Le prénom du locataire
     * @return
     *  La liste des emplacements des locataires trouvés
     */
    String rechercheLocataireParNom(String nom, String prenom) {
        String locatairesTrouves = "";

        for (int i = 0; i <= compteurLocataire[0]; i++) {
            if (donneesLocataire[i][0].equals(nom) || donneesLocataire[i][1].equals(prenom)) {
                locatairesTrouves += afficherInfosLocataire(i);
            }
        }

        if (locatairesTrouves.equals("")) {
            return "Aucun locataires trouvés";
        } else {
            return locatairesTrouves;
        }
    }


    String rechercheBien(String... args) {
        String bienTrouve = "";

        for (String mot : args) {

            for (int i = 0; i <= compteurBien[0]; i++) {
                String donnees[] = extraireDonneesBien(i);
                int nbMotCleTrouve = 0;

                for (String motCle : args) {
                    for (String donneeAComparer : donnees) {
                        if (mot.equals(donneeAComparer)) {
                            nbMotCleTrouve++;
                        }
                    }
                }

                if (nbMotCleTrouve == args.length) {
                    bienTrouve += afficherInfosBien(i);
                }
            }
        }

        if (bienTrouve.equals("")) {
            return "Aucun bien trouvé";
        } else {
            return bienTrouve;
        }
    }

    String[] extraireDonneesBien(int numCaseBien) {
        String adresseBien[] = donneesBien[numCaseBien][0].split(" ");

        String donnees[] = new String[adresseBien.length + 2];

        for (int i = 0; i < adresseBien.length; i++) {
            donnees[i] = adresseBien[i];
        }

        donnees[adresseBien.length] = nomType[clesBien[numCaseBien][1]];
        donnees[adresseBien.length + 1] = donneesBien[numCaseBien][1];

        return donnees;
    }

//      methode de location

    void loueBien() {
        System.out.print("Id du locataire : ");
        int idLoc = scan.nextInt();
        int numCaseLoc = rechCaseIdLoc(idLoc);
        if (idLocValide(idLoc)) {
            if (idBienLocataire[numCaseLoc][4] == 0) {
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

    void libBien() {
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
    String louerBien(int numCaseBien, int numCaseLocataire, int emplacmentLibreLocataire) {
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
    boolean loue(int numCaseBien) {
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

    boolean idTypeValide(int idType) {
        if (rechCaseIdType(idType) != -9) return true;
        else {
            System.out.println("id du type non trouvé");
            return false;
        }
    }

    boolean idBienValide(int idBien) {
        if (rechCaseIdBien(idBien) != -9) return true;
        else {
            System.out.println("id du bien non trouvé");
            return false;
        }
    }

    boolean idLocValide(int idLoc) {
        if (rechCaseIdLoc(idLoc) != -9) return true;
        else {
            System.out.println("id du locataire non trouvé");
            return false;
        }
    }

    public static void main(String[] args) {
    }
}
