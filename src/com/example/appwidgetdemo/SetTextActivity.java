package com.example.appwidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetTextActivity extends Activity {

	private EditText etContent;
	private Button btnSet;
	public static String content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);

		etContent = (EditText) findViewById(R.id.etContent);
		btnSet = (Button) findViewById(R.id.btnSet);

		btnSet.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startService(new Intent(SetTextActivity.this, MainActivity.UpdateService.class));
				content = etContent.getText().toString();
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

}
