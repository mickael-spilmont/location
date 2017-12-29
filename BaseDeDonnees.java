/**
 * <b></>Cette classe est la base données du programme, elle contient à la fois les données stockées sous forme de
 * tableau et les outils permettant d'exploiter celles-ci</b>
 * @author Duhamel Alexandre ; Spilmont Mickael
 */

class BaseDeDonnees {
//    base locataires

    /**
     * Cles primaires identifiant les locataires
     */
    int idLocataire[] = new int [500];

    /**
     * tableau contenant dans l'ordre :
     *  0 - le nom
     *  1 - le prenom
     *  2 - l'adresse
     *  3 - le numero de téléphone
     * du locataire
     */
    String donneesLocataire[][] = new String [500][4];

    /**
     * Tableau de deux dimmentions contenant jusqu'à 5 identifiants de bien loués par le locataire
     */
    int idBienLocataire[][] = new int [500][5];

//    base type

    /**
     * Cles primaires identifiant les types de biens
     */
    int idType[] = new int [20];

    /**
     * Noms des types de biens
     */
    String nomType[] = new String [20];

//    base biens

    /**
     * Tableau contenant les cles primaire et étrangères des biens dans cet ordre :
     *  0 - cle primaire du bien
     *  1 - cle etrangere du type de bien
     *  2 - cle etrangere du locataire
     */
    int clesBien[][] = new int [100][3];

    /**
     * Tableau contenant les données de type chaine de caractère du bien, dans cet ordre :
     *  0 - adresse du bien
     *  1 -  etat du bien
     */
    String donneesBien[][] = new String[100][2];

    /**
     * Montant du loyer du bien
     */
    double loyerBien[] = new double [100];

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
     * @param adresse
     * l'adresse du locataire
     * @param telephone
     * le numéro de téléphone du locataire
     */
    void ajouterLocataire(String nom, String prenom, String adresse, String telephone){
        compteurLocataire[0] ++;
        compteurLocataire[1] ++;
        int numCase = compteurLocataire[0];

        idLocataire[numCase] = compteurLocataire[1];
        donneesLocataire[numCase][0] = nom;
        donneesLocataire[numCase][1] = prenom;
        donneesLocataire[numCase][2] = adresse;
        donneesLocataire[numCase][3] = telephone;
    }

    /**
     * Méthode permetant l'ajout d'un type de bien
     * @param nom
     * le nom du type de bien ex : villa, appartement...
     */
    void ajouterTypeBien(String nom){
        compteurType[0] ++;
        compteurType[1] ++;
        int numCase = compteurType[0];

        idType[numCase] = compteurType[1];
        nomType[numCase] = nom;
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
    void ajouterBien(int type, String adresse, String etat){
        compteurBien[0] ++;
        compteurBien[1] ++;
        int numCase = compteurBien[1];

        clesBien[numCase][0] = compteurBien[1];
        clesBien[numCase][1] = type;
        donneesBien[numCase][0] = adresse;
        donneesBien[numCase][1] = etat;
    }

//    Méthodes de suppression

    /**
     * Méthode permettant la supression d'un locataire désigné par son emplacement dans le tableau.
     * La méthode remplace le locataire à supprimer par le dernier locataire du tableau, ainsi le trou dans la base de
     * données est comblé.
     * @param numCase
     * numéro de la case du tableau contenant du locataire concerné
     */
    void supprimerLocataire(int numCase){

        int dernierLoacataire = compteurLocataire[0];
        compteurLocataire[0] --;

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
    }

//    /**
//     * Méthode permettant la supression d'un locataire désigné par son emplacement dans le tableau, par compactage de
//     * la base de donnée, en décalant tout les locataires d'un cran vers la gauche du tableau.
//     * Si le locataire désigné est le dernier, dans ce cas ces données sont simplement remisent à 0 ou null.
//     * @param numCase
//     * numéro de la case du tableau contenant du locataire concerné
//     */
//    void supprimerLocataire(int numCase){
//        // Vérifie si le locataire est le dernier
//        if (numCase == 499){
//            idLocataire[numCase] = 0;
//            for (int i = 0 ; i < 4 ; i++){
//                donneesLocataire[numCase][i] = null;
//            }
//            for (int j = 0; j < 5; j++){
//                idBienLocataire[numCase][j] = 0;
//            }
//        }
//        // Si le locataire n'est pas le dernier, lance la supression par compactage de la base
//        else{
//            for (int i = numCase ; i <= compteurLocataire[0] ; i++){
//                idLocataire[i] = idLocataire[i +1];
//                for (int j = 0 ; j < 4 ; j++){
//                    donneesLocataire[i][j] = donneesLocataire[i +1][j];
//                }
//                for (int k = 0 ; k < 5 ; k++){
//                    idBienLocataire[i][k] = idBienLocataire[i +1][k];
//                }
//            }
//        }
//    }


//    Méthodes de requêtes

    /**
     * Affiche les info du locataire ciblé
     * @param numCase
     * numéro de la case du tableau contenant du locataire concerné
     * @return
     * retourne la fiche du locataire sous forme de chaine de caractères
     */
    String afficherLocataire(int numCase){
        String resultat = "";

        resultat += "ID : " + idLocataire[numCase] + "\n";
        resultat += "Nom : " + donneesLocataire[numCase][0] + "\n";
        resultat += "Prenom : " + donneesLocataire[numCase][1] + "\n";
        resultat += "Adresse : " + donneesLocataire[numCase][2] + "\n";
        resultat += "Téléphone : " + donneesLocataire[numCase][3] + "\n";

        return resultat;
    }

//    Main

    /**
     * Permet d'effectuer des tests sur la classe BaseDeDonnees afin de s'assurer de son bon fonctionnement
     * @param args
     */
    public static void main(String[] args){
        BaseDeDonnees base = new BaseDeDonnees();

        System.out.println("Ajout de 3 locataires");
        String nomLocataire = "Smith";
        String prenomLocataire = "Jhon";
        String adresseLocataire = "13 Bvd Louis XIV";
        String telephoneLocataire = "0658632459";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        nomLocataire = "Nolan";
        prenomLocataire = "Victor";
        adresseLocataire = "14 rue Charle V";
        telephoneLocataire = "0656841236";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        nomLocataire = "Laren";
        prenomLocataire = "Victoria";
        adresseLocataire = "14 rue du moulin";
        telephoneLocataire = "0658632145";

        base.ajouterLocataire(nomLocataire, prenomLocataire, adresseLocataire, telephoneLocataire);

        System.out.println(base.afficherLocataire(0));
        System.out.println(base.afficherLocataire(1));
        System.out.println(base.afficherLocataire(2));

        System.out.println("Supression du locataire ID 2");

        base.supprimerLocataire(2);

        System.out.println(base.afficherLocataire(0));
        System.out.println(base.afficherLocataire(1));
        System.out.println(base.afficherLocataire(2));
    }
}