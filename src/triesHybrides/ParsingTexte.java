package triesHybrides;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

public class ParsingTexte {
	
	private TriesHybrides hTries; 
	private File fichier;
	
	
	public ParsingTexte(String fileName){
		this.fichier = new File(fileName);		
		this.hTries = new TriesHybrides(); 
	}
	
	public void fullfillTries() throws IOException{
		//nécéssaires pour les tests
		boolean resTrue;
		boolean resFalse;
		
		String line;
		String []resSplit;
		BufferedReader buff = new BufferedReader(new FileReader(this.fichier));
		while((line = buff.readLine()) != null){
			resSplit = line.split(" ");
			for(int i = 0; i < resSplit.length;i++){
				this.hTries.insertion(resSplit[i]);
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
		System.out.println("start pretty print");
		this.hTries.prettyPrint();
		return;
	}
	
	
	
	
	
	
	
	
	
	
}