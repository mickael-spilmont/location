import java.io.*;

/**
 * <b></>Permet la lecture/écriture de la base de donnée dans un fichier .bin mais aussi la manipulation des fichiers
 * et repertoires</b>
 * @author Spilmont Mickael
 */

class GestionFichier {
    /**
     * Contiens le chemin relatif du fichier ainsi que son nom sous la forme sauvegarde/nom_du_fichier.bin
     */
    String cheminFichier;

    /**
     * Constructeur de GestionFichier
     * @param nomFichier
     * Le nom du fichier indiqué par l'utilisateur
     */
    GestionFichier(String nomFichier){
        cheminFichier = "sauvegardes/" + nomFichier + ".bin";
    }

    /**
     * Méthode qui permet la sauvegarde de la base dans un fichier, les bases sont sauvegardées les une en dessous des
     * autres dans cet ordre : locataires, type de biens, biens.
     * Les données sont enregistrées ligne par ligne, par exemple pour la classe locataire on commence par enregistrer
     * toutes les données relatif au premier locataire puis on passe au second.
     * Les données sous forme de String sont sauvegardées sur une seule ligne par base, chaque données étant séparée
     * par un ";".
     * @param base
     *  La base de données à enregistrer
     * @return
     *  Une phrase qui indique si la sauvegarde à réussie
     * @throws IOException
     */
    String ecrire(BaseDeDonnees base) throws IOException{
        String chaineDonnees = null;
        DataOutputStream dos;

        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(cheminFichier)));

//        Base locataires
        int nbLocataires = base.compteurLocataire[0];

        dos.writeInt(base.compteurLocataire[0]);
        dos.writeInt(base.compteurLocataire[1]);

        for (int i = 0 ; i <= nbLocataires ; i++){
            dos.writeInt(base.idLocataire[i]);

            chaineDonnees = "";
            for (int j = 0 ; j < 4 ; j++){
                chaineDonnees += base.donneesLocataire[i][j] + ";";
            }
            dos.writeUTF(chaineDonnees);

            for (int k = 0 ; k < 5 ; k++){
                dos.writeInt(base.idBienLocataire[i][k]);
            }
        }

//        Base types
        int nbTypes = base.compteurType[0];

        dos.writeInt(base.compteurType[0]);
        dos.writeInt(base.compteurType[1]);

        for (int i = 0 ; i <= nbTypes ; i++){
            dos.writeInt(base.idType[i]);
            dos.writeUTF(base.nomType[i]);
        }

//        Base biens
        int nbBiens = base.compteurBien[0];

        dos.writeInt(base.compteurBien[0]);
        dos.writeInt(base.compteurBien[1]);

        for (int i = 0 ; i <= nbBiens ; i++){

            for (int j = 0 ; j < 3 ; j ++){
                dos.writeInt(base.clesBien[i][j]);
            }

            chaineDonnees = "";
            for (int k = 0 ; k < 2 ; k++){
                chaineDonnees += base.donneesBien[i][k] + ";";
            }
            dos.writeUTF(chaineDonnees);

            dos.writeInt(base.loyerBien[i]);
        }

        dos.close();
        return "La sauvegarde est terminée avec succès";
    }


    /**
     * Méthode qui permet de charger les info contenues dans le fichier de sauvegarde vers la base de données.
     * Les info sont chargées dans le même ordre que celui d'écriture dans le fichier (voir ligne 25)
     * @param base
     *  La base de données qui vas recevoir le contenu du fichier
     * @return
     *  Une phrase qui indique si le chargement à réussi
     * @throws IOException
     */
    String lire(BaseDeDonnees base) throws IOException{
        String chaineDonnees = null;
        DataInputStream dis;

        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(cheminFichier)));

//        Locataire
        base.compteurLocataire[0] = dis.readInt();
        base.compteurLocataire[1] = dis.readInt();

        int nbLocataires = base.compteurLocataire[0];

        for (int i = 0 ; i <= nbLocataires ; i++){
            base.idLocataire[i] = dis.readInt();

//            Lis la chaine de doneesLocataire et la découpe à chaque ";" avec la méthode de classe split qui retourne
//            un tableau contenant chaque parties
            chaineDonnees = dis.readUTF();
            int j = 0;
            for (String donnee : chaineDonnees.split(";")){
                base.donneesLocataire[i][j] = donnee;
                j ++;
            }

            for (int k = 0 ; k < 5 ; k++){
                base.idBienLocataire[i][j] = dis.readInt();
            }
        }

//        Types
        base.compteurType[0] = dis.readInt();
        base.compteurType[1] = dis.readInt();

        int nbTypes = base.compteurType[0];

        for (int i = 0 ; i <= nbTypes ; i++){
            base.idType[i] = dis.readInt();
            base.nomType[i] = dis.readUTF();
        }

//        Biens
        base.compteurBien[0] = dis.readInt();
        base.compteurBien[1] = dis.readInt();

        int nbBiens = base.compteurBien[0];

        for (int i = 0 ; i <= nbBiens ; i++){

            for (int j = 0 ; j < 3 ; j++){
                base.clesBien[i][j] = dis.readInt();
            }

//            Même procédure que pour donneesLocataire, voir ligne 118
            chaineDonnees = dis.readUTF();
            int k = 0;
            for (String donnee : chaineDonnees.split(";")){
                base.donneesBien[i][k] = donnee;
                k ++;
            }

            base.loyerBien[i] = dis.readInt();
        }

        dis.close();
        return "Base de données chargée avec succès";
    }

    /**
     * Vérifie l'existance du fichier de sauvegarde donné par l'utilisateur
     * @return
     * retourne vrai si le fichier est trouvé et faux dans le cas contraire
     */
    boolean existe(){
        File fichier = new File(cheminFichier);

        return (fichier.exists());
    }

    /**
     * Créer le repertoire "sauvegardes" si ce dernier n'est pas présent
     */
    void creerRepertoireSauvegardes(){
        File repertoire = new File("sauvegardes");
//        peut renvoyer un booleen
        repertoire.mkdir();
    }

    /**
     * Liste les fichiers présente dans le répertoire "sauvegardes" et les affiches
     */
    void listerFichier(){
        File repertoire = new File("sauvegardes");
        String[] liste = repertoire.list();

        for (String nomFichier : liste) {
            System.out.println(nomFichier);
        }
    }

    /**
     * Permet d'effectuer des tests sur la classe GestionFichier afin de s'assurer de son bon fonctionnement
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        GestionFichier objetFichier = new GestionFichier("test");
        BaseDeDonnees base = new BaseDeDonnees();

//        Chargement dan sune nouvelle base
        objetFichier.lire(base);

//        Lire baseB
        for (int i = 0 ; i < 99 ; i++){
            System.out.println(base.afficherLocataire(i));
        }
    }
}

// vLocId
