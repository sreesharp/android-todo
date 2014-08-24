package com.sreesharp.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Build;

public class TodoActivity extends Activity {

	ArrayList <String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        items = new ArrayList<String>();
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }


    private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			items.remove(position);
			itemsAdapter.notifyDataSetChanged();
			saveItems();
			return true;
		}
		});
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void addTodoItem(View v)
    {
    	EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
    	items.add(etNewItem.getText().toString());
    	etNewItem.setText("");
    	saveItems();
    }
    
    private void readItems()
    {
    	File fileDir = getFilesDir();
    	File todoFile = new File(fileDir, "todo.txt");
    	try
    	{
    		items = new ArrayList<String>(FileUtils.readLines(todoFile));
    	}
    	catch(IOException ex)
    	{
    		items = new ArrayList<String>();
    		ex.printStackTrace();
    	}
    }
    
    private void saveItems()
    {
    	File fileDir = getFilesDir();
    	File todoFile = new File(fileDir, "todo.txt");
    	try
    	{
    		FileUtils.writeLines(todoFile,items);
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
    }

}
