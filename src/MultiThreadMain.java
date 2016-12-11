import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import patriciaTries.PatriciaTries;

public class MultiThreadMain {

	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {							
		
		File f =  new File("Shakespeare/");
		File [] listeFile = f.listFiles();
					
		MultiThread []prog = new MultiThread[listeFile.length];	
		
		
		ArrayList<String> listeMots; 
		
		String line;
		String []resSplit;
		int j = 0;		
		while(j < listeFile.length) {
			listeMots = new ArrayList<String>();
			BufferedReader buff = new BufferedReader(new FileReader(listeFile[j]));
			while((line = buff.readLine()) != null){
				resSplit = line.split(" ");
				for(int i = 0; i < resSplit.length;i++){
					listeMots.add(resSplit[i]);
				}
			}
			
			prog[j] = new MultiThread(listeMots);
			prog[j].call();
			j++;
			
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(prog.length);
		
		Future<PatriciaTries>temp = null;		
		Future<PatriciaTries> res = executor.submit(prog[0]);
		PatriciaTries resTries = res.get();
		for(int i = 1; i < prog.length;i++){
			temp = executor.submit(prog[i]); 
			resTries = resTries.fusion(temp.get());
			System.out.println(resTries.comptageMots());
		}
				
		System.out.println("terminated" + resTries.comptageMots());
		
		
	}
	
}
