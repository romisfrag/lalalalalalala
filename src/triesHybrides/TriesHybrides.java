package triesHybrides;

import java.util.ArrayList;

import patriciaTries.PatriciaTries;

public class TriesHybrides{
	
	public char caractere;
	public int valeur;
	public TriesHybrides[] fils;
	
	private static int compteur = 0;
	
	/* macros */
	public static final int GAUCHE = 0;
	public static final int MILIEU = 1;
	public static final int DROIT = 2;	
	
	public static int doublons = 0;
	
	
	public TriesHybrides(){
		this.caractere = (char)0;
		this.valeur = -1;	
		this.fils = new TriesHybrides[3];
	}

	public int getAndIncrementCompteur(){
		return compteur++;
	}

	
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
				doublons++;
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
		
		//s'il n'y a pas de caractère, retourne la liste
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
	
	
	
	/* cette fonction va crée un nouvel arbre dont la racine sera celle du fils gauche et 
	 * on met le fils droit comme fils de coté de la racine
	 */
	public TriesHybrides filsGaucheDroit(TriesHybrides gauche,TriesHybrides droit){
		
		if(gauche == null){
			return droit;
		}
		/* on a la garantie que les deux valeurs sont forcéments différentes */
		if(droit.caractere > gauche.caractere){
			if(gauche.fils[DROIT] == null){
				gauche.fils[DROIT] = droit;
				return gauche;
			}
			else{
				gauche.fils[DROIT] =  filsGaucheDroit(gauche.fils[DROIT],droit);
				return gauche;				
			}
		}
		/* sinon on sait que c'est plus grand */
		else if(droit.caractere < gauche.caractere){
			if(gauche.fils[GAUCHE] == null){				
				gauche.fils[GAUCHE] = droit;				
				return gauche;								
			}
			else{				
				gauche.fils[GAUCHE] = filsGaucheDroit(gauche.fils[GAUCHE], droit);
				return gauche;
				
			}
		}
		/* cas impossible */
		else{
			System.out.println("this is not possible that this happend");
			return null;
		}		
	}
	
