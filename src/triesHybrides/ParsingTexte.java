package triesHybrides;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParsingTexte {
	
	private TriesHybrides hTries; 
	private TriesHybrides hTries2;
	private File fichier;
	
	
	public ParsingTexte(String fileName){
		this.fichier = new File(fileName);		
		this.hTries = new TriesHybrides(); 
		this.hTries2 = new TriesHybrides();
	}
	
	public TriesHybrides fullfillTries() throws IOException{
		//nécéssaires pour les tests
		boolean resTrue;
		boolean resFalse;
		
		//ListeMots
		ArrayList<String> liste = new ArrayList<String>();
		
		String line;
		String []resSplit;
		BufferedReader buff = new BufferedReader(new FileReader(this.fichier));
		while((line = buff.readLine()) != null){
			resSplit = line.split(" ");
			for(int i = 0; i < resSplit.length;i++){
				this.hTries.insertion(resSplit[i]);
				this.hTries2 = this.hTries2.insertionEquilibrage(resSplit[i]);
				System.out.println("test en cours");
				hTries2.prettyPrint();
				System.out.println("end test en cours");
				//System.out.println(resSplit[i]);
				//hTries.prettyPrint();
				resTrue = this.hTries.recherche(resSplit[i]);				
				resFalse = false;
				resFalse = this.hTries.recherche("hahaha");
				System.out.print(resSplit[i] + " ");
				if(!(resTrue && !resFalse)){
					System.out.print("failure");					
				}
				else{
					System.out.println("success");
				}
			}
		}
	/*	System.out.println("start pretty print");
		this.hTries.prettyPrint();
		System.out.println("\nIl y a : " + this.hTries.comptageMots() + " mot(s)");
		System.out.println("\nIl y a : " + this.hTries.comptageNil() + " Nil");
		
		System.out.println("\ntest listeMots");
		liste = hTries.listeMots("");
		for(int i=0; i<liste.size(); i++) {
			System.out.println(liste.get(i)); 
		} */
	/*	System.out.println("testHauteur");
		System.out.println(hTries.hauteur());
		System.out.println("testProfondeurMoyenne");
		System.out.println(hTries.profondeurMoyenne());
		System.out.println("test prefixe");
		System.out.println(hTries.prefixe("e"));
		System.out.println("test suppression");
		/*System.out.println(hTries.comptageMots());
		hTries.suppression("bonjour");
		System.out.println(); 			*/
		System.out.println("affichage pas equilibre");
		hTries.prettyPrint();
		System.out.println("end");
		System.out.println("affichage equilibre");		
		hTries2.prettyPrint();
		System.out.println("end"); 
		
		return hTries;
	}
	
	
	
	
	
	
	
	
	
	
}
