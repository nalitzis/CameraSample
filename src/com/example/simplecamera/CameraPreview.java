//package com.example.simplecamera;
//
//
//import android.content.Context;
//import android.hardware.Camera;
//import android.util.AttributeSet;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//public class CameraPreview extends SurfaceView {
//
//	private SurfaceHolder surfaceHolder;
//	
//	public CameraPreview(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		
//		this.surfaceHolder = this.getHolder();
//		this.surfaceHolder.addCallback(new SurfaceCallback());
//		this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//	}
//	
//	private class SurfaceCallback implements SurfaceHolder.Callback {
//
//		@Override
//		public void surfaceChanged(SurfaceHolder holder, int format, int width,
//				int height) {
//			initPreview(width, height);
//			startPreview();
//		}
//
//		@Override
//		public void surfaceCreated(SurfaceHolder holder) {
//
//		}
//
//		@Override
//		public void surfaceDestroyed(SurfaceHolder holder) {
//
//		}
//
//	}
//	
//	private void initPreview(int width, int height) {
//		if (camera != null && surfaceHolder.getSurface() != null) {
//			try {
//				camera.setPreviewDisplay(surfaceHolder);
//			} catch (Throwable t) {
//
//			}
//
//			if (!cameraConfigured) {
//				Camera.Parameters parameters = camera.getParameters();
//				Camera.Size size = getBestPreviewSize(width, height, parameters);
//
//				if (size != null) {
//					parameters.setPreviewSize(size.width, size.height);
//					camera.setParameters(parameters);
//					cameraConfigured = true;
//				}
//			}
//		}
//	}
//
//}
