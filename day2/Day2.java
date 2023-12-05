import java.io.*;

public class Day2{

    public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		
		int sumOfIDs = 0;
		int sumOfPowerOfSets = 0;
		
		while((line = br.readLine()) != null){
			
			String[] brokenLine = line.split(":");
			
			if(isGamePossible( brokenLine[1] )){
				sumOfIDs = sumOfIDs + Integer.parseInt( brokenLine[0].substring(5) );
			}
			
			sumOfPowerOfSets = sumOfPowerOfSets + minCubesMultiplied( brokenLine[1]);
			
		}
		

        
		System.out.println(sumOfIDs);
		System.out.println(sumOfPowerOfSets);
    }
	
	static int minCubesMultiplied(String game){
		int numOfReds = 0;
		int numOfGreens = 0;
		int numOfBlues = 0;
		
		int tmpCubes;
		
		String[] cubes = game.split(",|;");
		
		for (int i = 0; i < cubes.length; i++){
			int lastIndex = cubes[i].length() -1;
			
			if(cubes[i].charAt(lastIndex) == 'd'){
				tmpCubes = Integer.parseInt( cubes[i].substring(1,lastIndex-3) );
				if(tmpCubes > numOfReds){
					numOfReds = tmpCubes;
				}
			}else if(cubes[i].charAt(lastIndex) == 'n'){
				tmpCubes = Integer.parseInt( cubes[i].substring(1,lastIndex-5) );
				if(tmpCubes > numOfGreens){
					numOfGreens = tmpCubes;
				}
			}else if(cubes[i].charAt(lastIndex) == 'e'){
				tmpCubes = Integer.parseInt( cubes[i].substring(1,lastIndex-4) );
				if(tmpCubes > numOfBlues){
					numOfBlues = tmpCubes;
				}
			}
			
			
		}
		
		return numOfReds*numOfGreens*numOfBlues;
	}
	
	
	/*
	Games possible with:
	12 red cubes
	13 green cubes
	14 blue cubes
	*/
	
	static Boolean isGamePossible(String game){
		String[] cubes = game.split(",|;");
		
		for (int i = 0; i < cubes.length; i++){
			int lastIndex = cubes[i].length() -1;
			
			
			if(cubes[i].charAt(lastIndex) == 'd'){
				if(Integer.parseInt( cubes[i].substring(1,lastIndex-3) ) > 12){
					return false;
				}
				
			}else if(cubes[i].charAt(lastIndex) == 'n'){
				if(Integer.parseInt( cubes[i].substring(1,lastIndex-5) ) > 13){
					return false;
				}
				
			}else if(cubes[i].charAt(lastIndex) == 'e'){
				if(Integer.parseInt( cubes[i].substring(1,lastIndex-4) ) > 14){
					return false;
				}
				
			}
			
			
		}
		
		return true;
	}

}