	public TriesHybrides suppression(String element){
		TriesHybrides res = suppressionRec(element);
		
		if(res == null){
			
			res = new TriesHybrides();
		}
		return res;		
	}
	
	
	public TriesHybrides suppressionRec(String element) {
		
		if(element == null){
			return this;
		}
		
		TriesHybrides res;
		
		char premiereLettre = element.charAt(0);

		if(premiereLettre == caractere){

			if(element.length() == 1){
				// ici on a trouver l'élément donc on retourne l'arbre vide
				if(compteur > 0){
					// si on était une feuille de l'arbre alors pour supprimer juste retirer la valeur
					if(fils[MILIEU] != null ){
						this.valeur = -1;
					}
					else if(fils[DROIT] != null){
						if(fils[GAUCHE] != null){
							return filsGaucheDroit(fils[GAUCHE],fils[DROIT]);
						}
						else{
							return fils[GAUCHE];
						}
					}
					// ici seul le fils gauche peut etre non nul mais au pire on retourne null 
					else{
						if(fils[GAUCHE] != null){
							return fils[GAUCHE];
						}
						else{
							
							return null;
						}
					}
				}				
				// si l'élément n'était pas dans l'abre on le retourne tel quel 
				return this;
			}
			
			// c'est a la remonté qu'il faut tester si on doit supprimer des noeuds supplémentaires 
			// donc on stock tous les appels recursifs dans rec et on test à la fin
			else if(fils[MILIEU] != null){
				
				res =  fils[MILIEU].suppressionRec(element.substring(1));
				
				
				/* a la remonté il faut modifier uniquement si le resultat est null est si on est pas sur un mot */
				if(res == null && valeur == -1){
					
					if(fils[GAUCHE] != null){						
						/* si les deux fils ne sont pas vides */
						if(fils[DROIT] != null){							
							return filsGaucheDroit(fils[GAUCHE],fils[DROIT]);
						}
						else{
							return fils[GAUCHE];
						}
					}
					else{
						if(fils[DROIT] != null){
							
							return fils[DROIT];
							
						}	
						/* aucun des trois fils n'existe et pas de valeur donc return null*/
						else{
							
							return null;
						}
					}
				}
				else{					
					fils[MILIEU] = res;
					return this;
				}
			}
			
			else{
				/* ici on sait que le mot n'est pas dans l'arbre */
				
				return this;
			}
		}
		/* maintenant si l'appel venait de la gauche */
		else if(premiereLettre < caractere){
			
			if(fils[GAUCHE] != null){				
				res =  fils[GAUCHE].suppressionRec(element);
				if(fils[MILIEU] == null && valeur == -1){
					if(res != null){
						return fils[DROIT];
					}
					else{
						if(fils[DROIT] == null){
							return res;
						}
						else{							
							return filsGaucheDroit(fils[GAUCHE],fils[DROIT]);
						}
					}
				}
				else{
					fils[GAUCHE] = res;
				}
			}
			/* si il n'y a pas de fils pour faire la recherche alors retourner abre tel quel */
			else{
				return this;
			}
		}
		else{
			if(fils[DROIT] != null){		
				
				res = fils[DROIT].suppressionRec(element);
				if(fils[MILIEU] == null && valeur == -1){
					
					if(fils[GAUCHE] != null){
						if(res != null){
							return filsGaucheDroit(fils[GAUCHE],fils[DROIT]);
						}
						else{
							return fils[GAUCHE];
						}
					}
					else{
						if(res != null){
							return res;
						}
						else{
							return null;
						}
					}
				}
				else{
					
					fils[DROIT] = res;
					return this;
				}
			}	
			/* cas ou le mot n'est pas présent dans l'arbre */
			else{
				return this;
			}
		}
		return this;							
	}
	
	
	public PatriciaTries hybrideToPatricia(){
			
		
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
			if(this.fils[GAUCHE] != null){
				
				resGauche = this.fils[GAUCHE].hybrideToPatriciaRec(newTries,-1);				
				
				if(this.fils[DROIT] != null){
					
					resDroit = this.fils[DROIT].hybrideToPatriciaRec(resGauche, -1);					
					
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
				if(this.valeur != -1){
					PatriciaTries tempTries = new PatriciaTries();
					newTries = this.fils[MILIEU].hybrideToPatriciaRec(tempTries,-1);
					String neutralElement = "" + (char)0;
					newTries.setPrefixe(0,neutralElement);
					courrant.setFils(indice, newTries);
					return courrant;
				}
				return this.fils[MILIEU].hybrideToPatriciaRec(courrant, indice);
			}			
			else{				
				if(this.valeur != -1){
					newPrefixe = courrant.getPrefixe(indice) + (char)0;			
					courrant.setPrefixe(indice,newPrefixe);
					return courrant;
				}
				else{
					
					return null;
				}
			}
		}		
	}
	
	public TriesHybrides rotationDroite(){
		TriesHybrides res = this.fils[GAUCHE];
		TriesHybrides temp = res.fils[DROIT];
		
		res.fils[DROIT] = this;
		res.fils[DROIT].fils[GAUCHE] = temp;
								
		return res;
	}
	
	public TriesHybrides rotationGauche(){
		TriesHybrides res = this.fils[DROIT];
		TriesHybrides temp = res.fils[GAUCHE];
		
		res.fils[GAUCHE] = this;
		res.fils[GAUCHE].fils[DROIT] = temp;
		
		return res;
	}
	
	/* ici on fait une hauteur ne considérant que le max de fils gauches ou droits */	
	public int hauteurEquilibrage(){
		return hauteurEquilibrageRec(1);
	}
	public int hauteurEquilibrageRec(int h){
		
		int newHauteur = 0;
		
		if(fils[GAUCHE] != null){
			 newHauteur = fils[GAUCHE].hauteurEquilibrageRec(1);			 			 
		}
		if(fils[DROIT] != null){
			newHauteur = Math.max(newHauteur,fils[DROIT].hauteurEquilibrageRec(1));			
		}		
		
		return newHauteur + h;
	}
		
