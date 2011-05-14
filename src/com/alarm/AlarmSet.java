package com.alarm;

import java.util.ArrayList;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmSet extends BroadcastReceiver {
	public static ArrayList<alarmItem> alarmList = alarmHome.alarmList;
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Intent Intent1 = new Intent(arg0,PlayMP3.class);
		Intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(Intent1);
		alarmList.remove(arg1.getIntExtra("no", 5));
	}
}
