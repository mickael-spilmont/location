import java.io.*;
import java.util.Random;

public class GenerateurDeBase {

    /**
     * Objet de type Random servant à la génération de nombres aléatoires
     */
    Random aleatoire = new Random();

    /**
     * Tableau contenant dans cet ordre 500 noms et présnoms
     */
    String nomsPrenoms[][] = new String[500][2];

    /**
     * Tableau contanant 230 nom de rue
     */
    String rues[] = new String[235];

    /**
     * Tableau contenant les débuts des numéro de téléphones sous forme de string
     */
    String debutTelephones[] = {"03", "09", "06", "07"};

    /**
     * Tableau contanant 10 code postaux et la ville correspondante
     */
    String villes[] = {"59000 Lille",
            "59100 Roubaix",
            "59200 Tourcoing",
            "59650 Villeneuve-d'Ascq",
            "59700 Marcq-en-Barœul",
            "59130 Lambersart",
            "59110 La Madeleine",
            "59510 Hem",
            "59790 Ronchin",
            "59370 Mons-en-Barœul"};

    /**
     * Tableau contenant les différents états que peux avoir un bien
     */
    String etatsBien[] = {"Neuf",
            "À rafraîchir",
            "Traveaux à prévoir",
            "Mauvais état"};

    /**
     * Tableau contenant 14 noms de type de biens
     */
    String typesBien[] = {"Garage",
            "studio",
            "Appartement T1",
            "Appartement T2",
            "Appartement T3",
            "Appartement T4",
            "Appartement T5",
            "Maison",
            "Loft",
            "Bureaux",
            "Entrepôt",
            "Maison de maître",
            "Commerce",
            "Hotel particulier"};

    /**
     * Constructeur de la classe.
     * Lit le fichier "donnees/donees.bin" et remplis les tableaux nomsPrenoms et rues, qui contiennent trop de données
     * pour être contenue dans le programme.
     * Il commence par lire les 500 premières lignes du fichiers qui correspondent aux noms et prénoms séparés par un
     * point virgule, puis les 230 suivantes qui corespondent aux noms de rue.
     * @throws IOException
     */
    GenerateurDeBase() throws IOException{
        String ligne;
        String tabLigne[];
        BufferedReader br;

        br = new BufferedReader(new FileReader("donees/donees.bin"));

//        lescture des noms et prénoms
        for (int i = 0 ; i < 500 ; i++){
            ligne = br.readLine();
            tabLigne = ligne.split(";");
            for (int j = 0 ; j < 2 ; j++ ){
                nomsPrenoms[i][j] = tabLigne[j];
            }
        }

//        lecture des noms de rue
        for (int k = 0 ; k < 235 ; k++){
            rues[k] = br.readLine();
        }
    }

//    Erreur étrange demander à M Atamenia
//    GenerateurDeBase() throws IOException{
//        String ligne;
//        BufferedReader br;
//
//        br = new BufferedReader(new FileReader("donees/donees.bin"));
//
//        for (int i = 0 ; i < 500 ; i++){
//
//            ligne = br.readLine();
//            int j = 0;
//            for (String str : ligne.split(";")){
//                nomsPrenoms[i][j] = str;
//                j++;
//            }
//        }
//
//        for (int k = 0 ; k < 235 ; k++){
//            rues[k] = br.readLine();
//        }
//    }


//    Génération aléatoire (regroupe toute les méthodes relatives à la génération alétoire de données)

    /**
     * Méthode retournant des nombres aléatoires compris dans une fourchette définie par l'utilisateur
     * @param debut
     *  Début de la fourchette
     * @param fin
     *  Fin de la fourcehette
     * @return
     *  Un nombre aléatoire
     */
    int generateurNombres(int debut, int fin){
        return aleatoire.nextInt(fin + 1) + debut;
    }

    /**
     * Méthode qui génère aléatoirement un numéroo de téléphone complet, en commençant par choisir le premier chhiffre
     * dans le tableau "debutTelephones"
     * @return
     *  Le numéro complet sous forme de string
     */
    String telephoneAleatoire(){
        String telephone = "";

        int i = generateurNombres(0, 3);
        telephone += debutTelephones[i];

        for (int j = 0 ; j < 4 ; j++){
            telephone += generateurNombres(10, 99);
        }

        return telephone;
    }

