import java.io.*;

/**
 * <b></>Permet la lecture/écriture de la base de donnée dans un fichier .bin mais aussi la manipulation des fichiers
 * et repertoires</b>
 * @author Spilmont Mickael
 */

public class GestionFichier {
    /**
     * Contiens le chemin relatif du fichier ainsi que son nom sous la forme sauvegarde/nom_du_fichier.bin
     */
    private String cheminFichier;

    /**
     * Constructeur de GestionFichier
     * @param nomFichier
     * Le nom du fichier indiqué par l'utilisateur
     */
    private GestionFichier(String nomFichier){
        cheminFichier = "sauvegardes/" + nomFichier + ".bin";
    }

    /**
     * Met le fichier de sauvegarde en mémoire tampon pour l'écriture
     * @throws IOException
     */
    private void ecrire() throws IOException{
        DataOutputStream dos;

        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(cheminFichier)));

        dos.writeUTF("Ceci est un test");
        dos.close();
    }

    /**
     * Met le fichier de sauvegarde en mémoire tampon pour la lecture
     * @throws IOException
     */
    private void lire() throws IOException{
        DataInputStream dis;

        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(cheminFichier)));

        String ligne1 = dis.readUTF();
        dis.close();

        System.out.println(ligne1);
    }

    /**
     * Vérifie l'existance du fichier de sauvegarde donné par l'utilisateur
     * @return
     * retourne vrai si le fichier est trouvé et faux dans le cas contraire
     */
    private boolean existe(){
        File fichier = new File(cheminFichier);

        return (fichier.exists());
    }

    /**
     * Créer le repertoire "sauvegardes" si ce dernier n'est pas présent
     */
    private void creerRepertoireSauvegardes(){
        File repertoire = new File("sauvegardes");
//        peut renvoyer un booleen
        repertoire.mkdir();
    }

    /**
     * Liste les fichiers présente dans le répertoire "sauvegardes" et les affiches
     */
    private void listerFichier(){
        File repertoire = new File("sauvegardes");
        String[] liste = repertoire.list();

        for (String nomFichier : liste){
            System.out.println(nomFichier);
        }
    }

    /**
     * Permet d'effectuer des tests sur la classe GestionFichier afin de s'assurer de son bon fonctionnement
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        GestionFichier objetFichier;

        objetFichier = new GestionFichier("bidule");
        objetFichier.creerRepertoireSauvegardes();
        objetFichier.ecrire();
        objetFichier.listerFichier();

        if (objetFichier.existe()){
            System.out.println("Le fichier existe");
        }
        else{
            System.out.println("Le fichier n'existe pas");
        }

        objetFichier.lire();
    }
}

// vLocId