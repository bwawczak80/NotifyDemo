package com.ebookfrenzy.notifydemo
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

open class MainActivity : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

                createNotificationChannel("com.ebookfrenzy.notifydemo.news", "NotifyDemo News","Example News Channel")

        val button1: Button = findViewById(R.id.buttonOne)
        button1.setOnClickListener {
            sendNotification() }

    }

    private fun createNotificationChannel(id: String, name: String, description: String){
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        channel.enableLights( true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
        notificationManager?.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val notificationID = 101
        val resultIntent = Intent(this, ResultActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,resultIntent,0)
        val channelID = "com.ebookfrenzy.notifydemo.news"
        val icon: Icon = Icon.createWithResource(this,android.R.drawable.ic_dialog_info)

       val action: Notification.Action = Notification.Action.Builder(icon,"open",pendingIntent).build()

        val notification = Notification.Builder(this@MainActivity, channelID)
                .setContentText("This is my notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelID)
                .setContentIntent(pendingIntent)
                .setNumber(10)
                .setActions(action)
                .build()
        notificationManager?.notify(notificationID,notification)


    }


}