import java.io.*;
import java.util.LinkedList;

//  1  3  6  10 15 21
//  21|2  3  4  5  6
//  21 6| 1  1  1  1
//  21 6  1| 0  0  0
public class Day9{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		int extrapolatedValues = 0;
		int leftExtrapolatedValues = 0;
		
		while((line = br.readLine()) != null){
			
			// make array of numbers
			LinkedList<Integer> leftmostNumbers = new LinkedList<Integer>();
			String[] brokenLine = line.split(" ");
			int[] numbers = new int[brokenLine.length];
			for(int i = 0; i <  numbers.length; i++){
				numbers[i] = Integer.parseInt(brokenLine[i]);
			}
			
			// compute diff, store needed data in unused parts of array
			int startFrom = 0;
			int neededValue = 0;
			while(!allZeros(numbers,startFrom)){
				leftmostNumbers.add(numbers[startFrom]);
				neededValue = numbers[numbers.length-1];
				for(int i = numbers.length-1; i > startFrom; i--){
					numbers[i] = numbers[i] - numbers[i-1];
				}
				numbers[startFrom] = neededValue;
				startFrom = startFrom +1;
			}
			
			//extrapolate next value
			int exVal = 0;
			for(int i = startFrom; i > 0; i--){
				exVal = numbers[i-1] + exVal;
			}
			extrapolatedValues = extrapolatedValues + exVal;
			
			// extrapolate previous value;
			exVal = 0;
			for(int i = leftmostNumbers.size()-1; i >= 0; i--){
				exVal = leftmostNumbers.get(i) -exVal;
			}
			leftExtrapolatedValues = leftExtrapolatedValues + exVal;
			
		}
		
		System.out.println(extrapolatedValues);
		System.out.println(leftExtrapolatedValues);
	}
	
	static boolean allZeros(int[] numbers, int startFrom){
		for(int i  = startFrom; i < numbers.length; i++){
			if(numbers[i] != 0){
				return false;
			}
		}
		return true;
	}
}