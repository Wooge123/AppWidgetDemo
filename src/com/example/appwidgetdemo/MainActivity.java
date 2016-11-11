package com.example.appwidgetdemo;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MainActivity extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(new Intent(context, UpdateService.class));
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			Intent configIntent = new Intent(context, SetTextActivity.class);
			PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);
			remoteViews.setOnClickPendingIntent(R.id.btnSet, configPendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		}
	}

	public static class UpdateService extends Service {

		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}

		@Override
		public void onStart(Intent intent, int startId) {
			super.onStart(intent, startId);
			
			/* 取得Widget的View */
			RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.activity_main);
			updateViews.setTextViewText(R.id.text, SetTextActivity.content);

			/* 更新widget */
			ComponentName thisWidget = new ComponentName(this, MainActivity.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			manager.updateAppWidget(thisWidget, updateViews);
		}

	}
}
