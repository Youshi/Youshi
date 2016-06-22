package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.fragment.chat.SettingsFragment;

/**
 * 系统设置
 */
public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction().add(R.id.container,new SettingsFragment()).commit();
    }
}
