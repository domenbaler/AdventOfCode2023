import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Day8{
	
	public static void main(String[] args)throws Exception{
		HashMap<Integer,Node> map = new HashMap<Integer,Node>();
		LinkedList<Integer> startingNodes = new LinkedList<Integer>();
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String instructions = br.readLine();
		String line = br.readLine();

		while((line = br.readLine()) != null){
			Node node = new Node(line);
			int c = node.getCurrent();
			map.put(c,node);
			
			if(c%100 == 65){
				startingNodes.add(c);
			}
		}
		
		
		int numberOfInstructions = instructions.length();
		int steps = 0;
		
		boolean searching = true;
		//AAA
		int currentValue = 656565;
		//ZZZ
		int finalValue = 909090;
		
		Node n;
		
		while(searching){
			n = map.get(currentValue);
			if(instructions.charAt(steps % numberOfInstructions) == 'L'){
				currentValue = n.getLeft();
			}else{
				currentValue = n.getRight();
			}
			steps = steps+1;
			if(currentValue == finalValue){
				searching = false;
			}
		}
		
		
		System.out.println(steps);
		
		
		// part 2 ???? trying with lcm
		int[] stepsPart2 = new int[startingNodes.size()];
		for(int i = 0; i < startingNodes.size(); i++){
			searching = true;
			while(searching){
				n = map.get(startingNodes.get(i));
				if(instructions.charAt(stepsPart2[i] % numberOfInstructions) == 'L'){
					currentValue = n.getLeft();
				}else{
					currentValue = n.getRight();
				}
				startingNodes.set(i,currentValue);
				stepsPart2[i] = stepsPart2[i]+1;
				if(currentValue %100 == 90){
					searching = false;
				}
			}
		}
		
		System.out.println(calculateSolution(stepsPart2));
		
		
		
	}
	// lcm is associative
	static long calculateSolution(int[] numbers){
		long x = numbers[0];
		for(int i=1; i < numbers.length; i++){
			x = lcm(x, numbers[i]);
		}
		return x;
	}
	
	static long lcm(long x, long y){
		return x*y/gcd(x,y);
	}
	
	static long gcd(long x, long y){
		if(y == 0){
			return x;
		}
		return gcd(y,x%y %y);
	}
		
}

class Node{
	private int current;
	private int left;
	private int right;
	
	public Node(String line){
		this.current = this.encode(line, 0);
		this.left = this.encode(line, 7);
		this.right = this.encode(line, 12);
	}
	
	private int encode(String line, int start){
		return line.charAt(start)*10000+line.charAt(start+1)*100+line.charAt(start+2);
	}
	
	public int getCurrent(){
		return this.current;
	}
	
	public int getLeft(){
		return this.left;
	}
	
	public int getRight(){
		return this.right;
	}
	
	@Override
	public String toString(){
		return this.current+"-"+this.left+"-"+this.right;
	}
	
}