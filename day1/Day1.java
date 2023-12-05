import java.io.*;
import java.lang.StringBuilder;

public class Day1 {

    public static void main(String[] args)throws Exception{
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		
		String line;
		int sum = 0;
		int sum2 = 0;
		
		while((line = br.readLine()) != null){
			
			sum = sum + twoDigitNumber(line);
			
			sum2 = sum2 + twoDigitNumber(correctLine(line));
			
		}

        System.out.println(sum);
		System.out.println(sum2);

    }
	
	static String correctLine(String line){
		
		StringBuilder sb = new StringBuilder(line);
		int offset = 0;
		
		for(int i = 0; i < line.length(); i++){
			char numberContained = containsNumber(line, i, line.length() - i);
			if(numberContained  > '0'){
				sb.insert(i+offset,numberContained);
				offset = offset+1;
			}
		}
		
		return sb.toString();
	}
	
	static char containsNumber(String line, int counter, int charsLeft){
		char ch = line.charAt(counter);
		
		if(ch == 'o'){
			if(charsLeft >= 3 && line.charAt(counter+1) == 'n' && line.charAt(counter+2) == 'e'){
				return '1';
			}
			
		}else if(ch =='t'){
			if(charsLeft >= 3 && line.charAt(counter+1) == 'w' && line.charAt(counter+2) == 'o' ){
				return '2';
			}
			if(charsLeft >= 5 && line.charAt(counter+1) == 'h' && line.charAt(counter+2) == 'r' && line.charAt(counter+3) == 'e' && line.charAt(counter+4) == 'e'  ){
				return '3';
			}
			
		}else if(ch == 'f'){
			if(charsLeft >= 4 && line.charAt(counter+1) == 'o' && line.charAt(counter+2) == 'u' && line.charAt(counter+3) == 'r'  ){
				return '4';
			}
			if(charsLeft >= 4 && line.charAt(counter+1) == 'i' && line.charAt(counter+2) == 'v' && line.charAt(counter+3) == 'e' ){
				return '5';
			}
			
		}else if(ch =='s'){
			if(charsLeft >= 3 && line.charAt(counter+1) == 'i' && line.charAt(counter+2) == 'x' ){
				return '6';
			}
			if(charsLeft >= 5 && line.charAt(counter+1) == 'e' && line.charAt(counter+2) == 'v' && line.charAt(counter+3) == 'e' && line.charAt(counter+4) == 'n'  ){
				return '7';
			}
			
		}else if(ch == 'e'){
			if(charsLeft >= 5 && line.charAt(counter+1) == 'i' && line.charAt(counter+2) == 'g' && line.charAt(counter+3) == 'h' && line.charAt(counter+4) == 't'  ){
				return '8';
			}
			
		}else if(ch == 'n'){
			if(charsLeft >= 4 && line.charAt(counter+1) == 'i' && line.charAt(counter+2) == 'n' && line.charAt(counter+3) == 'e' ){
				return '9';
			}
		}
		/*
		one
		two 
		three
		four
		five
		six
		seven
		eight
		nine
		*/
		return 0;
	}
	
	static int twoDigitNumber(String line){
		boolean firstFound = false;
		
		int firstDigit = 0;
		int lastDigit = 0;
		
		
		
		for(int i = 0; i < line.length(); i++){
			if(Character.isDigit(line.charAt(i))){
				if(!firstFound){
					firstDigit = line.charAt(i) - '0';
					lastDigit = firstDigit;
					firstFound = true;
				}else{
					lastDigit = line.charAt(i)  - '0';
				}
			}
		}
		
		return firstDigit*10 + lastDigit;
	}
		
	
	
}