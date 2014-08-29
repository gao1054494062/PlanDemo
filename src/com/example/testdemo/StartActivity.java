package com.example.testdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StartActivity extends Activity {
	
	private String[] planNames = {"计划1","计划2"};
	private String[] planplaces = {"地点1","地点2"};
	private String[] planModel = {"模式1","模式2"};
	private String[] planBeingTime = {"开始时间1","开始时间2"};
	private String[] planEndTime = {"时间1","时间2"};
	private String[] planContent = {"内容1","内容2"};
	private SimpleAdapter adapter;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_main);
		
		
		addListViewItem();

		planOnclickListener();
		planOnLongclickListener();

		Button simple_model = (Button) findViewById(R.id.plan_simple);
		btnSimpleListener(simple_model);
		
		Button complete_model = (Button) findViewById(R.id.plan_complete);
		btnCompleteListener(complete_model);
		
		Button mainSetting = (Button) findViewById(R.id.mainSetting);
		btnSetListener(mainSetting);
		
		Button btnAdd = (Button) findViewById(R.id.plan_add);
		btnAddListener(btnAdd);
	}

	protected void onListItemClick(ListView arg0, View arg1, int arg2, long arg3){
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, AddplanActivity.class);
		startActivity(intent);;
	}
	
	private void planOnclickListener() {
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Bundle bundle = new Bundle();
				TextView textName = (TextView) findViewById(R.id.plan);
				bundle.putString("name", textName.getText().toString());
				TextView textPlace = (TextView) findViewById(R.id.place);
				bundle.putString("place", textPlace.getText().toString());
				TextView textModel = (TextView) findViewById(R.id.model);
				bundle.putString("model", textModel.getText().toString());
				TextView textBeginTime = (TextView) findViewById(R.id.begin_time);
				bundle.putString("beginTime", textBeginTime.getText().toString());
				TextView textEndTime = (TextView) findViewById(R.id.end_time);
				bundle.putString("endTime", textEndTime.getText().toString());
				TextView textContent = (TextView) findViewById(R.id.content);
				bundle.putString("content", textContent.getText().toString());
				
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(StartActivity.this, DisplayPlanActivity.class);
				startActivity(intent);
			}
		});
	}

	private void planOnLongclickListener() {
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {				
				Builder alertDialog = new AlertDialog.Builder(StartActivity.this);
				alertDialog.setTitle("当前计划");
				alertDialog.setMessage("");
				alertDialog.setIcon(R.id.action_settings);
				alertDialog.setPositiveButton("取消",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface alertDialog, int pos){
						;
					}
				});
				alertDialog.setNegativeButton("删除", 
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface alertDialog, int pos){
						;
					}
				});
				alertDialog.setNeutralButton("编辑", 
						new DialogInterface.OnClickListener() {   
                    public void onClick(DialogInterface dialog, int which) {   
                    	Intent intent = new Intent();
        				intent.setClass(StartActivity.this, AddplanActivity.class);
        				startActivity(intent);   
                 	}   	
				});   
				alertDialog.create(); 
			    alertDialog.show();
				return true;
			}
		});
	}

	private void btnSetListener(Button mainSetting) {
		mainSetting.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void btnSimpleListener(Button btn) {
		btn.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				addSimpleListViewItem();
			}
		});
	}
	
	private void btnCompleteListener(Button btn) {
		btn.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				addListViewItem();
			}
		});
	}
	
	private void btnAddListener(Button btnAdd) {
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartActivity.this,AddplanActivity.class);
				startActivity(intent);
			}
		});
	}

	public void addSimpleListViewItem() {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i=0; i<planBeingTime.length; ++i)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("times1", planBeingTime[i]);
			listItem.put("times2", planEndTime[i]);
			listItem.put("contents", planContent[i]);
			lists.add(listItem);
		}
		adapter = new SimpleAdapter(this, lists, 
				R.layout.plan_item_simple, 
				new String[] {"times1","times2","contents"},
				new int[] {R.id.begin_time_simple,R.id.end_time_simple,R.id.content_simple}); 
		listview = (ListView) this.findViewById(R.id.mylist);
		listview.setAdapter(adapter);
	}
	
	public void addListViewItem() {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i=0; i<planNames.length; ++i)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("names", planNames[i]);
			listItem.put("places", planplaces[i]);
			listItem.put("times1", planBeingTime[i]);
			listItem.put("times2", planEndTime[i]);
			listItem.put("contents", planContent[i]);
			listItem.put("models", planModel[i]);
			lists.add(listItem);
		}
		adapter = new SimpleAdapter(this, lists, 
				R.layout.plan_item, 
				new String[] {"names","places","times1","times2",
					"contents","models"},
				new int[] {R.id.plan, R.id.place, R.id.begin_time,
					R.id.end_time,R.id.content, R.id.model}); 
		listview = (ListView) this.findViewById(R.id.mylist);
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch(item.getItemId()){
		case R.id.about_software:
			intent.setClass(StartActivity.this, AboutActivity.class);
			startActivity(intent);
			break;
		case R.id.para_setting:
			intent.setClass(StartActivity.this, SettingActivity.class);
			startActivity(intent);
			break;		
		}
		return true;
	}
	
	
}
