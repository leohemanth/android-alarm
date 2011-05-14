package com.alarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class alarmListView extends Activity {
	public ArrayList<alarmItem> alarmList;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarmlist);
		alarmList = alarmHome.alarmList;
		final ListView lv = (ListView) findViewById(R.id.listView1);
		// create the grid item mapping
		lv.setClickable(true);

		String[] from = new String[] { "rowid", "col_1" };
		int[] to = new int[] { R.id.textView1, R.id.textView2 };
		// prepare the list of all records
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < alarmList.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("rowid", i + "    ");
			map.put("col_1", alarmList.get(i).date);
			fillMaps.add(map);
		}
		// fill in the grid_item layout
		final SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
				R.layout.alarmelement, from, to);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:

							AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

							am.cancel(alarmList.get(arg2).pi);
							alarmList.remove(arg2);
							//should be changed
				            Intent intent = new Intent();
				            intent.setClass(alarmListView.this, alarmListView.class);
				            startActivity(intent);
				            finish();
							break;

						case DialogInterface.BUTTON_NEGATIVE:
							// No button clicked
							break;
						}
					}
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(
						alarmListView.this);
				builder.setMessage("Are you sure?")
						.setPositiveButton("Yes", dialogClickListener)
						.setNegativeButton("No", dialogClickListener).show();

			}
		});
	}

}
