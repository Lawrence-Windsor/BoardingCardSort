package com.windsor.boardcardsort;

import java.util.ArrayList;

public class Sorting {
	
	private ArrayList<BoardingCard> listOfBoardCards;
	
	public Sorting(){
		
	}
	
	public Sorting(ArrayList<BoardingCard> listOfBoardCards){
		this.listOfBoardCards = listOfBoardCards;
	}
	
	public ArrayList<BoardingCard> sortBoardingCard(){
		System.out.println("sortBoarindCard Method");
		ArrayList<BoardingCard> listOfSortedBoardCards = new ArrayList<BoardingCard>();
		String card1Departure;	//set first element of list
		String cardLastDestination;	//set lastcard dest
		boolean checkMatch = false;
		boolean matchFound = false;

		//find a departure that doesnt have a destination to identify starting point
		for (int i = 0; i < listOfBoardCards.size(); i++) {
			card1Departure = listOfBoardCards.get(i).getDeparture();
			
			for (int j = 0; j < listOfBoardCards.size(); j++) {
				if( card1Departure.equals(listOfBoardCards.get(j).getDestination()) ){
		        	//found a destination that has a departure, so not starting point, therefore break out of loop
					checkMatch = true;
					System.out.println("Match True break inner loop");
		             break; // Breaks out of the inner loop
		    	}
				
				checkMatch = false;
				System.out.println("Match False");
				
				//check if we have iterated the list without finding a match
				if(j == listOfBoardCards.size()-1 && checkMatch == false ){
					//starting point found
					listOfSortedBoardCards.add(listOfBoardCards.get(i));
					listOfBoardCards.remove(i);
					matchFound = true;
				}
			}
			
			//check if starting point is found, if so break out of loop
			if(matchFound = true){
				System.out.println("Match Found so break first loop");
				break; // Breaks out of the inner loop
			}	
			
		}
		
		//now get destination of previous element and find the card where its a departure
		while(!listOfBoardCards.isEmpty()) {
			System.out.println("listOfBoardCards is not empty");
			cardLastDestination = listOfSortedBoardCards.get(listOfSortedBoardCards.size()-1).getDestination();	//get destination of last element in array
			if( listOfSortedBoardCards.size() == 1 ){
				for (int i = 0; i < listOfBoardCards.size(); i++) {
					if( cardLastDestination.equals(listOfBoardCards.get(i).getDeparture()) ){
			        	//found a destination that has a departure, therefore break out of loop
						listOfSortedBoardCards.add(listOfBoardCards.get(i));
						listOfBoardCards.remove(i);
			            break; // Breaks out of the inner loop
			    	}
				}
			}
			else if( listOfSortedBoardCards.size() > 1 ){
				for (int i = 0; i < listOfBoardCards.size(); i++) {
					if( cardLastDestination.equals(listOfBoardCards.get(i).getDeparture()) ){
			        	//found a destination that has a departure, therefore break out of loop
						listOfSortedBoardCards.add(listOfBoardCards.get(i));
						listOfBoardCards.remove(i);
			            break; // Breaks out of the inner loop
			    	}
				}

		    }
		}
		
		//System.out.println("RETURN ORDERLIST" + listOfSortedBoardCards);
		return listOfSortedBoardCards;
	}

}
