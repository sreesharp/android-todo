package com.sreesharp.simpletodo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class EditItemActivity extends Activity {

	int index; //index of the item which is modified
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		index = getIntent().getIntExtra("index",0);
		String strItem = getIntent().getStringExtra("item");
		EditText etName = (EditText) findViewById(R.id.etModifiedItem);
		etName.setText(strItem);
	}

	
	public void onSubmit(View v) {
	  EditText etName = (EditText) findViewById(R.id.etModifiedItem);
	  Intent data = new Intent();
	  data.putExtra("item", etName.getText().toString());
	  data.putExtra("index", index);
	  setResult(RESULT_OK, data); 
	  finish(); 
	} 
	

}
