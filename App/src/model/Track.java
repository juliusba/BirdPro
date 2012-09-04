package model;

import java.util.Calendar;

public class Track {
	
	public enum Status { 
		INITIAL(0),
		TOBESENDT(1),
		SENDT(2),
		ANSWERED(3);
		
		private int numericValue;
		Status (int i)
	    {
	        this.numericValue = i;
	    }
	    public int getNumericValue()
	    {
	        return numericValue;
	    }
	}
	
	public long id;
	
	public Status status;
	public String name;
	public Calendar length;
	public Calendar date;
	//public String location;
	public String proposedSpecie;
	public String specie;
	
	public Track(long id, Status status, String name, Calendar length, Calendar date, String proposedSpecie, String specie){
		this.id = id;
		this.status = status;
		this.name = name;
		this.length = length;
		this.date = date;
		this.proposedSpecie = proposedSpecie;
		this.specie = specie;
	}
	
}
