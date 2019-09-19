package cachet.plugins.notifications;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.os.Bundle;

/**
 * Notification listening service. Intercepts notifications if permission is given to do so.
 */
public class NotificationListener extends NotificationListenerService {

    public static String NOTIFICATION_INTENT = "notification_event";
    public static String NOTIFICATION_PACKAGE_NAME = "package_name";
    public static String NOTIFICATION_PACKAGE_TITLE = "package_title";
    public static String NOTIFICATION_PACKAGE_TEXT = "package_text";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();
        String titleData = "";
        String textData = "";

        Bundle extras = sbn.getNotification().extras;
        if(extras.getString("android.title")!=null){
            titleData = extras.getString("android.title");
        }else{
            titleData = "";
        }
        if(extras.getCharSequence("android.text")!=null){
            textData = extras.getCharSequence("android.text").toString();
        }else{
            textData = "";
        }

        Intent intent = new  Intent(NOTIFICATION_INTENT);
        intent.putExtra(NOTIFICATION_PACKAGE_NAME, packageName);
        intent.putExtra(NOTIFICATION_PACKAGE_TITLE, titleData);
        intent.putExtra(NOTIFICATION_PACKAGE_TEXT, textData);
        sendBroadcast(intent);
    }
}