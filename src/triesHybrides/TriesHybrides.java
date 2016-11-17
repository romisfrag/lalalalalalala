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
		
		if(element == null){
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
				fils[MILIEU].insertion(element.substring(1));				
			}			
		}
		
		
		
		else if(premiereLettre == caractere){			
			if(element.length() == 1 && valeur == -1){
				valeur = compteur++;				
			}
			else{
				fils[MILIEU].insertion(element.substring(1));	
			}
			
		}
		
		else if(premiereLettre < caractere){
			fils[GAUCHE].insertion(element);
		}
		else{
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
			fils[MILIEU].recherche(element.substring(1));
		}
		else if(premiereLettre < caractere){
			fils[DROIT].recherche(element); 
		}
		else{
			fils[GAUCHE].recherche(element);
		}
		
		
		
		
		return false;
	}
	
	
	
	
	

}
