import java.io.*;

public class Day10{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		char[][] tiles = new char[140][];
		boolean[][] path = new boolean[140][140];
		int counter = 0;
		
		// read the input
		while((line = br.readLine()) != null){
			tiles[counter] = line.toCharArray();
			counter++;
		}
		
		int xS =0;
		int yS =0;
		
		// find the starting position
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[0].length; j++){
				if(tiles[i][j] == 'S'){
					yS = i;
					xS = j;
				}
			}
		}
		
		// solve
		counter = 1;
		Tile prev = new Tile(xS,yS,'S');
		Tile curr = new Tile(xS,yS-1,'|'); // first move from S by hand
		Tile next = new Tile(0,0,'.');
		path[yS][xS] = true;
		

		while(curr.getC() != 'S'){
			next = moveOne(prev,curr,tiles);
			prev = curr;
			curr = next;
			counter++;
			
			int pX = prev.getX();
			int pY = prev.getY();
			
			path[pY][pX] = true;
			
		}
		tiles[yS][xS] = 'L'; // check the type of S from input
		
		
		System.out.println(counter / 2);
		
		// calculate the area line by line
		// ignore -, F, 7
		// L-7 -> L--
		// F-7 -> ---
		// F-J -> --J    
		int area = 0;
		boolean inside = false;
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				char c = tiles[i][j];
				if(inside){
					if(!path[i][j]){
						area++;
					}else{
						if(c == '|' || c == 'L' || c == 'J'){
							inside = !inside;
						}
					}
				}else {
					if(path[i][j] && (c == '|' || c == 'L' || c == 'J')){
						inside = !inside;
					}
				}
			}
		}

		System.out.println(area);

	}
	
	static Tile moveOne(Tile prev, Tile curr, char[][] tiles){
		char c = curr.getC();
		
		int pX = prev.getX();
		int pY = prev.getY();
		
		int cX = curr.getX();
		int cY = curr.getY();
		
		int nX = 0;
		int nY = 0;
		
		if(c == '|'){
			if(pY < cY){
				nX = cX;
				nY = cY+1;
			}else{
				nX = cX;
				nY = cY-1;
			}
			
		}else if(c == '-'){
			if(pX < cX){
				nX = cX+1;
				nY = cY;
			}else{
				nX = cX-1;
				nY = cY;
			}
			
		}else if(c == 'L'){
			if(pX == cX){
				nX = cX+1;
				nY = cY;
			}else{
				nX = cX;
				nY = cY-1;
			}
			
		}else if(c == 'J'){
			if(pX == cX){
				nX = cX-1;
				nY = cY;
			}else{
				nX = cX;
				nY = cY-1;
			}
			
		}else if(c == '7'){
			if(pX == cX){
				nX = cX-1;
				nY = cY;
			}else{
				nX = cX;
				nY = cY+1;
			}
			
		}else if(c == 'F'){
			if(pX == cX){
				nX = cX+1;
				nY = cY;
			}else{
				nX = cX;
				nY = cY+1;
			}
			
		}
		
		return new Tile(nX,nY,tiles[nY][nX]);
	}
	
	
}
class Tile{
	private int x;
	private int y;
	private char c;
	
	public Tile(int x, int y, char c){
		setX(x);
		setY(y);
		setC(c);
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setC(char c){
		this.c = c;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public char getC(){
		return this.c;
	}
}