package com.example.sqlitecachedemo;

import java.util.Iterator;
import java.util.List;

import com.example.sqlitecachedemo.adapter.ItemAdapter;
import com.example.sqlitecachedemo.entity.DataEntity;
import com.example.sqlitecachedemo.entity.ListEntity;
import com.google.gson.Gson;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FinalActivity {
	private static final String URL = "http://192.172.10.56/data/data.php";
	FinalHttp http;
	ListEntity list;
	@ViewInject(id=R.id.listView)
	private ListView listView;
	FinalDb db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = FinalDb.create(this, "cacheSqlite");
		List<DataEntity> data = db.findAll(DataEntity.class);
		listView.setAdapter(new ItemAdapter(data, this));
//		requestData();
	}

	private void requestData(){
		http = new FinalHttp();
		http.configTimeout(5 * 1000);
		http.configCharset("gbk");
		http.get(URL, new AjaxCallBack<Object>() {
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				super.onLoading(count, current);
				
			}
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				String result = t.toString();
				Gson gson = new Gson();
				list = gson.fromJson(result, ListEntity.class);
				listView.setAdapter(new ItemAdapter(list.getData(), MainActivity.this));
				for (int i = 0; i < list.getData().size(); i++) {
					DataEntity entity = list.getData().get(i);
					db.save(entity);
				}
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
			}
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
