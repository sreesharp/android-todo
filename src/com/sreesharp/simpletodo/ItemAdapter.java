package com.sreesharp.simpletodo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<ToDoItem> {

	private ArrayList<ToDoItem> items;
	Context context;
	
	public ItemAdapter(Context context, int textViewResourceId,
			ArrayList<ToDoItem>items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.context = context;
	}

	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
        }
        ToDoItem item = items.get(position);
        if (item != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
                  tt.setText(item.getTitle());                            }
            if(bt != null){
                  bt.setText("Due: "+ item.getDueDate().toString());
            }
            ImageView icon = (ImageView)v.findViewById(R.id.icon);
            if(item.getPriority() == Priority.High)
            	icon.setImageResource(R.drawable.high);
            else  if(item.getPriority() == Priority.Normal)
            	icon.setImageResource(R.drawable.normal);
            if(item.getPriority() == Priority.Low)
            	icon.setImageResource(R.drawable.low);
        }
        return v;
    }
}
