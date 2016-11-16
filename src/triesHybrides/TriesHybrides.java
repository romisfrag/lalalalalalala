package triesHybrides;

import interfaces.ITries;

public class TriesHybrides implements ITries{
	
	private String[] caracteres;
	private TriesHybrides[] fils;
	
	/* macros */
	public static final int GAUCHE = 0;
	public static final int MILIEU = 1;
	public static final int DROIT = 2;
			
	
	public TriesHybrides(){
		this.caracteres = new String[3];
		this.fils = new TriesHybrides[3];
	}


	@Override
	public void insertion(String element) {
				
	}


	@Override
	public void suppression(String element) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean recherche(String element) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	

}
