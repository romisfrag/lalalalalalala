package patriciaTries;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ParsingTexte t = new ParsingTexte("test/test.txt");
		t.fullfillTries();
		
		
		if("a".compareTo("b") < 0){
			System.out.println("a".compareTo("b"));
		}
		else{
			System.out.println("failure");
		}
		
		return;
	}

}
