package com.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

class alarmItem {
	PendingIntent pi;
	String date;
}

public class alarmHome extends Activity {
	public static ArrayList<alarmItem> alarmList = new ArrayList<alarmItem>();

	private OnClickListener mOneShotListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(alarmHome.this, AlarmSet.class);
			intent.putExtra("no", alarmList.size());
			PendingIntent sender = PendingIntent.getBroadcast(alarmHome.this,
					alarmList.size(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
			DatePicker d = (DatePicker) findViewById(R.id.datePicker1);
			TimePicker t = (TimePicker) findViewById(R.id.timePicker1);

			Calendar calendar = Calendar.getInstance();
			calendar.set(d.getYear(), d.getMonth(), d.getDayOfMonth(),
					t.getCurrentHour(), t.getCurrentMinute(), 0);
			String DATE_FORMAT = "dd/MM/yyyy kk-mm";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			alarmItem a = new alarmItem();
			a.date = sdf.format(calendar.getTime());
			a.pi = sender;

			if (Calendar.getInstance().compareTo(calendar) < 0) {
				// Schedule the alarm!
				alarmList.add(a);
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
						sender);

				Toast.makeText(alarmHome.this, "alarm set", Toast.LENGTH_LONG)
						.show();
			} else {
				Toast.makeText(alarmHome.this, "Time passed", Toast.LENGTH_LONG)
						.show();
			}
		}
	};

	private OnClickListener mOneShotListener1 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(alarmHome.this, AlarmSet.class);
			PendingIntent sender = PendingIntent.getBroadcast(alarmHome.this,
					0, intent, 0);

			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.cancel(sender);

			Toast.makeText(alarmHome.this, "alarm cancel", Toast.LENGTH_LONG)
					.show();
		}
	};

	private OnClickListener mOneShotListener2 = new OnClickListener() {
		public void onClick(View v) {
			Intent Intent1 = new Intent(alarmHome.this, alarmListView.class);
			startActivity(Intent1);
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(mOneShotListener);
		button.setText("set alarm");
		Button button1 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(mOneShotListener1);
		button1.setText("cancel alarm");
		Button button2 = (Button) findViewById(R.id.button3);
		button2.setOnClickListener(mOneShotListener2);
		button2.setText("alarm list");
	}

}
