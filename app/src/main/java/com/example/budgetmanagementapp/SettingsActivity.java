package com.example.budgetmanagementapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends BaseActivity {
    CardView btn;
    TextView txtView;
    Button btnLogout;
    AudioManager am;
    Switch onOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.settings_activity);
        btn = findViewById(R.id.btnSelRingtone);
        txtView = findViewById(R.id.tvRingtone);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        onOff = (Switch) findViewById(R.id.vibswitch);
        onOff.setChecked(false);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save.save(getApplicationContext(),"session","false");
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(onOff.isChecked()){
                    am.setRingerMode(1);

                }
                else
                {
                    am.setRingerMode(2);

                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to select Ringtone.
                final Uri currentTone=
                        RingtoneManager.getActualDefaultRingtoneUri(SettingsActivity.this,
                                RingtoneManager.TYPE_ALARM);
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                startActivityForResult(intent, 999);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            txtView.setText("From :" + uri.getPath());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SettingsActivity.this ,MainActivity.class ));
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.settings_activity;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_prediction;
    }

}