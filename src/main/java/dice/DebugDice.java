package dice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DebugDice implements Dice {

	protected int diceResult;
	//private static String filePath = "/home/ludivineo/eclipse-workspace/Java_Warriors_CodeDeBase/src/debug/debug.csv";
	public static Iterator<Integer> dataDice;
	
	
	public DebugDice(String filePath) {
		readCSVfile(filePath);
	}
	
	@Override
	public int getDiceResult() {
		setDiceResult();
		System.out.println("   !! Mode Debug !!  \n");
		return diceResult;
	}


	@Override
	public void setDiceResult() {
		if (dataDice.hasNext()) {
			diceResult = dataDice.next();
		}
		return;
	}
	
	public static void readCSVfile(String filePath) {
		String line = "";  
		String splitBy = ",";  
		String[] values = {};
		int valToAdd;
		List<Integer> intValues = new ArrayList<Integer>() ;

		try   
		{   	
			BufferedReader bReader = new BufferedReader(new FileReader(filePath));  
			while ((line = bReader.readLine()) != null)  { 
				values = line.split(splitBy); 
				for (int i = 0; i<values.length;i++) {
					valToAdd = Integer.parseInt(values[i]);
					intValues.add(valToAdd);				
				}
				dataDice = intValues.iterator();
				}  
			bReader.close();
		}
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
		return;
	}

}
