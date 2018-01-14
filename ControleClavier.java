import java.io.IOException;
import java.util.Scanner;

public class ControleClavier {
    int lireEntier(BaseDeDonnees base){
        Scanner sc = new Scanner(System.in);

        while (!sc.hasNextInt()){
            sc.next();
            if (!sc.hasNextInt()){

            }
        }
        return sc.nextInt();
    }

    String lireChaine(char... args){
        Scanner sc = new Scanner(System.in);
        boolean valide = false;
        String entree = "";

        while (!valide){
            valide = true;
            entree = sc.nextLine();
            for (char caractere : args){
                if (entree.indexOf(caractere) >= 0){
                    valide = false;
                }
            }
        }
        return entree;
    }

    public static void main(String[] args) throws IOException {
        GestionFichier fichier = new GestionFichier();
        BaseDeDonnees base = new BaseDeDonnees();
        ControleClavier lireClavier = new ControleClavier();

        fichier.setCheminFichier("full.bin");
        fichier.lire(base);

        System.out.print("Entrez un entier : ");
        int id = lireClavier.lireEntier(base);
        System.out.println(id);

        System.out.print("Entrez une chaine : ");
        String chaine = lireClavier.lireChaine(';', '?');
        System.out.println(chaine);
    }
}
