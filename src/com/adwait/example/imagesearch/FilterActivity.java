package com.adwait.example.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class FilterActivity extends Activity {

	Spinner spImageSize;
	Spinner spColorFilter;
	Spinner spImageType;
	EditText etSite;
	SearchFilter sf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		
		spImageSize = (Spinner) findViewById(R.id.spImageSize);
		spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
		spImageType = (Spinner) findViewById(R.id.spImageType);
		etSite = (EditText) findViewById(R.id.etSite);
		
		sf = (SearchFilter) getIntent().getSerializableExtra("filter");
		if(sf != null && sf.isSet()) {
			if(sf.getSize() != "") {
				setSpinnerToValue(spImageSize, sf.getSize());
			}
			
			if(sf.getColor() != "") {
				setSpinnerToValue(spColorFilter, sf.getColor());
			}
			
			if(sf.getType() != "") {
				setSpinnerToValue(spImageType, sf.getType());
			}
			
			if(sf.getSite() != "") {
				etSite.setText(sf.getSite());
			}
		}
		
	}
	
	public void onSave(View v) {
		if(spImageSize.getSelectedItem().toString() != "") {
			sf.setSize(spImageSize.getSelectedItem().toString());
		}
		
		if(spColorFilter.getSelectedItem().toString() != "") {
			sf.setColor(spColorFilter.getSelectedItem().toString());
		}
		
		if(spImageType.getSelectedItem().toString() != "") {
			sf.setType(spImageType.getSelectedItem().toString());
		}
		
		if(etSite.getText().toString() != "") {
			sf.setSite(etSite.getText().toString());
		}
		
		Intent data = new Intent();
		data.putExtra("filter", sf);
		setResult(RESULT_OK, data);
		finish();
	}
	
	public void setSpinnerToValue(Spinner spinner, String value) {
		int index = 0;
		SpinnerAdapter adapter = spinner.getAdapter();
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(value)) {
				index = i;
			}
		}
		spinner.setSelection(index);
	}
}
