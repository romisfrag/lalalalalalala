
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import patriciaTries.PatriciaTries;
import triesHybrides.ParsingTexte;
import triesHybrides.TriesHybrides;


public class Shakespeare {
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		TriesHybrides hTries = new TriesHybrides();
		//TriesHybrides hTrie2 = new TriesHybrides();
		PatriciaTries pTries = new PatriciaTries();
	
 		
		ArrayList<ParsingTexte> listeFichiers = new ArrayList<ParsingTexte>();
		ParsingTexte t = new ParsingTexte("test/test.txt");
		File f =  new File("Shakespeare/");
		File [] listeFile = f.listFiles();
		for(File child : listeFile){
			listeFichiers.add(new ParsingTexte("Shakespeare/" + child.getName()));
		}
		
		String line;
		String []resSplit;
		int j = 0;
		long debut = System.nanoTime();
		while(j < listeFile.length) {
			BufferedReader buff = new BufferedReader(new FileReader(listeFile[j]));
			while((line = buff.readLine()) != null){
				resSplit = line.split(" ");
				for(int i = 0; i < resSplit.length;i++){
					hTries.insertion(resSplit[i]);
				}
			}
			j++;
			
		}
		debut = System.nanoTime() - debut;
		System.out.println(debut);
		
		j = 0;
		debut = System.nanoTime();
		while(j < listeFile.length) {
			BufferedReader buff = new BufferedReader(new FileReader(listeFile[j]));
			while((line = buff.readLine()) != null){
				resSplit = line.split(" ");
				for(int i = 0; i < resSplit.length;i++){
					pTries.insertion(resSplit[i]);
				}
			}
			j++;			
		}
		debut = System.nanoTime() - debut;
		System.out.println(debut);
		
		
		debut = System.nanoTime();
		hTries.insertion("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println(debut);
		
		debut = System.nanoTime();
		pTries.insertion("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println(debut);
		
		debut = System.nanoTime();
		hTries.suppression("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println(debut);
		
		debut = System.nanoTime();
		pTries.suppression("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println(debut);
	
	}

}
