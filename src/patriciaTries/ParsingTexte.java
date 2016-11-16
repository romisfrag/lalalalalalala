package patriciaTries;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParsingTexte {
	
	private PatriciaTries patTries; 
	private File fichier;
	
	
	public ParsingTexte(String fileName){
		this.fichier = new File(fileName);		
		this.patTries = new PatriciaTries(); 
	}
	
	public void fullfillTries() throws IOException{
		String line;
		String []resSplit;
		BufferedReader buff = new BufferedReader(new FileReader(this.fichier));
		while((line = buff.readLine()) != null){
			resSplit = line.split(" ");
			for(int i = 0; i < resSplit.length;i++){
				System.out.println(i);
				this.patTries.insertion(resSplit[i]);
			}
		}
		return;
	}
	
	
	
	
	
	
	
	
	
	
}
