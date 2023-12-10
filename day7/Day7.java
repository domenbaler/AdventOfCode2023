import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;

public class Day7{
	
	public static void main(String[] args)throws Exception{
		HashMap<Character, Integer> rankMap = new HashMap<Character,Integer>();

		rankMap.put('2',2);
		rankMap.put('3',3);
		rankMap.put('4',4);
		rankMap.put('5',5);
		rankMap.put('6',6);
		rankMap.put('7',7);
		rankMap.put('8',8);
		rankMap.put('9',9);
		rankMap.put('T',10);
		rankMap.put('J',11);
		rankMap.put('Q',12);
		rankMap.put('K',13);
		rankMap.put('A',14);
		
		HashMap<Character, Integer> rankMapPart2 = new HashMap<Character,Integer>();
		
		rankMapPart2.put('2',2);
		rankMapPart2.put('3',3);
		rankMapPart2.put('4',4);
		rankMapPart2.put('5',5);
		rankMapPart2.put('6',6);
		rankMapPart2.put('7',7);
		rankMapPart2.put('8',8);
		rankMapPart2.put('9',9);
		rankMapPart2.put('T',10);
		rankMapPart2.put('J',1);
		rankMapPart2.put('Q',12);
		rankMapPart2.put('K',13);
		rankMapPart2.put('A',14);
		
		BufferedReader br = new BufferedReader( new FileReader("input.txt"));
		String line;
		
		LinkedList<Hand> hands = new LinkedList<Hand>();
		LinkedList<Hand> handsPart2 = new LinkedList<Hand>();

		
		while((line = br.readLine()) != null){
			hands.add(new Hand(line, rankMap));
			
			Hand tmpHand = new Hand(line, rankMapPart2);
			tmpHand.checkJoker();
			handsPart2.add(tmpHand);
		}
		
		Collections.sort(hands);
		Collections.sort(handsPart2);
		
		int totalWinnings = 0;
		int totalWinningsPart2 = 0;
		for(int i = 0; i < hands.size(); i++){
			Hand h = hands.get(i);
			Hand h1 = handsPart2.get(i);
			
			totalWinnings = totalWinnings + h.getBid()*(i+1);
			totalWinningsPart2 = totalWinningsPart2 + h1.getBid()*(i+1);
		}
		System.out.println(totalWinnings);
		System.out.println(totalWinningsPart2);
	}
	
}

class Hand implements Comparable<Hand> {
	
	private HashMap<Character,Integer> rankMap;
	
	// 0: Five of a kind
	// 1: Four of a kind
	// 2: Full house
	// 3: Three of a kind
	// 4: Two pairs
	// 5: One pair
	// 6: High card
	
	private String cards;
	private int bid;
	
	private int handType;
	
	public Hand( String hand, HashMap<Character,Integer> rankMap){
		this.rankMap = rankMap;
		String tmp[] = hand.split(" ");
		
		this.bid = Integer.parseInt(tmp[1]);
		this.cards = tmp[0];
		this.handType = this.determineType();
	}
	
	public void checkJoker(){
		if(cards.contains("J")){
			this.handType = determineTypeJ();
		}
	}
	
	public int determineTypeJ(){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int numberOfJokers = 0;
		
		for(int i = 0; i < this.cards.length(); i++){
			char x =  this.cards.charAt(i);
			if(x == 'J'){
				numberOfJokers = numberOfJokers +1;
			}else{
				if(map.containsKey(x)){
					int y = map.get(x);
					map.replace(x,y+1);
				}else{
					map.put(x,1);
				}
			}
			
		}
		
		int sizeWithoutJokers = map.size();
		
		if(numberOfJokers >= 4){
			//five of a kind
			return 0;
		}else if(numberOfJokers == 3){
			//five of a kind
			//four of a kind
			if(sizeWithoutJokers == 1){
				return 0;
			}else{
				return 1;
			}
		}else if(numberOfJokers == 2){
			//five of a kind
			//four of a kind
			//three of a kind
			if(sizeWithoutJokers == 1){
				return 0;
			}else if(sizeWithoutJokers == 2){
				return 1;
			}else {
				return 3;
			}
		}else{
			//five of a kind
			//four of a kind
			//full house
			//three of a kind
			// pair
			if(sizeWithoutJokers == 4){
				return 5;
			}else if(sizeWithoutJokers == 3){
				return 3;
			}else if(sizeWithoutJokers == 1){
				return 0;
			}else{
				if(map.containsValue(3)){
					return 1;
				}else{
					return 2;
				}
			}
		}
	}
	
	@Override
	public int compareTo(Hand hand){
		int handType1 = hand.getHandType();
		if(this.handType == handType1){
			String cards1 = hand.getCards();
			for(int i = 0; i < cards1.length(); i++){
				int x = this.rankMap.get(this.cards.charAt(i));
				int y = this.rankMap.get(cards1.charAt(i));
				if(x != y){
					return x-y;
				}
			}
		}else{
			return handType1 -this.handType;
		}
		return 0;
	}
	
	public int getHandType(){
		return this.handType;
	}
	
	public String getCards(){
		return this.cards;
	}
	
	public int getBid(){
		return this.bid;
	}
	
	private int determineType(){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i = 0; i < this.cards.length(); i++){
			char x =  this.cards.charAt(i);
			if(map.containsKey(x)){
				int y = map.get(x);
				map.replace(x,y+1);
			}else{
				map.put(x,1);
			}
		}
		
		int size = map.size();
		if(size == 1){
			//  5
			return 0;
		}else if(size == 2){
			//4 1
			//3 2
			if(map.containsValue(4)){
				return 1;
			}else{
				return 2;
			}
		}else if(size == 3){
			// 3 1 1
			// 2 2 1
			if(map.containsValue(3)){
				return 3;
			}else{
				return 4;
			}
		}else if(size == 4){
			// 2 1 1 1
			return 5;
		}else{
			// 1 1 1 1 1
			return 6;
		}

	}
}