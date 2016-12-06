package triesHybrides;

import java.io.IOException;

import patriciaTries.PatriciaTries;

public class Main {

	public static void main(String[] args) throws IOException {
		ParsingTexte t = new ParsingTexte("test/test.txt");
		TriesHybrides tHyb = t.fullfillTries();
		
		/*System.out.println("testing hybirdes to patricia");		
		PatriciaTries resPat = tHyb.hybrideToPatricia(); 
		System.out.println("start printing");
		resPat.prettyPrint();
		System.out.println("end"); */
		
		return;
	}

}
