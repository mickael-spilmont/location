import java.util.Scanner;
import java.util.*;
class Commande{
void supprimerInt(int tab[],int indice){
        tab[indice]=0;
    }
    void supprimerString(String tab[],int indice){
        tab[indice]="";
    }
    void ajouterInt(int tab[]){
        
    }
    void modifierInt(int tab[],int indice){
        
    }
    void supprimerString(String tab[],int indice){
        tab[indice]="";
    }
    void ajouterString(String tab[]){
        
    }
    void modifierString(String tab[],int indice){
        
    }
    void afficherAlphabetique(String tab[]){
        String tmp []=new String [tab.length];
        for(int i=0; i<tab.length; i++){
            tmp[i]=tab[i];
        }
        Arrays.sort(tmp);
        for(int i=0; i<tab.length; i++){
            System.out.println(tmp[i]);
        }
    }
    // afficherList(BaseDeDonnee.getVTyByNo()) ;
    void afficherList(String tab[]){
        for(int i=0; i<tab.length; i++){
            System.out.println(tab[i]);
        }
    }
    void supprimerLoc(int n){
        BaseDeDonnee.setVLocId(n, BaseDeDonnee.getVLocId(BaseDeDonnee.getCIdLocataire()));
        BaseDeDonnee.setVLocNo(n, BaseDeDonnee.getVLocNo(BaseDeDonnee.getCIdLocataire()));
        BaseDeDonnee.setVLocAd(n, BaseDeDonnee.getVLocAd(BaseDeDonnee.getCIdLocataire()));
        BaseDeDonnee.setVLocBi(n, BaseDeDonnee.getVLocBi(BaseDeDonnee.getCIdLocataire()));
        setCIdLocataire(getCIdLocataire()-1);
    }
    void supprimerBien(int n){
        BaseDeDonnee.setVBiId(n, BaseDeDonnee.getVBiId(BaseDeDonnee.getCIdBien()));
        BaseDeDonnee.setVBiTy(n, BaseDeDonnee.getVBiTy(BaseDeDonnee.getCIdBien()));
        BaseDeDonnee.setVBiDes(n, BaseDeDonnee.getVBiDes(BaseDeDonnee.getCIdBien()));
        BaseDeDonnee.setVBiLoc(n, BaseDeDonnee.getVBiLoc(BaseDeDonnee.getCIdBien()));
        setCIdBiens(getCIdBiens()-1);
    }
    void supprimerTypeBien(int n){
        BaseDeDonnee.setVBiId(n, BaseDeDonnee.getVBiId(BaseDeDonnee.getCIdType()));
        BaseDeDonnee.setVBiNo(n, BaseDeDonnee.getVBiNo(BaseDeDonnee.getCIdType()));
        setCIdType(getCIdType()-1);
    }
    void ajouterLoc(){
        System.out.print("")
    }
}