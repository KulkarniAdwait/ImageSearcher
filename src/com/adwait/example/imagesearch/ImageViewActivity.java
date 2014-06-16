package com.adwait.example.imagesearch;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;

public class ImageViewActivity extends Activity {

	SmartImageView ivImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		ivImage = (SmartImageView) findViewById(R.id.ivImageFull);
		String imgUrl = (String) getIntent().getStringExtra("imgUrl");
		
		ivImage.setImageUrl(imgUrl);
	}
}
