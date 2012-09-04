package service;

import java.util.ArrayList;
import java.util.Calendar;

import model.Track;
import model.Track.Status;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBService extends SQLiteOpenHelper {

	private static final String LOG_TAG = "DBService";
	private static DBService _DBService;
	
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "birdPro";
	
    private static final String TABLE_TRACKS = "tracks";
    
    private static final String COL_ID = "id";
    private static final String COL_STATUS = "status";
    private static final String COL_NAME = "name";
    private static final String COL_LENGTH = "length";
    private static final String COL_DATE = "date";
    //private static final String COL_LOCATION = "location";
    private static final String COL_PROPOSED_SPECIE = "proposed";
    private static final String COL_SPECIE = "specie";
	
	private DBService(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	public static void init(Context context){
		_DBService = new DBService(context);
	}
	
	public static DBService getInstance(){
		if(_DBService == null){
			Log.e(LOG_TAG, "Not yet instanciated!");
		}
		return _DBService;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TRACKS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_STATUS + " INTEGER DEFAULT 0,"
				+ COL_NAME + " TEXT,"
				+ COL_LENGTH + " INTEGER,"
				+ COL_DATE + " INTEGER,"
				//+ COL_LOCATION + " TEXT,"
				+ COL_PROPOSED_SPECIE + " TEXT,"
				+ COL_SPECIE + " TEXT"
				+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKS);
        onCreate(db);
        // TODO I think we have to preserve the old data, but not sure.
	}
	
	public long addTrack(String name, Calendar length, Calendar date) {
	    SQLiteDatabase db = getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(COL_NAME, name);
	    values.put(COL_LENGTH, length.getTimeInMillis());
	    values.put(COL_DATE, date.getTimeInMillis());
	 
	    long id = db.insert(TABLE_TRACKS, null, values);
	    db.close();
	    
	    return id;
	}
	
	public void removeTrack(Track track){
		
		SQLiteDatabase db = getWritableDatabase();
	    db.delete(TABLE_TRACKS, COL_ID + " = ?",
	            new String[] { String.valueOf(track.id) });
	    db.close();
	}
	
	public int updateTrack(Track track) {
        SQLiteDatabase db = getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, track.status.getNumericValue());
        values.put(COL_NAME, track.name);
        values.put(COL_LENGTH, track.length.getTimeInMillis());
        values.put(COL_PROPOSED_SPECIE, track.proposedSpecie);
        values.put(COL_SPECIE, track.specie);
 
        return db.update(TABLE_TRACKS, values, COL_ID + " = ?",
                new String[] { String.valueOf(track.id) });
    }
	
	public Track getTrack(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(
        		TABLE_TRACKS, 
        		new String[] { COL_STATUS, COL_NAME, COL_LENGTH, COL_DATE, COL_PROPOSED_SPECIE, COL_SPECIE },
        		COL_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        Status status = Status.INITIAL;
        switch(Integer.parseInt(cursor.getString(0))){
        	case 0:
        		break;
        	case 1:
        		status = Status.TOBESENDT;
        		break;
        	case 2:
        		status = Status.SENDT;
        		break;
        	case 3:
        		status = Status.ANSWERED;
        		break;
        }
        String name = cursor.getString(1);
        Calendar length = Calendar.getInstance();
        length.setTimeInMillis(Long.parseLong(cursor.getString(2)));
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(Long.parseLong(cursor.getString(3)));
        String proposedSpecie = cursor.getString(4);
        String specie = cursor.getString(5);
        return new Track(id, status, name, length, date, proposedSpecie, specie);
    }
	
	public ArrayList<Track> getAllTracks(){
		ArrayList<Track> tracks = new ArrayList<Track>();
		return tracks;
	}
}