    /**
     * Méthode quio assemble une adresse complète à partir d'un numero aléatoire, puis d'une case aléatoire des tableaux
     * rues et villes
     * @return
     *  Une adresse complette
     */
    String adresseAleatoire(){
        String adresse = "";

        int numero = generateurNombres(1, 99);
        adresse += numero;

        int i = generateurNombres(0, 234);
        adresse += " " + rues[i];

        int j = generateurNombres(0, 9);
        adresse += " " + villes[j];

        return adresse;
    }

    /**
     * Méthode qui génère un nom aléatoire en se basant sur le tableau "nomsPrenoms"
     * @return
     *  Un nom
     */
    String nomAleatoire(){
        int i = generateurNombres(0, 499);
        return nomsPrenoms[i][0];
    }

    /**
     * Méthode qui génère un prénom aléatoire en se basant sur le tableau "nomsPrenoms"
     * @return
     *  Un prénom
     */
    String prenomAleatoire(){
        int i = generateurNombres(0, 499);
        return nomsPrenoms[i][1];
    }

    /**
     * Méthode qui retourne un nombre aléatoire correspondant à une clé de type de bien
     * @return
     *  un chiffre de 1 à 14
     */
    int typeAleatoire(){
        return generateurNombres(1, 14);
    }

    /**
     * Méthode qui génère un état à partir du tableau "etatsbien"
     * @return
     *  un état
     */
    String etatAleatoire(){
        int i = generateurNombres(0, 3);
        return etatsBien[i];
    }

    /**
     * Méthode qui génère un loyer aléatoire au montant basé sur le type de bien
     * @param type
     *  la clé correspondant au type de bien
     * @return
     *  le montant du loyer
     */
    int loyerAleatoire(int type){
        switch(type){
            case 0: // garage
                return generateurNombres(30, 80);
            case 1: // studio
                return generateurNombres(300, 550);
            case 2: // T1
                return generateurNombres(400, 650);
            case 3: // T2
                return generateurNombres(500, 750);
            case 4: // T3
                return generateurNombres(600, 850);
            case 5: // T4
                return generateurNombres(700, 950);
            case 6: // T5
                return generateurNombres(800, 1050);
            case 7: // maison
                return generateurNombres(600, 1050);
            case 8: // loft
                return generateurNombres(900, 1200);
            case 9: // bureaux
                return generateurNombres(800, 1800);
            case 10: // entrepôt
                return generateurNombres(1000, 2000);
            case 11: // maison de maître
                return generateurNombres(1200, 2000);
            default: // hotel particulier
                return generateurNombres(4000, 10000);
        }
    }

//    Remplissage de la base

    String types(BaseDeDonnees base){
        for (int i = 0 ; i < typesBien.length ; i++){
            String nom = typesBien[i];
            base.ajouterTypeBien(nom);
        }
        return "La base types de biens est correctement remplie";
    }

    String locataires(BaseDeDonnees base, int nbLocataires){
        for (int i = 0 ; i < nbLocataires ; i++) {
            String nom = nomAleatoire();
            String prenom = prenomAleatoire();
            String adresse = adresseAleatoire();
            String telephone = telephoneAleatoire();

            base.ajouterLocataire(nom, prenom, adresse, telephone);
        }

        return "La base locataires est correctement remplie";
    }

    String biens(BaseDeDonnees base, int nbBiens){
        for (int i = 0 ; i < nbBiens ; i++) {
            int type = typeAleatoire();
            String adresse = adresseAleatoire();
            String etat = etatAleatoire();
            int loyer = loyerAleatoire(type);

            base.ajouterBien(type, adresse, etat, loyer);
        }
        return "La base biens est correctement remplie";
    }


    public static void main(String[] args) throws IOException{
        GenerateurDeBase generateur = new GenerateurDeBase();
        BaseDeDonnees base = new BaseDeDonnees();

        System.out.println(generateur.locataires(base, 500));
        System.out.println(base.afficherLocataire(499));

        System.out.println(generateur.biens(base, 100));
        System.out.println(base.donneesBien[0]);
    }
}
