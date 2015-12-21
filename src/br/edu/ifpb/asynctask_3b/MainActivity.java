package br.edu.ifpb.asynctask_3b;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifpb.asynctask_3b.R;
import br.edu.ifpb.asynctask_3b.LoginAsyncTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button asyncTaskButton = (Button)findViewById(R.id.enviarButton);
        asyncTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("NotificationWearApp", "Clique no botão da AsyncTask");

                EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
                String nome = nomeEditText.getText().toString();

                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(v.getContext());
                String[] valores = {nome};

                loginAsyncTask.execute(valores);
            }
        });
    }
}
