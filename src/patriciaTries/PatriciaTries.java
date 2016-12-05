package patriciaTries;

import java.util.ArrayList;

import triesHybrides.TriesHybrides;
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
			if(fils[index] != null){
				String suiteElement = element.substring(prefixeCommun.length());
				return this.fils[index].rechercheRec(suiteElement);
			}
			return false;
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
	
	public ArrayList<String> listeMots(){
		
		return listeMotsRec("");
	}
	
	public ArrayList<String> listeMotsRec(String pref) {
		ArrayList<String> liste = new ArrayList<String>();
		String mot = new String();
		
		if(prefixes[0] != null) {
			liste.add(pref);
		}
		
		for(int i=1; i<prefixes.length; i++) {
			if(prefixes[i] != null) {
				if(prefixes[i].charAt(prefixes[i].length()-1) == (char)0) {
					liste.add(pref + prefixes[i]);
				}else {
					mot = pref + prefixes[i];
					liste.addAll(fils[i].listeMotsRec(mot));
				}
			}
		}
			
		return liste;
	}
	
	// on considère que si un noeud seul et pas de fils alors hauteur = 0
	
	public int hauteur(){
		return hauteurRec(0);
	}
	
	public int hauteurRec(int h) {
		
		int newHauteur = 0;			
		
		for(int i=1; i<fils.length; i++) {
			if(fils[i] != null) {
				int tmp = fils[i].hauteurRec(1);
				if(tmp > newHauteur) {
					newHauteur = tmp;
				}
			}
		}
		return newHauteur + h;
	}
	
	
	
	public int profondeurMoyenne(){
		
		int []tab = new int[2];
		tab[0] = 0;
		tab[1] = 0;
		profondeurMoyenneRec(0,tab);				
		return tab[1] / tab[0];			
	}
		
	public void profondeurMoyenneRec(int profondeur,int []tab){
		
		int nbfils = 0;
		
		for(int i = 0; i < fils.length;i++){
			if(fils[i] != null){
				nbfils++;
				fils[i].profondeurMoyenneRec(profondeur+1,tab);
			}
		}
		if(nbfils == 0){
			tab[0]++;
			tab[1] += profondeur;
		}
		
	}
	
	/* TODO : fonction a vérifier */
	public int prefixe(String mot){
				
		int index = mot.charAt(0);
		/* ici l'erreur était que on avait copié collé la recherche et que dans la recherche si le prefixe dans la case était plus grand que le mot on quittait
		 * or ici il ne faut pas faire ça
		 */
		if(this.prefixes[index] == null){
			return 0;
		}

		// on calcule le prefixe commun 
		String prefixeCommun = bestPrefixe(mot,this.prefixes[index]);

		if(prefixeCommun.equals(mot)){
			if(prefixes[index].charAt(prefixes[index].length() - 1) == 0){
				return 1;
			}
			
			return fils[index].comptageMots();
		}
		else {
			if(fils[index] != null){
				String suiteElement = mot.substring(prefixeCommun.length());
				return this.fils[index].prefixe(suiteElement);
			}
			return 0;
		}
		
	}
	
	public void suppression(String element){		
		element = element + (char)0;
		this.suppressionRec(element,null,-1);				
	}
	
	
	public void suppressionRec(String element,PatriciaTries Pere,int indexPere){
		
		int index = element.charAt(0);
		if(this.prefixes[index] == null || this.prefixes[index].length() > element.length()){
			return;
		}
	
		/* on calcule le prefixe commun */
		String prefixeCommun = bestPrefixe(element,this.prefixes[index]);
		
		if(prefixeCommun.equals(element)){
			this.prefixes[index] = null;
			/* cas ou on a pas besoin de remonter */
			if(Pere == null){
				return;
			}
			int nbPrefixes = 0;
			int indice = 0;
			for(int i = 0; i < prefixes.length;i++){
				if(prefixes[i] != null){
					nbPrefixes++;
					indice = i;
				}
			}
			if(nbPrefixes >= 2){
				return;
			}
			// plus que un prefixe dans le noeud
			else{				
				Pere.prefixes[indexPere] += this.prefixes[indice];
				Pere.fils[indexPere] = this.fils[indice];				
			}					
		}
		else {
			if(fils[index] != null){
				String suiteElement = element.substring(prefixeCommun.length());
				this.fils[index].suppressionRec(suiteElement,this,index);
				return;
			}
			return;
		}
		
	}

	// TODO: a tester 
	public PatriciaTries fusion(PatriciaTries second){
		
		PatriciaTries resFusion = new PatriciaTries();
		fusionRec(second,resFusion);
		return resFusion;
	}
	
	public void fusionRec(PatriciaTries second,PatriciaTries resultat)
	{
		PatriciaTries newFils;
		String prefixeCommun;
		String suitePrefixeA;
		String suitePrefixeB;
		
		for(int i = 0; i < this.prefixes.length;i++){
			
			if(this.prefixes[i] == null){
				resultat.prefixes[i] = second.prefixes[i];
				resultat.fils[i] = second.fils[i];
			}
			else if(second.prefixes[i] == null){
				resultat.prefixes[i] = this.prefixes[i];
				resultat.fils[i] = this.fils[i];
			}
			else if(this.prefixes[i].equals(second.prefixes[i])){
				 resultat.prefixes[i] = this.prefixes[i];
				 
				 /* pour ne pas faire un appel inutile et initialiser un fils pour rien */				 
				 if(this.prefixes[i].charAt(this.prefixes[i].length()-1) != 0){
					 System.out.println("print lol");
					 newFils = new PatriciaTries();					 
					 this.fils[i].fusionRec(second.fils[i],newFils);
					 resultat.fils[i] = newFils;
				 }				 				 
			}
			/* ils ont un prefixe commun et ne sont pas les memes  */
			else{
				prefixeCommun = bestPrefixe(this.prefixes[i],second.prefixes[i]);
				resultat.prefixes[i] = prefixeCommun;
				newFils = new PatriciaTries();				
				suitePrefixeA = this.prefixes[i].substring(prefixeCommun.length());
				suitePrefixeB = second.prefixes[i].substring(prefixeCommun.length());
				
				if(prefixeCommun.length() != this.prefixes[i].length()){
					newFils.prefixes[suitePrefixeA.charAt(0)] = suitePrefixeA;				
					newFils.fils[suitePrefixeA.charAt(0)] = this.fils[i];
				}
				else{
					newFils.prefixes[0] = "" + (char)0;
				}
				
				
				if(prefixeCommun.length() != this.prefixes[i].length()){
					newFils.prefixes[suitePrefixeB.charAt(0)] = suitePrefixeB;
					newFils.fils[suitePrefixeB.charAt(0)] = second.fils[i];
				}
				else{
					newFils.prefixes[0] = "" + (char)0;
				}
				
				resultat.fils[i] = newFils;
			}
		}		
	}		
	
	/* permet de créer un arbre en ligne droite a partir d'un mot en lui mettant en fils l'arbre crée un argument */
	public TriesHybrides createNode(String element, TriesHybrides son){
		
		if(element == null){
			return son;
		}		
		else if(element.length() == 2){
			TriesHybrides node = new TriesHybrides();
			if(element.charAt(1) == 0){
				node.valeur = node.getAndIncrementCompteur();
			}			
			node.caractere = element.charAt(0);
			node.fils[1] = son;
			return node;
		}
		else{
			TriesHybrides node = new TriesHybrides();					
			node.caractere = element.charAt(0);			
			String suiteElement = element.substring(1);
			node.fils[1] = createNode(suiteElement,son);
			return node;			
		}								
	}
	
	public void changeRef(TriesHybrides a,TriesHybrides b){
		TriesHybrides temp = a;
		a = b;
		b = temp;
	}
	
	/* a voir */
	@SuppressWarnings("null")
	public TriesHybrides patriciaToHybride(){
		System.out.println("entrer");
								
		ArrayList<Integer> noeudsActifs = new ArrayList<Integer>();
		
		/* on commence a 1 car l'information de fin de mot est recuperer ulterieurement */
		for(int i = 1; i < prefixes.length;i++){
			if(prefixes[i] != null){
				noeudsActifs.add(i);
			}
		}
		
		
		
		int milieu = ((noeudsActifs.size() - 1) / 2) + 1;
		System.out.println(milieu);		
		int indice;
		TriesHybrides filsTemp = null;	
		
		/* premiere boucle pour construire la partie droite */
		for(int i = noeudsActifs.size() - 1; i >= milieu; i--){
			
			/* on déclare ici car variable locale et donc ça va nous aider */					
			TriesHybrides temp = null;
			TriesHybrides res = null;
			
			indice = noeudsActifs.get(i);
			System.out.println(prefixes[indice]);
			/* on commence par l'appel recursif sur le fils du noeud a traiter */
			if(fils[indice] != null){
				res = fils[indice].patriciaToHybride();
			}
					
			/* pour verifier si le mot se termine dans la case */
			int tailleMot = prefixes[indice].length() - 1;
			String mot = prefixes[indice];
			
			/* si le mot se termine à l'aide du fils */
			if(mot.charAt(tailleMot - 1) != 0){
				if(fils[indice] != null){
					if(fils[indice].prefixes[0] != null){
						mot = mot + (char)0;
					}
				}
			}								
			temp = createNode(mot,res);
			temp.fils[0] = filsTemp;
			changeRef(filsTemp,temp);
			//filsTemp = temp;			
		}
		
		
		/* on stock le fils du milieu */
		TriesHybrides ret = filsTemp;
		
		/* traitement fils gauche */
		for(int i = 0; i < milieu; i++){
			TriesHybrides temp = null;
			TriesHybrides res = null;
			
			indice = noeudsActifs.get(i);
			if(fils[indice] != null){
				res = fils[indice].patriciaToHybride();
			}
			
			/* pour verifier si le mot se termine dans la case */
			int tailleMot = prefixes[indice].length() - 1;
			String mot = prefixes[indice];
			
			/* si le mot se termine à l'aide du fils */
			if(mot.charAt(tailleMot - 1) != 0){
				if(fils[indice] != null){
					if(fils[indice].prefixes[0] != null){
						mot = mot + (char)0;
					}
				}
			}
			
			temp = createNode(mot,res);
			temp.fils[0] = filsTemp;			
			changeRef(filsTemp,temp);
			//filsTemp = temp;				
		}
		if(filsTemp != null){
			filsTemp.fils[0] = filsTemp;			
		}
		else{
			ret = filsTemp;
		}
		System.out.println("ending");
		return ret;					
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

		
	
	
	
	

		
	
	
	
	
}
