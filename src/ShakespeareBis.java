
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import triesHybrides.ParsingTexte;
import triesHybrides.TriesHybrides;


public class ShakespeareBis {
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		TriesHybrides hTries = new TriesHybrides();
		//TriesHybrides hTrie2 = new TriesHybrides();
		
		boolean resTrue;
		boolean resFalse;
 		
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
		while(j < listeFile.length) {
			BufferedReader buff = new BufferedReader(new FileReader(listeFile[j]));
			while((line = buff.readLine()) != null){
				resSplit = line.split(" ");
				for(int i = 0; i < resSplit.length;i++){
					hTries.insertion(resSplit[i]);
					resTrue = hTries.recherche(resSplit[i]);				
					resFalse = false;
					resFalse = hTries.recherche("UE_ALGAV");
					if(!(resTrue && !resFalse)){
						System.out.print("failure");					
					}
					else{
						System.out.println("success");
					}
				}
			}
			System.out.println(listeFile[j].getName());
			System.out.println(hTries.doublons + hTries.comptageMots());
			break;
			//j++;
		}
		
		
		
	/*	File.listRoots()
		ArrayList<ParsingTexte> listeFichiers = new ArrayList<ParsingTexte>;
		for(int i = 0;) */
	}

}
