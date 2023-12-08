import java.io.*;
import java.lang.Math;
import java.util.Arrays;

public class Day4{
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		
		double totalPoints = 0;
		
		// For part2, counting arraylist elements
		int counter = 0;
		int[] scratchCardsMultiplier = new int[10000];
		Arrays.fill(scratchCardsMultiplier,1);
		
		while((line = br.readLine()) != null){
			// cleaning input
			String[] brokenLine = line.split(":|\\| ");
			String winningNumbers[] = brokenLine[1].trim().replaceAll(" + "," ").split(" ");
			String ourNumbers[] = brokenLine[2].trim().replaceAll(" + "," ").split(" ");
			
			// checking winning numbers
			int correctNumbers = 0;
			for(int i = 0; i< winningNumbers.length; i++){
				
				for(int j = 0; j < ourNumbers.length; j++){
					if(winningNumbers[i].equals(ourNumbers[j])){
						correctNumbers = correctNumbers +1;
					}
				}
				
			}
			
			// adding points
			if(correctNumbers > 0){
				totalPoints = totalPoints + Math.pow(2,correctNumbers-1);
			}
			
			// part2
			for(int i = counter+1; i < counter+1+correctNumbers; i++){
				scratchCardsMultiplier[i] = scratchCardsMultiplier[i] + scratchCardsMultiplier[counter];
			}
			
			counter = counter + 1;
			
			
		}
		
		int cardSum = 0;
		for(int i = 0; i< counter; i++){
			cardSum = cardSum + scratchCardsMultiplier[i];
		}
		
		System.out.println((int)totalPoints);
		System.out.println(cardSum);
	}
}