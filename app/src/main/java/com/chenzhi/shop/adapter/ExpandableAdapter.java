package com.chenzhi.shop.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.chenzhi.shop.R;
import com.chenzhi.shop.bean.GlobalData;

public class ExpandableAdapter implements ExpandableListAdapter {

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

	@Override
	public long getCombinedChildId(long groupId, long childId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.expandable_child_item, null);
			TextView tv = (TextView) view.findViewById(R.id.expandable_child);
			view.setTag(tv);
		}
		String data = GlobalData.type2[groupPosition][childPosition];
		((TextView)view.getTag()).setText(data);
		return view;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return GlobalData.type2[groupPosition].length;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view == null){
			view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
			TextView tv = (TextView) view.findViewById(android.R.id.text1);
			view.setTag(tv);
		}
		String data = GlobalData.type[groupPosition];
		((TextView)view.getTag()).setText(data);
		//GlobalData.type
		return view;
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return GlobalData.type.length;
	}
	
	private Context context;

	public ExpandableAdapter(Context context) {
		super();
		this.context = context;
	}
	

}
