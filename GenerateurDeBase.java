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
     * Tableau contenant 12 nom de type de biens
     */
    String typesBien[] = {"Maison",
            "Appartement T1",
            "Appartement T2",
            "Appartement T3",
            "Appartement T4",
            "Appartement T5",
            "Garage","Loft",
            "Maison de maître",
            "Hotel particulié",
            "Bureaux",
            "Commerce",
            "Studio"};

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
     * Méthode qui génére un type de bien à partir du tableau "typesBien"
     * @return
     *  un type de bien
     */
    String typeAleatoire(){
        int i = generateurNombres(0, 11);
        return typesBien[i];
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

//    Remplissage de la base

    String locataires(BaseDeDonnees base, int nbLocataires){
        for (int i = 0 ; i < nbLocataires ; i++) {
            String nom = nomAleatoire();
            String prenom = prenomAleatoire();
            String adresse = adresseAleatoire();
            String telephone = telephoneAleatoire();

            base.ajouterLocataire(nom, prenom, adresse, telephone);
        }

        return "La base locataire est correctement remplie";
    }


    public static void main(String[] args) throws IOException{
        GenerateurDeBase generateur = new GenerateurDeBase();
        BaseDeDonnees base = new BaseDeDonnees();

        System.out.println(generateur.locataires(base, 500));
        System.out.println(base.afficherLocataire(499));
    }
}
