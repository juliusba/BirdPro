package model;

import java.util.ArrayList;
import java.util.Calendar;

import model.Track.Status;

import service.DBService;
import android.util.Log;

public class Model {
	
	private static final String LOG_TAG = "Model";
	private static Model _model;
	
	private Model(){}
	
	public static Model getInstance(){
		if(_model==null){
			_model = new Model();
			Log.i(LOG_TAG, "Service created");
		}
		return _model;
	}
	
	public ArrayList<Track> getTracks(){
		return DBService.getInstance().getAllTracks();
	}
	
	public Track addTrack(String name, Calendar length, Calendar date){
		long id = DBService.getInstance().addTrack(name, length, date);
		return new Track(id, Status.INITIAL, name, length, date, null, null);
	}
}
