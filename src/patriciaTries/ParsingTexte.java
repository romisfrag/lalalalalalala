package patriciaTries;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public void fullfillTries() throws IOException{
		//nécéssaires pour les tests
		boolean resTrue;
		boolean resFalse;
		
		//listeMots
		ArrayList<String> liste = new ArrayList<String>();
		
		String line;
		String []resSplit;
		BufferedReader buff = new BufferedReader(new FileReader(this.fichier));
		while((line = buff.readLine()) != null){
			resSplit = line.split(" ");
			for(int i = 0; i < resSplit.length;i++){
				this.patTries.insertion(resSplit[i]);
				//System.out.println(resSplit[i]);
				resTrue = this.patTries.recherche(resSplit[i]);
				resFalse = false;
				resFalse = this.patTries.recherche("hahaha");
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
		liste = patTries.listeMots("");
		for(int i = 0; i<liste.size(); i++) {
			System.out.println(liste.get(i));
		}
		return;
	}
	
	
	
	
	
	
	
	
	
	
}
