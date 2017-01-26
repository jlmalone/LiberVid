package com.techventus.libervid;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by josephmalone on 16-08-28.
 */
public class VideoService extends Service
{
	private static final String VIDEO_KEY = "VIDEO_KEY";
	private static final String TAG = "VIDEOSERVICE";
	private String mUrl;
	private VideoView mVideoView;

	// internal system services
	WindowManager mWindowManager;
	private NotificationManager mNotificationManager;
	LayoutInflater mLayoutInflater;
	private boolean startedForeground;
	FrameLayout mFrameLayout;

	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		startedForeground = false;

//		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				500,500,
//				WindowManager.LayoutParams.WRAP_CONTENT,
//				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.LEFT;

		mFrameLayout = new FrameLayout(this);


//		mLayoutInflater.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Here is the place where you can inject whatever layout you want.
		View v = mLayoutInflater.inflate(R.layout.video_layout, mFrameLayout);
		mUrl = "http://techslides.com/demos/sample-videos/small.mp4";
		//getIntent().getStringExtra(VIDEO_KEY);
		mVideoView = (VideoView)v.findViewById(R.id.video);
		Uri uri = Uri.parse(mUrl);


		mVideoView.setVideoURI(uri);

		mVideoView.setMediaController(new MediaController(mVideoView.getContext()));
		mVideoView.setVideoURI(uri);

		mWindowManager.addView(mFrameLayout, params);



		new Handler().postDelayed(new Runnable() {
			public void run() {
				mVideoView.requestFocus();
				mVideoView.start();
			}
		}, 1000);
		Log.v
				(TAG, "HELLP WORLD");

	}
}
