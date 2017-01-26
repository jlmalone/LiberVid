package com.techventus.libervid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

	private static final String TAG = "MAINACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v
				(TAG, "START WORLD");
		startService(new Intent(this, VideoService.class));

	}
}
