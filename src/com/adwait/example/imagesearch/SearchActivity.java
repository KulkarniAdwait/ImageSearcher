package com.adwait.example.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

	EditText etImageQuery;
	Button btnSearch;
	GridView gvImages;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	SearchFilter sf = new SearchFilter();
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initializeControls();
		
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvImages.setAdapter(imageAdapter);
		
		gvImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				Intent i = new Intent(getApplicationContext(), ImageViewActivity.class);
				i.putExtra("imgUrl", imageResults.get(position).getUrl());
				startActivity(i);
			}
			
		});
	}
	
	public void onFilter(MenuItem mi) {
		Intent i = new Intent(getApplicationContext(), FilterActivity.class);
		i.putExtra("filter", sf);
		startActivityForResult(i, 200);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
	  if (resultCode == RESULT_OK && requestCode == 200) {
	     // Extract name value from result extras
	     sf = (SearchFilter) data.getExtras().getSerializable("filter");
	     // Toast the name to display temporarily on screen
	     //Toast.makeText(this, sf.getImageSize(), Toast.LENGTH_SHORT).show();
	     searchImages();
	  }
	} 
	
	private void initializeControls() {
		etImageQuery = (EditText) findViewById(R.id.etImageQuery);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvImages = (GridView) findViewById(R.id.gvImages);	
	}

	public void onImageSearch(View v) {
		//Toast.makeText(SearchActivity.this, etImageQuery.getText().toString(), Toast.LENGTH_SHORT).show();
		searchImages();
	}
	
	private void searchImages() {
		AsyncHttpClient client = new AsyncHttpClient();
		String googImageUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8";
		
		StringBuilder completeRequest = new StringBuilder();
		completeRequest.append(googImageUrl);
		String query = "&q=" + Uri.encode(etImageQuery.getText().toString());
		completeRequest.append(query);
		
		if(sf != null && sf.isSet()) {
			if(sf.getSize() != "") {
				completeRequest.append("&imgsz=" + Uri.encode(sf.getSize()));
			}
			
			if(sf.getColor() != "") {
				completeRequest.append("&imgcolor=" + Uri.encode(sf.getColor()));
			}
			
			if(sf.getType() != "") {
				completeRequest.append("&imgtype=" + Uri.encode(sf.getType()));
			}
			
			if(sf.getSite() != "") {
				completeRequest.append("&as_sitesearch=" + Uri.encode(sf.getSite()));
			}
		}
		
		Log.d("DEBUG", completeRequest.toString());
		client.get(completeRequest.toString(), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray results = null;
				try {
					results = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					imageAdapter.addAll(ImageResult.fromJSONArray(results));
					Log.d("DEBUG", results.toString());
				}
				catch(JSONException je) {
					je.printStackTrace();
				}
			}
		});
	}
}
