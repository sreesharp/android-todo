package com.sreesharp.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditItemActivity extends Activity {

	int index; //index of the item which is modified
	boolean isNew; //Indicate whether the operation is new entry or edit entry
	
	DatePicker dtPkr;
	RadioGroup rdGroup;
	EditText etTitle; //ToDo title
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		etTitle = (EditText) findViewById(R.id.etModifiedItem);
		dtPkr = (DatePicker)findViewById(R.id.datePicker1);
		rdGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		
		if(getIntent().getStringExtra("type").equals("new"))
		{
			isNew = true;
			etTitle.setHint(R.string.new_item);
		}
		else
		{
			isNew = false;
			index = getIntent().getIntExtra("index",0);
			ToDoItem item = (ToDoItem) getIntent().getSerializableExtra("item");
			etTitle.setText(item.getTitle());
			dtPkr.init(item.getDueDate().getYear(), item.getDueDate().getMonth()-1, item.getDueDate().getDay(), null);
			
			if(item.getPriority() == Priority.High)
				((RadioButton) rdGroup.getChildAt(0)).setChecked(true);
			else if(item.getPriority() == Priority.Normal)
				((RadioButton) rdGroup.getChildAt(1)).setChecked(true);
			else if(item.getPriority() == Priority.Low)
				((RadioButton) rdGroup.getChildAt(2)).setChecked(true);
				
		}
	}

	
	public void onSubmit(View v) {
		etTitle = (EditText) findViewById(R.id.etModifiedItem);
		
		Intent data = new Intent();
	  
		ToDoDate date = new ToDoDate(dtPkr.getYear(), dtPkr.getMonth()+1, dtPkr.getDayOfMonth());
	  
		Priority priority ;
		if(rdGroup.getCheckedRadioButtonId() == R.id.radio0)
			priority = Priority.High;
		else if(rdGroup.getCheckedRadioButtonId() == R.id.radio1)
			priority = Priority.Normal;
		else 
			priority = Priority.Low;
		
		ToDoItem item = new ToDoItem(etTitle.getText().toString(), date, priority);
		data.putExtra("item",item );
	  
		if(!isNew)
		{
		  data.putExtra("index", index);
		  data.putExtra("type", "edit");
		}
		else
		  data.putExtra("type", "new");
	  
		setResult(RESULT_OK, data); 
		finish(); 
	} 
	

}
