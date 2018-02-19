package zad2.graph;

public class Color {//implements IColor{
	
	//bo tablica boolean inicjalizowana 'false'
	public static final boolean ALLOWED = false;
	public static final boolean FORBIDDEN = true;
	
	private final int color;
	private final boolean [] allowedColors;
	
	public Color(int color, Color[] avalibleColors) {
		this.color = color;
//		this.allowedColors = allowedColors;
		allowedColors = new boolean[avalibleColors.length];
		allowedColors[color] = FORBIDDEN;
	}
	
	public Color(Color otherColor){
		color = otherColor.color;
		allowedColors = new boolean[otherColor.allowedColors.length];
		for (int i = 0; i < allowedColors.length; i++){
			allowedColors[i] = otherColor.allowedColors[i];
		}  
//		allowedColors = otherColor.allowedColors.clone();
	}

	public int getColorValue() {
		return color;
	}
	
	public boolean[] getAllowedColors(){
		return allowedColors;
	}

//	public boolean[] getAllowedColors() {
//		return allowedColors;
//	}
	
	public boolean canBePairedWithColor(Color otherColor) {
		return allowedColors[otherColor.getColorValue()] == ALLOWED;
	}
	
	public void pairWithColor(Color otherColor){
//		allowedColors[otherColor.getColorValue()] = FORBIDDEN;
//		((Color)otherColor).allowedColors[color] = FORBIDDEN;
		removeColorFromList(otherColor);
		otherColor.removeColorFromList(this);
	}			
	
	@Override
	public String toString() {
		return String.format("[%1$2d]", color);//+Integer.toString(color)+"]";
	}

	public void removeColorFromList(Color color) {
//		if(allowedColors[color.getColorValue()] == ALLOWED)
//			System.out.println("usuwam dozwolony");
//		else
//			System.out.println("USUWAM NIEDOZWOLONY");
		allowedColors[color.getColorValue()] = FORBIDDEN;
	}
	

}
