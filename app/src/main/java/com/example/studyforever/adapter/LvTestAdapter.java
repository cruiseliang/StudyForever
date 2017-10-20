package com.example.studyforever.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studyforever.R;

import java.util.List;

public class LvTestAdapter extends BaseAdapter {
	private Context context;
	private List<String> list;

	public LvTestAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		if (list == null) {
			return null;
		}
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_test_lv, parent, false);

			holder.content = (TextView) convertView.findViewById(R.id.tv_test_lv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.content.setText(list.get(position));
		return convertView;
	}

	public static class ViewHolder {

		TextView content;
	}
}
