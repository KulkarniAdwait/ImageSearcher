package com.adwait.example.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult {
	String imgUrl;
	String thumbUrl;
	
	public ImageResult(JSONObject result) {
		try {
			imgUrl = result.getString("url");
			thumbUrl = result.getString("tbUrl");
		}
		catch (JSONException je) {
			Log.d("DEBUG", "JSON parse error");
		}
	}
	
	@Override
	public String toString() {
		return this.thumbUrl;
	}

	public static ArrayList<ImageResult> fromJSONArray(JSONArray results) {	
		ArrayList<ImageResult> rList = new ArrayList<ImageResult>();
		for(int i=0; i< results.length(); i+=1 ) {
			try {
				rList.add(new ImageResult(results.getJSONObject(i)));
			}
			catch (JSONException je) {
				je.printStackTrace();
			}
		}
		return rList;
	}

	public String getThumbUrl() {
		return this.thumbUrl;
	}
	
	public String getUrl() {
		return this.imgUrl;
	}
}
