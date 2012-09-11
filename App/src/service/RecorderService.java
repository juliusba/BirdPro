package service;

import java.io.IOException;
import java.util.Calendar;

import model.Model;

import android.media.MediaRecorder;
import android.util.Log;

public class RecorderService {

	private static RecorderService _recorderService;
	private static final String LOG_TAG = "RecordService";
	private MediaRecorder recorder = null;
	private String fileName;
	private long recordStart;
	
    private RecorderService(){}
    
    public static RecorderService getInstance(){
    	if(_recorderService==null){
    		_recorderService = new RecorderService();
    		Log.i(LOG_TAG, "Service created");
		}
		return _recorderService;
    }
    
    public void startRecording() {
    	fileName = FileService.getInstance().createFileName();
    	recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recordStart = Calendar.getInstance().getTimeInMillis();
        recorder.start();
        Log.i(LOG_TAG, "recorder startet");
    }

    public void stopRecording() {
        recorder.stop();
        long length = Calendar.getInstance().getTimeInMillis() - recordStart;
        recorder.reset();
        recorder.release();
        recorder = null;
        Model.getInstance().addTrack(fileName, length, Calendar.getInstance().getTimeInMillis());
    }
	
}