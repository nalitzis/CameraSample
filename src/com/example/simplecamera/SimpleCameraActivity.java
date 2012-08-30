package com.example.simplecamera;

import android.os.Bundle;
import android.app.Activity;


public class SimpleCameraActivity extends Activity {

	private CameraPreview cameraPreview;
	
	  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_camera);

		this.cameraPreview = (CameraPreview) this.findViewById(R.id.preview);
		
	}
	
	 @Override
	  public void onResume() {
	    super.onResume();
	    
	    cameraPreview.initCamera();
	    cameraPreview.startPreview();
	    
	  }
	    
	  @Override
	  public void onPause() {
		  cameraPreview.releaseCamera();
		  super.onPause();
	  }
	  
}
