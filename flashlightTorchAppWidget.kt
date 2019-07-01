package com.hungrok.flashlight

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 <위젯>
 . 원래는 스몰 어플리케이션 의미
 . 안드로이드 framework 에서 위젯은 App launch 없이 (activity 실행없이) 실행되는
   app 이 지니는 broadcast receiver 와 service 기능 임, UI 는 remoteView 개념으로 적용
 . UI 없는 background task App 이 BR 과 service 를 지니는 것과 비슷한 개념 (booting 후 살아나는 ..)
 . AppWidgetProvider 는 broadcast receiver 를 상속받는다 (manifest 에서 Receiver 가 등록된다)
 . 즉 framework 에서 시스템 이벤트를 통하여 위젯이 활성화 되고 해당 서비스를 start 시킨다
 . 별도 layout 을 지니며, remoteView 개념으로 UI 를 가져가며, 한정된 layout 과 view 만 사용가능
 . res/xml 하부에 widget_info 를 지닌다
 **/
class TorchAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {

            System.out.printf("TorchAppWidget-updateAppWidget \n")
            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.torch_app_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Intent 를 통하여 명시적 service (startService)
            val intent = Intent(context, TorchService::class.java)
            intent.action = "widget" // app 에서 호출되는 경우와 구분을 위한 text 정보
            val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

            // 위젯에서 이벤트 발생시 불려야 하는 pendingIntent 등록
            views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

