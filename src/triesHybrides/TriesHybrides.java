package triesHybrides;

import java.util.ArrayList;

import interfaces.ITries;
import patriciaTries.PatriciaTries;

public class TriesHybrides implements ITries{
	
	public char caractere;
	public int valeur;
	public TriesHybrides[] fils;
	
	/* macros */
	public static final int GAUCHE = 0;
	public static final int MILIEU = 1;
	public static final int DROIT = 2;

	private static int compteur = 0;
	
	
	public TriesHybrides(){
		this.caractere = (char)0;
		this.valeur = -1;
		/* au cas ou */
		this.fils = new TriesHybrides[3];
	}

	public int getAndIncrementCompteur(){
		return compteur++;
	}

	@Override
	public void insertion(String element) {
		
		if(element == null || element == ""){
			return;
		}
		char premiereLettre = element.charAt(0);
		
		/* comme pour les patricia tries on prend le code ascii 0 comme caractere de null */
		if (caractere == 0){			
			caractere = premiereLettre;
			
			if(element.length() == 1){				
				valeur = compteur++;
			}
			else{
				fils[MILIEU] = new TriesHybrides();
				fils[MILIEU].insertion(element.substring(1));				
			}			
		}
		
		
		
		else if(premiereLettre == caractere){			
			if(element.length() == 1 && valeur == -1){				
				valeur = compteur++;				
			}
			else if(element.length() == 1){
				return;
			}
			else{
				if(fils[MILIEU] == null){
					fils[MILIEU] = new TriesHybrides();
				}				
				fils[MILIEU].insertion(element.substring(1));	
			}
			
		}
		
		else if(premiereLettre < caractere){
			if(fils[GAUCHE] == null){
				fils[GAUCHE] = new TriesHybrides();
			}
			fils[GAUCHE].insertion(element);
		}
		else{
			if(fils[DROIT] == null){
				fils[DROIT] = new TriesHybrides();
			}
			fils[DROIT].insertion(element);
		}
		return;
	}




	@Override
	public boolean recherche(String element) {
		if(element == null){
			return false;
		}
		char premiereLettre = element.charAt(0);

		if(premiereLettre == caractere){

			if(element.length() == 1){
				if(compteur > 0){					
					return true;
				}
				
				return false;
			}
			if(fils[MILIEU] != null){				
				return fils[MILIEU].recherche(element.substring(1));
			}
		}
		else if(premiereLettre < caractere){
			if(fils[GAUCHE] != null){				
				return fils[GAUCHE].recherche(element);
			}			
		}
		else{
			if(fils[DROIT] != null){				
				return fils[DROIT].recherche(element);
			}						
		}
		return false;
	}
	
	public int comptageMots() {
		int cpt = 0;
		int cptFG = 0, cptFM = 0, cptFD = 0;
		
		if(caractere != 0) {
			if(fils[GAUCHE] != null) {
				cptFG = fils[GAUCHE].comptageMots();
			}
			if(fils[MILIEU] != null) {
				cptFM = fils[MILIEU].comptageMots();
			}
			if(fils[DROIT] != null) {
				cptFD = fils[DROIT].comptageMots();
			}					
			if(valeur != -1) {	
				cpt++;
			}
			return cpt + cptFG + cptFM + cptFD;
		}
		return 0;
	}
	
	public int comptageNil() {
		int cpt = 0;
		
		if(caractere == (char)0) {
			return 1;
		}
		
		if(fils[GAUCHE] == null) {
			cpt++;
		}else {
			cpt += fils[GAUCHE].comptageNil();
		}
		if(fils[MILIEU] == null) {
			cpt++;
		}else {
			cpt += fils[MILIEU].comptageNil();
		}
		if(fils[DROIT] == null) {
			cpt++;
		}else {
			cpt += fils[DROIT].comptageNil();
		}
		
		
		return cpt;
	}
	
	public ArrayList<String> listeMots(String prefixe) {
		ArrayList<String> liste = new ArrayList<String>();
		String mot = new String();
		
		//s'il n'y a pas de caractère, return la liste
		if(caractere == 0) {
			return liste;
		}		
		
		if(fils[GAUCHE] != null) {
			liste.addAll(fils[GAUCHE].listeMots(prefixe));
		}
		
		if(valeur != -1) {
			mot = prefixe + caractere;
			liste.add(mot);
			mot = prefixe;
		}
		
		if(fils[MILIEU] != null) {
			mot = prefixe + caractere;
			liste.addAll(fils[MILIEU].listeMots(mot));
			mot = prefixe;
		}
		
		if(fils[DROIT] != null) {
			liste.addAll(fils[DROIT].listeMots(prefixe));
		}
		
		return liste;		
	}
	
	public int hauteur(){
		return hauteurRec(0);
	}
	
	public int hauteurRec(int h){
		
		int newHauteur = 0;
		
		for(int i = 0;i < 3;i++){
			if(fils[i] != null){
				int rec = fils[i].hauteurRec(1);
				if(rec > newHauteur){
					newHauteur = rec;
				}
			}
		}
		return newHauteur + h;		
	}
	
	 
	
	//tableau pour stocker les valeurs de retours
	//tab[0] stock le nb de feuilles et tab[1] la somme des profondeurs
	public int profondeurMoyenne(){
		
		int []tab = new int[2];
		tab[0] = 0;
		tab[1] = 0;
		profondeurMoyenneRec(0,tab);				
		return tab[1] / tab[0];
	}
	
