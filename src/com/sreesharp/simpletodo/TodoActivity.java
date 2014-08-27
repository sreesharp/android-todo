package com.sreesharp.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class TodoActivity extends Activity {

	ArrayList <ToDoItem> items;
	ItemAdapter itemsAdapter;
	ListView lvItems; 
	private final int REQUEST_CODE = 20;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        items = new ArrayList<ToDoItem>();
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ItemAdapter(this, R.layout.list_item, items);
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
		
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				 Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
				 i.putExtra("item", items.get(position)); 
				 i.putExtra("index", position);
				 i.putExtra("type", "edit");
				 startActivityForResult(i, REQUEST_CODE);
				
			}
		});
		
	}

    public void addTodoItem(View v)
    {
		Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
		i.putExtra("type", "new");
		startActivityForResult(i, REQUEST_CODE);
    }
    
    private void readItems()
    {
    	File fileDir = getFilesDir();
    	File todoFile = new File(fileDir, "todo.txt");
    	try
    	{
    		List<String> fileItems = FileUtils.readLines(todoFile);
    		for (String row: fileItems)
    		{
    			String[] parts = row.split("~");
    			
    			ToDoDate date = new ToDoDate(parts[1]);
    			Priority priority = Priority.valueOf(parts[2]);
    			items.add(new ToDoItem(parts[0], date, priority));
    		}
    	}
    	catch(Exception ex)
    	{
    		items = new ArrayList<ToDoItem>();
    		ex.printStackTrace();
    	}
    }
    
    private void saveItems()
    {
    	File fileDir = getFilesDir();
    	File todoFile = new File(fileDir, "todo.txt");
    	try
    	{
    		ArrayList<String> rows = new ArrayList<String>();
    		for (ToDoItem item: items)
    		{
    			String row = item.getTitle()+"~"+item.getDueDate().toString()+"~"+ item.getPriority().toString();
    			rows.add(row);
    		}
    		FileUtils.writeLines(todoFile,rows);
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      // REQUEST_CODE is defined above
      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
         ToDoItem item = (ToDoItem) data.getSerializableExtra("item");
         if(data.getExtras().getString("type").equals("new"))
         {
        	 items.add(item);
         }
         else
         {
        	 int index = data.getExtras().getInt("index");
        	 items.set(index, item);
         }
         saveItems();
         itemsAdapter.notifyDataSetChanged();
      }
    } 


}