	public TriesHybrides equilibreArbre(){
			
		TriesHybrides temp;
		int hauteurGauche = 0;
		int hauteurDroite = 0;
		
		
		/* test préliminaires */
		if(this.fils[GAUCHE] != null){
			hauteurGauche = this.fils[GAUCHE].hauteurEquilibrage();
		}
		if(this.fils[DROIT] != null){
			hauteurDroite = this.fils[DROIT].hauteurEquilibrage();
		}
				
		
		if((hauteurGauche - hauteurDroite) == 2){
			int hauteurGaucheFils = 0;
			int hauteurDroiteFils = 0;
			if(this.fils[GAUCHE].fils[DROIT] != null){
				hauteurDroiteFils = this.fils[GAUCHE].fils[DROIT].hauteurEquilibrage();
			}
			if(this.fils[GAUCHE].fils[GAUCHE] != null){
				hauteurGaucheFils = this.fils[GAUCHE].fils[GAUCHE].hauteurEquilibrage();
			}
			
			if(hauteurGaucheFils < hauteurDroiteFils){
				temp = this.fils[GAUCHE].rotationGauche();
			}
			else{
				temp = fils[GAUCHE];
				fils[GAUCHE] = null;
			}
			this.fils[GAUCHE] = temp;
			return this.rotationDroite();						
		}		
		else if((hauteurDroite - hauteurGauche) == 2){
			
			int hauteurGaucheFils = 0;
			int hauteurDroiteFils = 0;
			if(this.fils[DROIT].fils[DROIT] != null){
				hauteurDroiteFils = this.fils[DROIT].fils[DROIT].hauteurEquilibrage();
			}
			if(this.fils[DROIT].fils[GAUCHE] != null){
				hauteurGaucheFils = this.fils[DROIT].fils[GAUCHE].hauteurEquilibrage();
			}
			
			if(hauteurDroiteFils < hauteurGaucheFils){
				temp = this.fils[DROIT].rotationDroite();
			}
			else{
				temp = fils[DROIT];
				fils[DROIT] = null;
			}
			
			this.fils[DROIT] = temp;
			
			
			return this.rotationGauche();						
		}		
		/* les deux sous arbres ont la meme hauteur donc pas besoin de reequilibrer */
		else{
			return this;
		}				
	}
		
	public TriesHybrides insertionEquilibrage(String element) {
		
		if(element == null || element == ""){
			
			return this;
		}
		char premiereLettre = element.charAt(0);
		
		/* comme pour les patricia tries on prend le code ascii 0 comme caractere de null */
		if (caractere == 0){			
			caractere = premiereLettre;
			
			if(element.length() == 1){				
				valeur = compteur++;
				
				return this;
			}
			else{
				fils[MILIEU] = new TriesHybrides();
				fils[MILIEU] = fils[MILIEU].insertionEquilibrage(element.substring(1));
				
				return this;
			}			
		}
		
		
		
		else if(premiereLettre == caractere){			
			if(element.length() == 1 && valeur == -1){				
				valeur = compteur++;
				
				return this;
			}
			/* il existe deja */
			else if(element.length() == 1){
				
				return this;
			}
			else{
				if(fils[MILIEU] == null){
					fils[MILIEU] = new TriesHybrides();
				}				
				fils[MILIEU] = fils[MILIEU].insertionEquilibrage(element.substring(1));
				
				return this;
			}
			
		}
		
		else if(premiereLettre < caractere){
			if(fils[GAUCHE] == null){
				fils[GAUCHE] = new TriesHybrides();
			}
			fils[GAUCHE] = fils[GAUCHE].insertionEquilibrage(element);
			
			return this.equilibreArbre();
		}
		else{
			if(fils[DROIT] == null){
				fils[DROIT] = new TriesHybrides();
			}
			fils[DROIT] = fils[DROIT].insertionEquilibrage(element);
			
			
			
			return this.equilibreArbre();
			
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
	

}
