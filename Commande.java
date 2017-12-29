//import java.util.Scanner;
//import java.util.*;
//class Commande{
//void supprimerInt(int tab[],int indice){
//        tab[indice]=0;
//    }
//    void supprimerString(String tab[],int indice){
//        tab[indice]="";
//    }
//    void ajouterInt(int tab[]){
//
//    }
//    void modifierInt(int tab[],int indice){
//
//    }
//    void supprimerString(String tab[],int indice){
//        tab[indice]="";
//    }
//    void ajouterString(String tab[]){
//
//    }
//    void modifierString(String tab[],int indice){
//
//    }
//    void afficherAlphabetique(String tab[]){
//        String tmp []=new String [tab.length];
//        for(int i=0; i<tab.length; i++){
//            tmp[i]=tab[i];
//        }
//        Arrays.sort(tmp);
//        for(int i=0; i<tab.length; i++){
//            System.out.println(tmp[i]);
//        }
//    }
//    // afficherList(BaseDeDonnees.getVTyByNo()) ;
//    void afficherList(String tab[]){
//        for(int i=0; i<tab.length; i++){
//            System.out.println(tab[i]);
//        }
//    }
//    void supprimerLoc(int n){
//        BaseDeDonnees.setVLocId(n, BaseDeDonnees.getVLocId(BaseDeDonnees.getCIdLocataire()));
//        BaseDeDonnees.setVLocNo(n, BaseDeDonnees.getVLocNo(BaseDeDonnees.getCIdLocataire()));
//        BaseDeDonnees.setVLocAd(n, BaseDeDonnees.getVLocAd(BaseDeDonnees.getCIdLocataire()));
//        BaseDeDonnees.setVLocBi(n, BaseDeDonnees.getVLocBi(BaseDeDonnees.getCIdLocataire()));
//        setCIdLocataire(getCIdLocataire()-1);
//    }
//    void supprimerBien(int n){
//        BaseDeDonnees.setVBiId(n, BaseDeDonnees.getVBiId(BaseDeDonnees.getCIdBien()));
//        BaseDeDonnees.setVBiTy(n, BaseDeDonnees.getVBiTy(BaseDeDonnees.getCIdBien()));
//        BaseDeDonnees.setVBiDes(n, BaseDeDonnees.getVBiDes(BaseDeDonnees.getCIdBien()));
//        BaseDeDonnees.setVBiLoc(n, BaseDeDonnees.getVBiLoc(BaseDeDonnees.getCIdBien()));
//        setCIdBiens(getCIdBiens()-1);
//    }
//    void supprimerTypeBien(int n){
//        BaseDeDonnees.setVBiId(n, BaseDeDonnees.getVBiId(BaseDeDonnees.getCIdType()));
//        BaseDeDonnees.setVBiNo(n, BaseDeDonnees.getVBiNo(BaseDeDonnees.getCIdType()));
//        setCIdType(getCIdType()-1);
//    }
//    void ajouterLoc(){
//        System.out.print("");
//    }
//}