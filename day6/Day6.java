import java.io.*;

public class Day6{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line1 = br.readLine().split(":")[1].trim();
		String line2 = br.readLine().split(":")[1].trim();
		
		String[] times = line1.trim().replaceAll(" + "," ").split(" ");
		String[] distances = line2.trim().replaceAll(" + "," ").split(" ");
		
		// part 1
		
		int margin = 1; //  multiplication Identity
		
		for(int i = 0; i < times.length; i++){
			int goodTimes = 0;
			int t = Integer.parseInt(times[i]);
			for(int p = 1; p < t; p++){
				int d = Integer.parseInt(distances[i]);
				if( (t-p)*p > d ){
					goodTimes = goodTimes +1;
				}
			}
		
			margin = margin * goodTimes;
		}
		
		System.out.println(margin);
		
		// part 2
		margin = 0; // + Identity
		long time = Long.parseLong(line1.replaceAll(" + ",""));
		long distance = Long.parseLong(line2.replaceAll(" + ",""));
		
		for(long p = 0; p < time; p++){
			if( (time - p)*p > distance ){
				margin = margin +1;
			}
			
		}
		System.out.println(margin);
	}
	
}