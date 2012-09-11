package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import model.Track.Status;

import service.DBService;
import android.util.Log;

public class Model {
	
	private static final String LOG_TAG = "Model";
	private static Model _model;
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public static final String PROPERTY_CHANGE_TRACK_CREATED = "tc";
	public static final String PROPERTY_CHANGE_TRACK_UPDATED = "tu";
	public static final String PROPERTY_CHANGE_TRACK_DELETED = "td";
	
	private Model(){}
	
	public static Model getInstance(){
		if(_model==null){
			_model = new Model();
			Log.i(LOG_TAG, "Service created");
		}
		return _model;
	}
	
	public ArrayList<Track> getTracks(){
		ArrayList<Track> tracks = DBService.getInstance().getAllTracks();
		
		return tracks;
	}
	
	public Track addTrack(String name, long length, long date){
		long id = DBService.getInstance().addTrack(name, length, date);
		Track createdTrack = new Track(id, Status.INITIAL.getNumericValue(), name, length, date, null, null);
		listeners.firePropertyChange(PROPERTY_CHANGE_TRACK_CREATED, null, createdTrack);
		return createdTrack;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) 
	{
		listeners.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) 
	{
		listeners.removePropertyChangeListener(l);
	}
}