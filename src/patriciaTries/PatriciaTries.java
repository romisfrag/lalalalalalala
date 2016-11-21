package patriciaTries;

import interfaces.ITries;

public class PatriciaTries implements ITries{
	
	private String [] prefixes;	
	private PatriciaTries[] fils;
	
	   
	
	public PatriciaTries(){
		/* faire une initialisation pour les prefixes et mettre a null quand on a pas initialisé */
		this.prefixes = new String[128];
		this.fils = new PatriciaTries[128];
		/* il va falloir faire une initialisation a null de tous les fils avec boucle for il me semble */
	}
	
	/*
	public void putElemPrefixe(int index, String element){
		this.prefixes[index] = element;
	}
	public void putTrieFils(int index,PatriciaTries son){
		this.fils[index] = son;
	}
	*/

	@Override
	public void insertion(String element){
		/* cas de base au cas ou l'élément est vide */
		if(element.equals(null)){
			return;
		}
		/* on rajoute le mot vide a la fin de la chaine de charactère en entrée */
		element = element + (char)0;
		this.insertionRec(element);
	}
	
	public void insertionRec(String element) {
		

		/* index obtenue avec l'aide de la première ligne du mot */
		int index = element.charAt(0);
		/* on récupère le préfixe de la case ou il faut insérer */
		String prefixe = prefixes[index];
		
		/* c'est le cas ou la case n'a pas été remplie TODO peut etre a changer*/ 
		/*TODO frapper très fort alezxandr elavigne avec son .equals de merde */
		if(prefixe == null){
			prefixes[index] = element;
			return;
		}
		
		/* donc ici on est dans le cas ou il existe un élément dans la case */
		/* on calcule le préfixe commun */
		String prefixeCommun = bestPrefixe(element,prefixe);
		
		
		
		/* cas ou le prefixe commun est plus grand que le prefixe dans la case */
		/* il suffit de faire l'appel recursif sur le fils avec la suite de l'élément a inserer */
		/* je met supérieur ou égale d'ou le test pour voir si c'est nul ou pas au debut */
		if(prefixeCommun.length() == prefixe.length()){
			/* cas ou le mot est déja présent */
			if(prefixeCommun.length() == element.length()){
				return;
			}
			String suiteElement = element.substring(prefixeCommun.length());
			fils[index].insertionRec(suiteElement);
			return;
		}
		/* si le préfixe commun est plus petit que le préfixe déja en place */
		else{
			/* il faut d'abord crée un nouveau PatriciaTries */
			PatriciaTries newSon = new PatriciaTries();
			/* il faut calculer la suite des préfixes */
			String suitePrefixe = prefixe.substring(prefixeCommun.length());
			String suiteElement = element.substring(prefixeCommun.length());
			int indexSuitePrefixe = suitePrefixe.charAt(0);
			int indexSuiteElement = suiteElement.charAt(0); 
			/* maintenant on les places dans le nouvel arbre */
			//newSon.putElemPrefixe(indexSuitePrefixe,suitePrefixe);
			newSon.prefixes[indexSuitePrefixe] = suitePrefixe;
			
			//newSon.putElemPrefixe(indexSuiteElement, suiteElement);
			newSon.prefixes[indexSuiteElement] = suiteElement;
			
			//newSon.putTrieFils(indexSuitePrefixe,this.fils[indexSuitePrefixe]);
			newSon.fils[indexSuitePrefixe] = this.fils[indexSuitePrefixe];
			/* et maintenant on met le nouveau fils  */
			this.prefixes[index] = prefixeCommun;
			this.fils[index] = newSon;
		}	
	}
	
	private String bestPrefixe(String mot1,String mot2){
		
		String prefixe_commun = "";
		int i = 0;
		
		while((i < mot1.length() && i < mot2.length()) && (mot1.charAt(i) == mot2.charAt(i))){
			prefixe_commun += mot1.charAt(i);
			i++;
		}
		return prefixe_commun;
	}

	@Override
	public boolean recherche(String element){
		/* on rajoute le mot vide a la fin de la chaine de charactère en entrée */
		element = element + (char)0;
		
		//System.out.println("");
		//System.out.println(element + " ");
		return this.rechercheRec(element);
	}
	public boolean rechercheRec(String element) {
		
		
		int index = element.charAt(0);
		if(this.prefixes[index] == null || this.prefixes[index].length() > element.length()){
			return false;
		}
	
		/* on calcule le prefixe commun */
		String prefixeCommun = bestPrefixe(element,this.prefixes[index]);
		
		if(prefixeCommun.equals(element)){
			return true;
		}
		else {
			String suiteElement = element.substring(prefixeCommun.length());
			return this.fils[index].rechercheRec(suiteElement); 
		}
	}
	
	public int comptageMots() {
		int cpt = 0;
		String lastChar = "";
		
		if(prefixes != null) {
			if(prefixes[0] != null) {
				cpt++;
			}
			if(fils != null) {
				for(int i = 1; i < prefixes.length; i++) {
					if(prefixes[i] != null) {
						//On récupère le dernier caractère du mot pour tester si 'fin de mot'
						lastChar = prefixes[i].substring(prefixes[i].length()-1);
						//test
						if(lastChar.equals(""+(char)0)) {
							cpt++;
						}
						else{
							if(fils[i] != null) {
								cpt += fils[i].comptageMots();
							}							
						}
					}
				}
			}	
		}	
		
		return cpt;
	}
	
	public int comptageNil() {
		int cpt = 0;
		
		if(prefixes == null) {
			return cpt;
		}
		
		for(int i = 0; i < prefixes.length; i++) {
			if(prefixes[i] == null) {
				cpt++;
			}
			else {
				if(fils[i] != null) {
					cpt += fils[i].comptageNil();
				}
			}
		}
		
		return cpt;
	}
	
	public void prettyPrint(){
		for(int i = 0; i < this.prefixes.length;i++){
			if(this.prefixes[i] != null){
				System.out.println(prefixes[i]);
			}
		}
		for(int i = 0; i < this.fils.length;i++){
			if(this.fils[i] != null){
				System.out.println("fils" + i);
				this.fils[i].prettyPrint();
			}
		}
		
	}

	
	
	@Override
	public void suppression(String element) {

		
	}


		
	
	
	
	
}
