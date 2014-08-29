package com.example.testdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DisplayPlanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display_plan);		
		
//		Bundle bundle = getIntent().getExtras();
//		TextView textName = (TextView)findViewById(R.id.plan_detail);
//		textName.setText(bundle.getString("name"));
//		TextView textPlace = (TextView)findViewById(R.id.place_detail);
//		textPlace.setText(bundle.getString("place"));
//		TextView textModel = (TextView)findViewById(R.id.model_detail);
//		textModel.setText(bundle.getString("model"));
//		TextView textBeing = (TextView)findViewById(R.id.begin_time_detail);
//		textBeing.setText(bundle.getString("beginTime"));
//		TextView textEnd = (TextView)findViewById(R.id.end_time_detail);
//		textEnd.setText(bundle.getString("endTime"));
//		TextView textContent = (TextView)findViewById(R.id.content_detail);
//		textContent.setText(bundle.getString("content"));
		
		Button btnReturn = (Button) findViewById(R.id.btnReturn);
		btnReturnListener(btnReturn);
	}

	private void btnReturnListener(Button btnReturn) {
		btnReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DisplayPlanActivity.this, StartActivity.class);
				startActivity(intent);
			}
		});
	}

}
