import java.io.*;
import java.util.ArrayList;
import java.lang.Math;

public class Day3 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		
		int sum = 0;
		int y = 0;
		
		ArrayList<Number> numbers = new ArrayList<Number>();
		ArrayList<Symbol> symbols = new ArrayList<Symbol>();
		ArrayList<Symbol> gears = new ArrayList<Symbol>();
		
		// read input and create numbers and symbols
		
		while((line = br.readLine()) != null){
			for(int i = 0; i < line.length(); i++){
				if(line.charAt(i) == '.'){
					//skip dots
				}else if( Character.isDigit(line.charAt(i))){
					//digits
					
					String tmpNumber = "";
					tmpNumber = tmpNumber+line.charAt(i);
					int startX = i;
					
					// number from digits
					while((i+1)<line.length() && Character.isDigit(line.charAt(i+1))){
						tmpNumber = tmpNumber+line.charAt(i+1);
						i++;
					}
					numbers.add(new Number(Integer.parseInt(tmpNumber),startX,i,y));
				}else {
					//everything left is a symbol
					symbols.add(new Symbol(i,y));
					
					// gear
					if(line.charAt(i) == '*'){
						gears.add(new Symbol(i,y));
					}
				}
			}
			y = y+1;
		}
		
		// compare adjacent numbers and symbols
		for(Number numb : numbers){
			for(Symbol symb : symbols){
				numb.checkAdjacency(symb.getX(), symb.getY());
			}
			if(numb.isAdjacent()){
				sum = sum + numb.getValue();
			}
		}
		
		// compare with gears
		int gearSum = 0;
		for(Symbol gear : gears){
			int gearMulti = 1;
			int connectedParts = 0;
			
			for(Number numb : numbers){
				if(numb.checkAdjacency(gear.getX(),gear.getY())){
					connectedParts = connectedParts +1;
					gearMulti = gearMulti*numb.getValue();
				}
			}
			
			if(connectedParts == 2){
				gearSum = gearSum+gearMulti;
			}
			
		}
		
		
		System.out.println(sum);
		System.out.println(gearSum);

	}
	
}
class Symbol{
	private int x;
	private int y;
	
	public Symbol(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	@Override
	public String toString(){
		return this.x+" "+this.y;
	}
}

class Number {
	private boolean adjacent;
	private int value;
	private int xStart;
	private int xEnd;
	private int y;
	
	public Number(int value, int xStart, int xEnd, int y){
		this.value = value;
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.y = y;
		this.adjacent = false;
	}
	
	public boolean isAdjacent(){
		return adjacent;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public boolean checkAdjacency(int x, int y){
		for(int i = 0; i <= (this.xEnd-this.xStart); i++){
			if(Math.sqrt( (this.y - y )*(this.y - y ) + (this.xStart+i - x)*(this.xStart+i - x)) <= Math.sqrt(2)){
				this.adjacent = true;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.value+" "+this.adjacent+" "+this.xStart+" "+this.xEnd+" "+this.y;
	}
	
	
}