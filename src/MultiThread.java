import java.util.ArrayList;
import java.util.concurrent.Callable;



import patriciaTries.PatriciaTries;


public class MultiThread implements Callable<PatriciaTries> {

	ArrayList<String> listeMots;
	
	public MultiThread (ArrayList<String> listeMots){
		super();
		this.listeMots = listeMots;		
	}

	@Override
	public PatriciaTries call() {
		PatriciaTries res = new PatriciaTries();		
		for(int i = 0; i < listeMots.size();i++){
			res.insertion(listeMots.get(i));
		}
		System.out.println("J'ai finis de m'executer");
		return res;
	}

	
	
	
	
	
}
