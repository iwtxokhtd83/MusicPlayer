package com.iwtxokhtd.android.musicplayer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.util.ContextUtil;

/**
 * Created by tujiyue on 27/6/17.
 */

public class MusicWidgetProvider extends AppWidgetProvider {

    public static final String WIDGET_PLAY_CURRENT_ACTION = "widget.play.current.action";
    public static final String WIDGET_PLAY_CURRENT_SERVICE = "com.iwtxokhtd.android.musicplayer.widget.play.current.service";
    public static final String WIDGET_PLAY_NEXT_ACTION = "widget.play.next.action";
    public static final String WIDGET_PLAY_NEXT_SERVICE = "com.iwtxokhtd.android.musicplayer.widget.play.next.service";
    public static final String WIDGET_PLAY_PREVIOUS_ACTION = "widget.play.previous.action";
    public static final String WIDGET_PLAY_PREVIOUS_SERVICE = "com.iwtxokhtd.android.musicplayer.widget.play.previous.service";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null) {
            String action = intent.getAction();
            if (TextUtils.equals(action, WIDGET_PLAY_CURRENT_ACTION)) {
                ContextUtil.sendBroadCast(context, WIDGET_PLAY_CURRENT_SERVICE);
            } else if (TextUtils.equals(action, WIDGET_PLAY_NEXT_ACTION)) {
                ContextUtil.sendBroadCast(context, WIDGET_PLAY_NEXT_SERVICE);
            } else if (TextUtils.equals(action, WIDGET_PLAY_PREVIOUS_ACTION)) {
                ContextUtil.sendBroadCast(context, WIDGET_PLAY_PREVIOUS_SERVICE);
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_next, getPendingIntent(context,WIDGET_PLAY_NEXT_ACTION));
        remoteViews.setOnClickPendingIntent(R.id.widget_ib_play, getPendingIntent(context,WIDGET_PLAY_CURRENT_ACTION));
        remoteViews.setOnClickPendingIntent(R.id.widget_preview, getPendingIntent(context,WIDGET_PLAY_PREVIOUS_ACTION));
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    private PendingIntent getPendingIntent(Context context, String action) {
        Intent mIntent = new Intent();
        mIntent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

    }
}
