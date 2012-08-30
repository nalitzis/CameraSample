package com.example.simplecamera;


import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView {

	private SurfaceHolder surfaceHolder;
	
	private Camera camera;
	
	private boolean inPreview=false;
	private boolean cameraConfigured=false;
	
	
	public CameraPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.surfaceHolder = this.getHolder();
		this.surfaceHolder.addCallback(new SurfaceCallback());
		this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
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
	
	
	public void startPreview() {
		if (cameraConfigured && camera != null) {
			camera.startPreview();
			inPreview = true;
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
	
	private Camera.Size getBestPreviewSize(int w, int h, Camera.Parameters parameters){
		Camera.Size result = null;
		for(Camera.Size size : parameters.getSupportedPreviewSizes()){
			if(size.width <= w && size.height <= h){
				if(result == null)
					result = size;
				else{
					int resultArea = result.height * result.width;
					int newArea = size.height * size.width;
					if(newArea > resultArea)
						result = size;
				}
			}
		}
		return result;
	}
	
	public void initCamera(){
		 camera=Camera.open();
		 camera.setDisplayOrientation(90);
	}
	
	public void releaseCamera(){
		if (inPreview) {
		      camera.stopPreview();
		    }
		    
		camera.release();
		camera=null;
		inPreview=false;
	}

}
