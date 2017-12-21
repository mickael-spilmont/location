import java.util.Scanner;
class BaseDeDonnee{
    Scanner Scan = new Scanner(System.in);

    // base locataires
    private int vLocId []=new int [500];//numero id d'adherent
    private String vLocNo []=new String[500];//nom d'adherent
    private String vLocAd []=new String[500];//adresse d'adherent
    private int vLocBi [][]=new int [500][5];//id de biens loués (5 par adherent)

    // base type
    private int vTyBiId []=new int [20];//id type de biens
    private String vTyBiNo []=new String [20];//nom type de biens

    // base biens
    private int vBiId []=new int [100];//numero id des biens
    private int vBiTy []=new int [100];//numero id des types de biens
    private String vBiDes []=new String [100];//description des biens
    private int vBiLoc []=new int [100];//numero id des locataires biens

    // compteurID
    private int cIdLocataire = 0;
    private int cIdType = 0;
    private int cIdBiens = 0;
}