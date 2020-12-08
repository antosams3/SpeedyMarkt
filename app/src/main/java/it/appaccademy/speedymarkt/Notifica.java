package it.appaccademy.speedymarkt;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class Notifica extends Fragment {
    Button btNotification;
    private NotificationManager mNotificationManager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualizzaordini, container, false);
        btNotification = view.findViewById(R.id.bt_notification);
        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Puoi recarti in negozio per il ritiro della spesa.";


                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext(), "Notifica_001");
                Intent ii = new Intent(getContext(), Notifica2.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.bigText(message);
                bigText.setBigContentTitle("SpeedyMarkt: il tuo ordine è stato preso in carico!");
                bigText.setSummaryText("Ordine preso in carico!");

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.drawable.ic_notifica);
                mBuilder.setContentTitle("Your Title");
                mBuilder.setContentText("Your text");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);

                mNotificationManager =
                        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelId = "Notifica_001";
                    NotificationChannel channel = new NotificationChannel(
                            channelId,
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_HIGH);
                    mNotificationManager.createNotificationChannel(channel);
                    mBuilder.setChannelId(channelId);
                }

                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        return view;
    }
}



