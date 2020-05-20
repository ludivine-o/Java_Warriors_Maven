package boxes_maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import warriors.contracts.Map;

public class Board implements Map{

	protected String name;
	protected int caseNumberMax;
	HashMap<Integer, BoxMap> completeRandomMap;
	List<BoxMap> defaultMap;
	
	public Board(String chooseName, int chooseCaseNumber) {
		name = chooseName;
		caseNumberMax = chooseCaseNumber;
		completeRandomMap = new HashMap<Integer, BoxMap>();
		BoxMap newBox;
	
		for(int i=1; i<caseNumberMax; i++) {
			if (i%2==0) {
				newBox = new BoxMap(i, "un bonus !");
				completeRandomMap.put(i, newBox);
			}
			else {
				newBox = new EnnemyBoxMap(i, "un ennemi !", "Gobelin", 1, 6);
				completeRandomMap.put(i, newBox);
			}
		}
	}
	
	
	public Board() {
		name = "Map par défaut";
		caseNumberMax = 64;
		defaultMap = new ArrayList<BoxMap>();
		BoxMap newBox;
		for(int i=1; i<caseNumberMax; i++) {
			if (i>1 && i<=4) { // 4 dragons
				newBox = new EnnemyBoxMap(i, "un dragon", "Dragon", 4, 15);
				defaultMap.add(newBox);
			}
			else if (i>=5 && i<=14) { //10 sorciers
				newBox = new EnnemyBoxMap(i, "un Sorcier", "Sorcier", 2, 9);
				defaultMap.add(newBox);
			}
			else if (i>=15 && i<=24) { //10 gobelins
				newBox = new EnnemyBoxMap(i, "un Gobelin", "Gobelin", 1, 6);
				defaultMap.add(newBox);
			}
			else if (i>=24 && i<=28) { //5 Arcs
				newBox = new WarBonusBoxMap(i, "Arme", "Arc", 1);
				defaultMap.add(newBox);
			}
			else if (i>=29 && i<=31) { //3 Massues
				newBox = new WarBonusBoxMap(i, "Arme", "Massue", 3);
				defaultMap.add(newBox);
			}
			else if (i==32 && i==33) { //2 epées
				newBox = new WarBonusBoxMap(i, "Arme", "Epée", 5);
				defaultMap.add(newBox);
			}
			else if (i>=34 && i<=38) { //5 sorts eclairs
				newBox = new WizBonusBoxMap(i, "sort", "Eclairs", 2);
				defaultMap.add(newBox);
			}
			else if (i>=39 && i<=40) { //2 Sorts bouleDeFeu
				newBox = new WizBonusBoxMap(i, "sort", "Boule de Feu", 7);
				defaultMap.add(newBox);
			}
			else if (i>=41 && i<=45) { //5 mini philter
				newBox = new PhilterBoxMap(i, "Philter", "Une miniPotion de vie",1 );
				defaultMap.add(newBox);
			}
			else if (i>=46 && i<=48) { //3 philter
				newBox = new PhilterBoxMap(i, "Philter", "Une Potion de vie", 2);
				defaultMap.add(newBox);
			}
			else if (i==49) { //1 SuperPhilter
				newBox = new PhilterBoxMap(i, "Philter", "Une SUPERpotion de vie",5 );
				defaultMap.add(newBox);
			}
			else {
				newBox = new EmptyBoxMap(i, "Une case vide ...");
				defaultMap.add(newBox);
			}
		}
		Collections.shuffle(defaultMap);
	}

			
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getNumberOfCase() {
		return this.caseNumberMax;
	}
	
	
	public BoxMap getBoxMap(int boxNbr) {
		return defaultMap.get(boxNbr-1);
	}

}
