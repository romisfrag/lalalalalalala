package patriciaTries;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParsingTexte {
	
	private PatriciaTries patTries; 
	private File fichier;
	
	
	
	public ParsingTexte(String fileName){
		this.fichier = new File(fileName);		
		this.patTries = new PatriciaTries(); 
	}
	
	public PatriciaTries fullfillTries() throws IOException{
		//nécéssaires pour les tests
		boolean resTrue;
		boolean resFalse;
		
		//listeMots
		ArrayList<String> liste = new ArrayList<String>();
		
		String line;
		String []resSplit;
		BufferedReader buff = new BufferedReader(new FileReader(this.fichier));
		/* test insertion */
		while((line = buff.readLine()) != null){
			resSplit = line.split(" ");
			for(int i = 0; i < resSplit.length;i++){
				this.patTries.insertion(resSplit[i]);
				//System.out.println(resSplit[i]);
				resTrue = this.patTries.recherche(resSplit[i]);
				resFalse = false;
				resFalse = this.patTries.recherche("quelor");
				System.out.print(resSplit[i] + " ");
				if(!(resTrue && !resFalse)){
					System.out.println("failure");
				}
				else{
					System.out.println("success");
				}
			}
		}
		System.out.println("start pretty print");
		this.patTries.prettyPrint();
		//String lol = "test";
		//System.out.println(lol.substring(1));
		System.out.println("\nIl y a : " + patTries.comptageMots() + " mot(s)");
		System.out.println("\nIl y a : " + patTries.comptageNil() + " Nil");
		//test listeMots
		liste = patTries.listeMots();		
		for(int i = 0; i<liste.size(); i++) {
			System.out.println(liste.get(i));
		}
		System.out.println("testHauteur");
		System.out.println(patTries.hauteur());
		System.out.println("profondeurMoyenne");
		System.out.println(patTries.profondeurMoyenne());
		System.out.println("test Prefixe");
		System.out.println(patTries.prefixe("dactylo"));
		//System.out.println("Test suppression");
		//test suppression
		//System.out.println(patTries.comptageMots());
		//patTries.suppression("p");
		//System.out.println(patTries.comptageMots());
		//System.out.println("lol");
		//System.out.println(patTries.comptageMots());
		//patTries.prettyPrint();			
		
		return patTries;
	}
	
	
	
	
	
	
	
	
	
	
}