	public void profondeurMoyenneRec(int profondeur,int []tab){
		
		int nbFils = 0;
		
		for(int i = 0;i < 3;i++){
			if(fils[i] != null){
				nbFils++;
				fils[i].profondeurMoyenneRec(profondeur + 1,tab);
			}
		}
		if(nbFils == 0){
			tab[0]++;
			tab[1] += profondeur;			
		}				
	}
	
	public int prefixe(String mot){
		if(mot == null){
			return 0;
		}
		char premiereLettre = mot.charAt(0);

		if(premiereLettre == caractere){

			if(mot.length() == 1){
				if(fils[MILIEU] == null && valeur != -1){
					return 1;
				}
				return this.fils[MILIEU].comptageMots();
			}
			if(fils[MILIEU] != null){				
				return fils[MILIEU].prefixe(mot.substring(1));
			}
		}
		else if(premiereLettre < caractere){
			if(fils[GAUCHE] != null){				
				return fils[GAUCHE].prefixe(mot);
			}			
		}
		else{
			if(fils[DROIT] != null){				
				return fils[DROIT].prefixe(mot);
			}						
		}
		return 0;
	}
	
	
	
/*	public void suppression(String element){
				
	}
	
	public TriesHybrides suppressionRec(String element) {
		if(element == null){
			return this;
		}
		char premiereLettre = element.charAt(0);

		if(premiereLettre == caractere){

			if(element.length() == 1){
				// ici on a trouver le mot 
				if(compteur > 0){					
					if(fils[MILIEU] == null){
						fils[DROIT]
					}
					// sinon il faut laisser le noeud tel quel 
					else{
						return this;
					}
				}
				
				return false;
			}
			if(fils[MILIEU] != null){				
				return fils[MILIEU].suppressionRec(element.substring(1));
			}
		}
		else if(premiereLettre < caractere){
			if(fils[GAUCHE] != null){				
				return fils[GAUCHE].recherche(element);
			}			
		}
		else{
			if(fils[DROIT] != null){				
				return fils[DROIT].recherche(element);
			}						
		}
		return this;
	}
	*/
	
	public PatriciaTries hybrideToPatricia(){
		
		/* TODO : faire le premier cas de base ou il n'y a que le fils du milieu dans le fils passer en argument Pour l'arbre a un element */
		
		if(fils[GAUCHE] == null && fils[DROIT] == null){
			PatriciaTries newArbre = new PatriciaTries();
			return hybrideToPatriciaRec(newArbre,-1);
		}
		
		return hybrideToPatriciaRec(null,-1);
	}
	
	public PatriciaTries hybrideToPatriciaRec(PatriciaTries courrant,int indice){
		
		PatriciaTries newTries;
		
		
		/* cela signifie qu'il faut crée un nouveau Patricia Tries */
		if(fils[GAUCHE] != null || fils[DROIT] != null){						
			newTries = new PatriciaTries();
			PatriciaTries resGauche;
			PatriciaTries resDroit;
			/* TODO: a changer ces if moches */
			if(this.fils[GAUCHE] != null){
				System.out.println("entre dans gauche");
				resGauche = this.fils[GAUCHE].hybrideToPatriciaRec(newTries,-1);
				resGauche.prettyPrint();
				System.out.println("end");
				if(this.fils[DROIT] != null){
					System.out.println("entre dans droite");
					resDroit = this.fils[DROIT].hybrideToPatriciaRec(resGauche, -1);
					resDroit.prettyPrint();
					System.out.println("second end");
				}	
				else{
					resDroit = resGauche;
				}
			}
			else{
				if(this.fils[DROIT] != null){
					resDroit = this.fils[DROIT].hybrideToPatriciaRec(newTries, -1);
				}				
				else{ 
					resDroit = newTries;
				}
			}
			/* on rappel la fonction avec les fils droits et gauches a null  pour ne pas avoir besoin de réecrire du code*/			
			if(indice == -1){
				fils[GAUCHE] = null;
				fils[DROIT] = null;
				//courrant.setFils(indice,resDroit);
				return this.hybrideToPatriciaRec(resDroit,-1);
			}
			else{
				fils[GAUCHE] = null;
				fils[DROIT] = null;
				PatriciaTries milieu = this.hybrideToPatriciaRec(resDroit,-1);
				courrant.setFils(indice,milieu);
				return courrant;
			}														
		}		
		/*Si aucun des fils droits et gauches ne sont remplis */
		else{			
			/* si l'indice pas initialiser alors il faut considerer comme indice la lettre en cours */
			String newPrefixe;
			if(indice == -1){
				indice = (int)this.caractere;
				courrant.setPrefixe(indice,"");
			}			
			newPrefixe = courrant.getPrefixe(indice) + this.caractere;			
			courrant.setPrefixe(indice, newPrefixe);				
			if(fils[MILIEU] != null){				
				return this.fils[MILIEU].hybrideToPatriciaRec(courrant, indice);
			}			
			else{
				/* test normallement inutile mais bon */
				if(this.valeur != -1){
					newPrefixe = courrant.getPrefixe(indice) + (char)0;			
					courrant.setPrefixe(indice,newPrefixe);
					return courrant;
				}
				else{
					System.out.println("ERRORORORORORORO");
					return null;
				}
			}
		}		
	}
	
	public void prettyPrint(){		
		if(caractere != 0){
			System.out.print(caractere + " ");
			if(valeur >= 0){
				System.out.println(valeur);
			}
			else{
				System.out.println("");
			}
			if(fils[MILIEU] != null){
				fils[MILIEU].prettyPrint();
			}
			if(fils[GAUCHE] != null){
				fils[GAUCHE].prettyPrint();
			}
			if(fils[DROIT] != null){
				fils[DROIT].prettyPrint();
			}
				
		}
		return;		
	}

	@Override
	public void suppression(String element) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	

}
