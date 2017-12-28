import java.util.Scanner;
class BaseDeDonnee{
    Scanner Scan = new Scanner(System.in);

//    base locataires
    int idLocataire []=new int [500];//numero id du locataire
    String nomLocataire []=new String[500];//nom du locataire
    String adresseLocataire []=new String[500];//adresse du locataire
    int idBienLocataire [][]=new int [500][5];//id de bien loués (5 par locataire)

//    base type
    int idType []=new int [20];//id type de biens
    String nomType []=new String [20];//nom type de biens

//    base biens
    int idBien []=new int [100];//numero id des bien
    int idTypeBien []=new int [100];//numero id des types de bien
    String adresseBien []=new String [100];//description des bien
    int idLocataireBien []=new int [100];//numero id des locataires bien
    double loyerBien[] = new double [100];

//    compteurID
    int compteurLocataire[] = {0, 0};
    int compteurType[] = {0, 0};
    int compteurBien[] = {0, 0};
}
