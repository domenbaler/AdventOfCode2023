import java.io.*;
import java.util.LinkedList;


public class Day5{
	
	public static void main(String[] args) throws Exception{
		
		// destination source range
				
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		
		String line = br.readLine();
		
		String[] seeds = line.split(":")[1].trim().split(" ");
		
		LinkedList<MathFuncMapper> almanac = new LinkedList<MathFuncMapper>();

		while((line = br.readLine()) != null){
			
			if(line.length() > 0 && !Character.isDigit(line.charAt(0))){
				
				almanac.add(new MathFuncMapper());
				
				while((line = br.readLine()) != null && line.length() > 0 && Character.isDigit(line.charAt(0))){
					
					String[] params = line.split(" ");
					
					long destination = Long.parseLong( params[0] );
					long source = Long.parseLong( params[1] );
					long range = Long.parseLong( params[2] );
					
					almanac.get( almanac.size() -1).addPartialMap(destination, source, range);
				}
			}
		}
		// part1
		Long minSeed = Long.MAX_VALUE;
		for(String seedString : seeds){
			Long seed = Long.parseLong(seedString);
			for(MathFuncMapper alm : almanac){
				seed = alm.evaluate(seed);
			}
			
			if(seed < minSeed){
				minSeed = seed;
			}
		}
		System.out.println(minSeed);
		
		// part2
		LinkedList<Interval> sources = new LinkedList<Interval>();
		
		for(int i = 0; i < seeds.length; i = i+2){
			Long seedStart = Long.parseLong(seeds[i]);
			Long seedFinish = seedStart + Long.parseLong(seeds[i+1]) -1;

			sources.add(new Interval( seedStart, seedFinish ));
		}
		
		for(MathFuncMapper alm : almanac){
			LinkedList<Interval> destinations = new LinkedList<Interval>();

			// transform sources into destinations
			while(sources.size() > 0){
				Interval tmp = sources.pop();
				boolean intervalUnused = true;
				long a = tmp.getStart();
				long b = tmp.getEnd();
				for(PartialMap pm : alm.pmList){
					long aPm = pm.getStart();
					long bPm = pm.getEnd();
					if(a >= aPm && b <= bPm){
						destinations.add( new Interval(pm.evaluate(a), pm.evaluate(b) ));
						intervalUnused = false;
						break;
					}else if(a >= aPm && a <= bPm){
						destinations.add( new Interval(pm.evaluate(a), pm.evaluate(bPm) ));
						sources.add(new Interval(bPm+1, b));
						intervalUnused = false;
						break;
					}else if(b >= aPm && b <= bPm){
						destinations.add( new Interval(pm.evaluate(aPm), pm.evaluate(b) ));
						sources.add(new Interval(a, aPm -1));
						intervalUnused = false;
						break;
					}
				}
				if(intervalUnused){
					destinations.add(tmp);
				}
				
			}
			sources = destinations;
			
		}
		
		// find minimum intervalStart
		minSeed = Long.MAX_VALUE;
		for(Interval source : sources){
			if(source.getStart() < minSeed){
				minSeed = source.getStart();
			}
		}
		
		System.out.println(minSeed);
		
	}
}

class Interval{
	private long start;
	private long end;
	
	public Interval(long start, long end){
		this.start = start;
		this.end = end;
	}
	
	public long getStart(){
		return this.start;
	}
	
	public long getEnd(){
		return this.end;
	}
	
}

class MathFuncMapper{
	
	public LinkedList<PartialMap> pmList;
	
	
	public MathFuncMapper(){
		this.pmList = new LinkedList<PartialMap>();
	}
	
	public void addPartialMap(long destination, long source, long range){
		this.pmList.add(new PartialMap( destination, source, range ));
	}
	
	public long evaluate(long value){
		for(PartialMap pm : this.pmList){
			if(pm.isInDomain(value)){
				return pm.evaluate(value);
			}
		}
		return value;
	}
	
}

class PartialMap{
	private long destination;
	private long source;
	private long range;
	private long mapDiff;
	
	public PartialMap(long destination, long source, long range){
		this.destination = destination;
		this.source = source;
		this.range = range;
		
		this.mapDiff = this.destination - this.source;
	}
	
	public boolean isInDomain(long value){
		if(value >= this.source && value < (this.source + this.range)){
			return true;
		}
		return false;
	}
	
	public long evaluate(long value){
		return value + this.mapDiff;
	}
	
	public long getStart(){
		return this.source;
	}
	
	public long getEnd(){
		return this.source + this.range -1;
	}
}