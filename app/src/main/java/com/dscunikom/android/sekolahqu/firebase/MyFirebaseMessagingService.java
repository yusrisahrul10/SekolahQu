package com.dscunikom.android.sekolahqu.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.detail.acara.DetailAcaraActivity;
import com.dscunikom.android.sekolahqu.detail.berita.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.detail.prestasi.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    SessionManager sessionManager;
    Context context;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().size());
            
            String activity = remoteMessage.getData().get("click_action");
            String body = remoteMessage.getData().get("body");
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String , String> sekolah = sessionManager.getSekolahPref();
            String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF);
            boolean session = sessionManager.getNotif();
            String testing = remoteMessage.getData().get("whosend");
            if(session == true && testing.equals(id_sekolah)){
                Log.e("Berhasil ","Notification Testing" +String.valueOf(testing));
                sendNotification(body,activity , remoteMessage);
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


//            String activity = remoteMessage.getNotification().getClickAction();
//            sendNotification(remoteMessage.getNotification().getBody(),activity);
        }
//        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String body , String activity , RemoteMessage remoteMessage ) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        Intent intentNew = sendMessage(activity,intent,remoteMessage);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentNew,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("SekolahQu")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(body, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[] {100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationBuilder.setChannelId(body);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        notificationManager.notify(1, notificationBuilder.build());
    }

    private Intent sendMessage(String activity, Intent intent , RemoteMessage remoteMessage){
        if(activity.equals("ACARAACTIVITY")){
            intent = new Intent(this,DetailAcaraActivity.class);
            intent.putExtra("id_acara",remoteMessage.getData().get("id_acara"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if(activity.equals("BERITAACTIVITY")){
            intent = new Intent(this,DetailBeritaActivity.class);
            intent.putExtra("id_berita",remoteMessage.getData().get("id_berita"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if(activity.equals("PRESTASIACTIVITY")){
            intent = new Intent(this,DetailPrestasiActivity.class);
            intent.putExtra("id_prestasi",remoteMessage.getData().get("id_prestasi"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return intent;
    }
}
