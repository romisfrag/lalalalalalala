package patriciaTries;

public class PatriciaTries implements ITries{
	
	private String [] prefixes;	
	private PatriciaTries[] fils;
	
	   
	
	public PatriciaTries(){
		/* faire une initialisation pour les prefixes et mettre a null quand on a pas initialisé */
		this.prefixes = new String[128];
		this.fils = new PatriciaTries[128];
	}


	@Override
	public void insertion(String element) {
		/* on rajoute le mot vide a la fin de la chaine de charactère en entrée */
		element = element + (char)0;
		
		
		/* index obtenue avec l'aide de la première ligne du mot */
		int index = element.charAt(0);
		/* on récupère le préfixe de la case ou il faut insérer */
		String prefixe = prefixes[index];
		
		
		/* c'est le cas ou la case n'a pas été remplie TODO peut etre a changer*/ 
		if(prefixe.equals(null)){
			prefixes[index] = element;
			return;
		}
		
		
		/* donc ici on est dans le cas ou il existe un élément dans la case */
	
		/* on calcule le préfixe commun */
		String prefixe_commun = bestPrefixe(element,prefixe);
		
		/* cas ou le prefixe commun est plus petit que l'élément */ 
		if(prefixe_commun.length() < element.length())
		
				
		
	}
	
	private String bestPrefixe(String mot1,String mot2){
		
		String prefixe_commun = "";
		int i = 0;
		
		while((i < mot1.length() || i < mot2.length()) && (mot1.charAt(i) == mot2.charAt(i))){
			i++;
			prefixe_commun += mot1.charAt(i);
		}
		return prefixe_commun;
	}


	@Override
	public void suppression(String element) {

		
	}


	@Override
	public boolean recherche(String element) {

		return false;
	}
	
	
	
	
	
}
