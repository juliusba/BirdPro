package service;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import android.os.Environment;
import android.util.Log;

public class FileService {

	private static final String LOG_TAG = "FileService";
	/*
	private static FileService _fileService;
	
	private FileService(){}
	
	public static FileService getInstance(){
		if(_fileService==null){
			_fileService = new FileService();
			Log.i(LOG_TAG, "Service created");
		}
		return _fileService;
	}
	*/
	public static String createFileName() {
		String directoryPath = getDirectory();
	    
	    DateFormat dateFormat = new SimpleDateFormat("HH.mm-dd.MM.yyyy");
	    Calendar cal = Calendar.getInstance();
	    String fileName = directoryPath + "/rec_" + dateFormat.format(cal.getTime());
	    
	    int num = 1;
	    File file = new File(fileName + ".3gp");
	    while(file.exists()){
	    	num++;
	    	file = new File(fileName + "(" + num + ").3gp");
	    }
	    if(num > 1){
	    	fileName += "(" + num + ")";
	    }
	    fileName += ".3gp";
	    Log.i(LOG_TAG, "File created: " + fileName);
	    
	    
        return fileName;
	}
	
	public static String[] getAllFileNames() {
	    File directory = new File(getDirectory());
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				//File sel = new File(dir, filename);
                return filename.contains(".3gp");// || sel.isDirectory();
			}
		};
	    return directory.list(filter);
	}
	
	private static String getDirectory(){
		String state = android.os.Environment.getExternalStorageState();
	    if(!state.equals(android.os.Environment.MEDIA_MOUNTED))  {
	    	Log.e(LOG_TAG, "SD Card is not mounted.  It is " + state + ".");
	    }

	    String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/BirdPro";
	    // make sure the directory we plan to store the recording in exists
	    File directory = new File(directoryPath);
	    if (!directory.exists() && !directory.mkdirs()) {
	    	Log.e(LOG_TAG, "Path to file could not be created.");
	    }
	    return directoryPath;
	}
	
}
