package triesHybrides;

import interfaces.ITries;

public class TriesHybrides implements ITries{
	
	private char caractere;
	private int valeur;
	private TriesHybrides[] fils;
	
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
	public void suppression(String element) {
		// TODO Auto-generated method stub
		
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
			return 1;
		}else {
			cpt += fils[GAUCHE].comptageNil();
		}
		if(fils[MILIEU] == null) {
			return 1;
		}else {
			cpt += fils[MILIEU].comptageNil();
		}
		if(fils[DROIT] == null) {
			return 1;
		}else {
			cpt += fils[DROIT].comptageNil();
		}
		
		
		return cpt;
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
	
	
	
	
	

}
