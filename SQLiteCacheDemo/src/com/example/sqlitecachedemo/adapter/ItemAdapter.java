package com.example.sqlitecachedemo.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.sqlitecachedemo.R;
import com.example.sqlitecachedemo.entity.DataEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter{
	private List<DataEntity> list;
	private Context context;
	private FinalBitmap bitmap;
	public ItemAdapter(List<DataEntity> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		bitmap = FinalBitmap.create(context);
		bitmap.configLoadfailImage(R.drawable.ic_launcher);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
			holder.bookText = (TextView) convertView.findViewById(R.id.item_bookName);
			holder.priceText = (TextView) convertView.findViewById(R.id.item_price);
			holder.authorText = (TextView) convertView.findViewById(R.id.item_author);
			holder.image = (ImageView) convertView.findViewById(R.id.item_image);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bookText.setText(list.get(position).getBookName());
		holder.priceText.setText(list.get(position).getPrice());
		holder.authorText.setText(list.get(position).getPrice());
		bitmap.display(holder.image, "http://192.172.10.56/data/" + list.get(position).getImage());
		return convertView;
	}
	class ViewHolder{
		TextView bookText, priceText, authorText;
		ImageView image;
	}

}
