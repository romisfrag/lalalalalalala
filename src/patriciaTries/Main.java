package patriciaTries;

import java.io.IOException;

import triesHybrides.TriesHybrides;

public class Main {

	public static void main(String[] args) throws IOException {
		ParsingTexte t = new ParsingTexte("test/test.txt");
		PatriciaTries patTries1 = t.fullfillTries();
		
		ParsingTexte tBis = new ParsingTexte("test/test.txt");
		PatriciaTries patTriesTest = tBis.fullfillTries();
		
		System.out.println("start 2nd file");
		ParsingTexte t2 = new ParsingTexte("test/test2.txt");
		PatriciaTries patTries2 = t2.fullfillTries();
		
		/*System.out.println("statfusion");
		PatriciaTries resultatFusion = patTries1.fusion(patTries2);
		
		System.out.println("begining pritint fusion");
		resultatFusion.prettyPrint(); */
		
		System.out.println("start test pat to hyrbride");
		TriesHybrides res = patTriesTest.patriciaToHybride();
		System.out.println("beginin printing res");
		if(res != null){					
			res.prettyPrint();
		}
		System.out.println("end");
		
		
		return;
	}

}
