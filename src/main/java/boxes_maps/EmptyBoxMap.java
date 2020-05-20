package boxes_maps;

public class EmptyBoxMap extends BoxMap {

	public EmptyBoxMap(int boxNbr, String boxEvent) {
		super(boxNbr, boxEvent);
		
	}
	
	
	@Override
	public String toString() {
		String str = "Une case vide ....";
		return str;

	}

}
