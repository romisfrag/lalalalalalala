package triesHybrides;

import java.io.IOException;

import patriciaTries.PatriciaTries;

public class Main {

	public static void main(String[] args) throws IOException {
		ParsingTexte t = new ParsingTexte("test/test.txt");
		TriesHybrides tHyb = t.fullfillTries();
	
		
		ParsingTexte t2 = new ParsingTexte("test/test2.txt");
		TriesHybrides tHyb2 = t2.fullfillTries();
		System.out.println("first tree");
		tHyb.prettyPrint();
		System.out.println("second tree");
		tHyb2.prettyPrint();
		
		System.out.println("starting printing test");
		/* test de la fonction pour foutre deux arbres en un */
		//(tHyb.filsGaucheDroit(tHyb,tHyb2)).prettyPrint();
		
		System.out.println("arbre before");
		tHyb.prettyPrint();
		TriesHybrides res = tHyb.suppression("bonjour");
		System.out.println("arbre after");
		res.prettyPrint();
		
		/*System.out.println("testing hybirdes to patricia");		
		PatriciaTries resPat = tHyb.hybrideToPatricia(); 
		System.out.println("start printing");
		resPat.prettyPrint();
		System.out.println("end"); */
		
		return;
	}

}
