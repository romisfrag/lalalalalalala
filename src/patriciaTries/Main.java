package patriciaTries;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ParsingTexte t = new ParsingTexte("test/test.txt");
		PatriciaTries patTries1 = t.fullfillTries();
		
		System.out.println("start 2nd file");
		ParsingTexte t2 = new ParsingTexte("test/test2.txt");
		PatriciaTries patTries2 = t2.fullfillTries();
		
		System.out.println("statfusion");
		PatriciaTries resultatFusion = patTries1.fusion(patTries2);
		
		System.out.println("begining pritint fusion");
		resultatFusion.prettyPrint();
		
		return;
	}

}
