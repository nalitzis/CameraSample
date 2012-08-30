package com.example.simplecamera;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleCameraActivity extends Activity {

	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera camera;

	private boolean inPreview=false;
	private boolean cameraConfigured=false;
	  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_camera);

		this.surfaceView = (SurfaceView) this.findViewById(R.id.preview);
		this.surfaceHolder = this.surfaceView.getHolder();
		this.surfaceHolder.addCallback(new SurfaceCallback());
		this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	 @Override
	  public void onResume() {
	    super.onResume();
	    
	    camera=Camera.open();
	    startPreview();
	  }
	    
	  @Override
	  public void onPause() {
	    if (inPreview) {
	      camera.stopPreview();
	    }
	    
	    camera.release();
	    camera=null;
	    inPreview=false;
	          
	    super.onPause();
	  }
	  

	private class SurfaceCallback implements SurfaceHolder.Callback {

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			initPreview(width, height);
			startPreview();
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {

		}

	}

	private void initPreview(int width, int height) {
		if (camera != null && surfaceHolder.getSurface() != null) {
			try {
				camera.setPreviewDisplay(surfaceHolder);
			} catch (Throwable t) {

			}

			if (!cameraConfigured) {
				Camera.Parameters parameters = camera.getParameters();
				Camera.Size size = getBestPreviewSize(width, height, parameters);

				if (size != null) {
					parameters.setPreviewSize(size.width, size.height);
					camera.setParameters(parameters);
					cameraConfigured = true;
				}
			}
		}
	}

	private Camera.Size getBestPreviewSize(int width, int height,
			Camera.Parameters parameters) {
		Camera.Size result = null;

		for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
			if (size.width <= width && size.height <= height) {
				if (result == null) {
					result = size;
				} else {
					int resultArea = result.width * result.height;
					int newArea = size.width * size.height;

					if (newArea > resultArea) {
						result = size;
					}
				}
			}
		}

		return (result);
	}

	private void startPreview() {
		if (cameraConfigured && camera != null) {
			camera.startPreview();
			inPreview = true;
		}
	}

}
