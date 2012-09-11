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
	
	public Track(long id, int status, String name, long length, long date, String proposedSpecie, String specie){
		this.id = id;
		
		this.status = Status.INITIAL;
        switch(status){
        	case 0:
        		break;
        	case 1:
        		this.status = Status.TOBESENDT;
        		break;
        	case 2:
        		this.status = Status.SENDT;
        		break;
        	case 3:
        		this.status = Status.ANSWERED;
        		break;
        }
		this.name = name;
		this.length = Calendar.getInstance();
		this.length.setTimeInMillis(length);
		this.date = Calendar.getInstance();
		this.date.setTimeInMillis(date);
		this.proposedSpecie = proposedSpecie;
		this.specie = specie;
	}
	
}
