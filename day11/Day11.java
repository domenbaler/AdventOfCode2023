import java.io.*;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Arrays;

public class Day11{
	
	public static void main(String[] args)throws Exception{
		
		LinkedList<Galaxy> galaxies = new LinkedList<Galaxy>();
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		int y = 0;
		int yOld = 0;
		int[] xTable = new int[140]; // number of rows 
		
		// part 1 : ADD lines   
		// part 2 : REPLACE lines
		
		// read input
		while((line = br.readLine()) != null){
			boolean allDots = true;
			char[] scanLine = line.toCharArray();
			for(int i = 0; i < scanLine.length; i++){
				if(scanLine[i] != '.'){
					allDots = false;
					Galaxy g = new Galaxy(i,y);
					g.setYOld(yOld);
					galaxies.add(g);
					xTable[i] = 1;
				}
			}
			// add extra rows
			if(allDots){
				y++;
				yOld = yOld + 999999;
			}
			y++;
			yOld++;
		}
		// change xTable to show number of empty lines before xTable[i]
		int emptyLines = 0;
		for(int i = 0; i < xTable.length; i++){
			if(xTable[i] == 0){
				xTable[i] = emptyLines;
				emptyLines++;
			}else{
				xTable[i] = emptyLines;
			}
		}
		
		// add more columns
		for(int i = 0; i < galaxies.size(); i++){
			Galaxy g =galaxies.get(i);
			int tmpX = g.getX();
			g.expandX(xTable[tmpX]);
			g.expandOldX(xTable[tmpX]);
		}
		
		
		// solve
		int sum = 0;
		long sumOld = 0;
		for(int i = 0; i < galaxies.size(); i++){
			Galaxy a = galaxies.get(i);
			for(int j = i+1; j < galaxies.size();j++){
				Galaxy b = galaxies.get(j);
				sum = sum + Math.abs(a.getX()-b.getX())+Math.abs(a.getY()-b.getY());
				sumOld = sumOld + Math.abs(a.getXOld()-b.getXOld())+Math.abs(a.getYOld()-b.getYOld());
			}
		}
		System.out.println(sum);
		System.out.println(sumOld);
	}
	
}

class Galaxy{
	private int x;
	private int y;
	
	private int xOld;
	private int yOld;
			
	public Galaxy(int x, int y){
		this.x = x;
		this.y = y;
		
		this.xOld = x;
		this.yOld = y;
	}
	
	public void expandX(int k){
		this.x = this.x + k;
	}
	
	public void expandOldX(int k){
		this.xOld = this.xOld + k*999999;
	}
	
	public int getXOld(){
		return this.xOld;
	}
	
	public int getYOld(){
		return this.yOld;
	}
	
	public void setYOld(int yOld){
		this.yOld = yOld;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
		
	@Override
	public String toString(){
		return this.x+" - "+this.y;
	}
}