
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
		PatriciaTries pTries = new PatriciaTries();
	
 		
		ArrayList<ParsingTexte> listeFichiers = new ArrayList<ParsingTexte>();		
		File f =  new File("Shakespeare/");
		File [] listeFile = f.listFiles();
		for(File child : listeFile){
			listeFichiers.add(new ParsingTexte("Shakespeare/" + child.getName()));
		}
		
		ArrayList<String> listeMots = new ArrayList<String>();
		
		String line;
		String []resSplit;
		int j = 0;		
		while(j < listeFile.length) {
			BufferedReader buff = new BufferedReader(new FileReader(listeFile[j]));
			while((line = buff.readLine()) != null){
				resSplit = line.split(" ");
				for(int i = 0; i < resSplit.length;i++){
					listeMots.add(resSplit[i]);
				}
			}
			j++;
			
		}
								
		long debut = System.nanoTime();		
		for(int i = 0; i < listeMots.size();i++){
			hTries.insertion(listeMots.get(i));
		}		
		debut = System.nanoTime() - debut;
		System.out.println("hTrie mots " + hTries.comptageMots());
		System.out.println("insertion Hybrides Tries " + debut);
		
		TriesHybrides hTriesE = new TriesHybrides();
				
		debut = System.nanoTime();	
		for(int i = 0; i < listeMots.size();i++){
			hTriesE.insertionEquilibrage(listeMots.get(i));
		}	
		debut = System.nanoTime() - debut;		
		System.out.println("insertion equilibrée Tries Hybrides " + debut);
				
		
		debut = System.nanoTime();
	
		for(int i = 0; i < listeMots.size();i++){
			pTries.insertion(listeMots.get(i));
		}	
		
		debut = System.nanoTime() - debut;
		System.out.println("insertion patricia Tries " + debut);
		System.out.println("pTrie mots " + pTries.comptageMots());
		
		
		
		
		debut = System.nanoTime();
		hTries.insertion("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println("HTries insertion anticonstitutionnellement " + debut);
		
		debut = System.nanoTime();
		hTriesE.insertionEquilibrage("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println("HTriesE insertion anticonstitutionnellement " + debut);
		
		debut = System.nanoTime();
		pTries.insertion("anticonstitutionnellement");
		debut = System.nanoTime() - debut;
		System.out.println("PTries insertion anticonstitutionnellement " + debut);
		
		
		System.out.println("");
		
		
		
		debut = System.nanoTime();
		System.out.println(hTries.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;
		System.out.println("HTries recherche anticonstitutionnellement " + debut);
		

		debut = System.nanoTime();
		System.out.println(hTriesE.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;
		System.out.println("HTriesE recherche anticonstitutionnellement " + debut);
		
		debut = System.nanoTime();
		System.out.println(pTries.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;		
		System.out.println("PTries recherche anticonstitutionnellement " + debut);
		
		
		System.out.println("");
		
		
		debut = System.nanoTime();
		hTries = hTries.suppression("anticonstitutionnellement");
		debut = System.nanoTime() - debut;		
		System.out.println("HTries suppression anticonstitutionnellement " + debut);

		debut = System.nanoTime();
		hTriesE = hTriesE.suppression("anticonstitutionnellement");
		debut = System.nanoTime() - debut;		
		System.out.println("HTriesE suppression anticonstitutionnellement " + debut);
		
		debut = System.nanoTime();
		pTries.suppression("anticonstitutionnellement");
		debut = System.nanoTime() - debut;		
		System.out.println("PTries suppression anticonstitutionnellement " + debut);
		
		
		
		System.out.println("");
		
		
		debut = System.nanoTime();
		System.out.println(hTries.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;
		System.out.println("HTries recherche anticonstitutionnellement " + debut);

		debut = System.nanoTime();
		System.out.println(hTriesE.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;
		System.out.println("HTriesE recherche anticonstitutionnellement " + debut);
		
		debut = System.nanoTime();
		System.out.println(pTries.recherche("anticonstitutionnellement"));
		debut = System.nanoTime() - debut;		
		System.out.println("PTries recherche anticonstitutionnellement " + debut);
		
		
		
		System.out.println("");
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			hTries.recherche(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("Htries recherche tout dico " + debut);
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			hTriesE.recherche(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("HtriesE recherche tout dico " + debut);
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			pTries.recherche(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("Ptries recherche tout dico " + debut);
		
		
		
		
		System.out.println("");
		
		
		
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			hTries = hTries.suppression(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("Htries suppresion tout dico " + debut);
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			hTriesE = hTriesE.suppression(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("HtriesE suppresion tout dico " + debut);	
		
		debut = System.nanoTime();
		for(int i = 0; i < listeMots.size() - 100; i++){
			pTries.suppression(listeMots.get(i));
		}
		debut = System.nanoTime() - debut;
		System.out.println("Ptries suppresion tout dico " + debut);	
		
		
		
	}

}
