package com.windsor.boardcardsort;

public class BoardingCard {
	 public String seat;
	 public String transportMeans;
	 public String departure;
	 public String destination;
	 public String tbRowLink;
	 public boolean baggage;
	 
	 	public BoardingCard(){
	 		
	 	}
	 
	 	public BoardingCard(String tbRowLink, String transportMeans, String departure, String destination, String seat, boolean baggage){
	        this.tbRowLink = tbRowLink;
	 		this.seat = seat;
	        this.transportMeans = transportMeans;
	        this.departure = departure;
	        this.destination = destination;
	        this.baggage = baggage;
	 	}

	 	public String getTbRowLink() {
	        return tbRowLink;
	    }
	 	
	    public String getSeat() {
	        return seat;
	    }
	    
	    public String getTransportMeans() {
	        return transportMeans;
	    }
	    
	    public String getDeparture() {
	        return departure;
	    }
	    
	    public String getDestination() {
	        return destination;
	    }
	    
	    public boolean getBaggage() {
	        return baggage;
	    }

	    public void SetTbRowLink(String tbRowLink) {
	        this.tbRowLink = tbRowLink;
	    }
	    
	    public void setSet(String seat) {
	        this.seat = seat;
	    }
	    
	    public void setTransportMeans(String transportMeans) {
	        this.transportMeans = transportMeans;
	    }
	    
	    public void setDeparture(String departure) {
	        this.departure = departure;
	    }
	    
	    public void setDestination(String destination) {
	        this.destination = destination;
	    }
	    
	    public void setBaggage(boolean baggage) {
	        this.baggage = baggage;
	    }

}
