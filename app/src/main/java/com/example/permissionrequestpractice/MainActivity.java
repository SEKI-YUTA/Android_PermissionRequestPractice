package com.example.permissionrequestpractice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.permissionrequestpractice.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
                        + ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Snackbar.make(view, "すべてのパーミッションを許可してください", Snackbar.LENGTH_LONG)
                                .setAction("ENABLE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ActivityCompat.requestPermissions(MainActivity.this,
                                                new String[] {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
                                    }
                                })
                                .show();
                    } else {
                        Log.d("MyLog", "再びリクエスト");
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[] {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
                    }
                } else {
                    Log.d("MyLog", "すべてのパーミッションが許可されています");
                }
            }

        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("MyLog", "onRequestPermissionsResult");
        for(String item: permissions) {
            Log.d("GrantedPermission", item);
        }
        for(int item: grantResults) {
            Log.d("grantResults",  String.valueOf(item));
        }

        Log.d("requestCode",String.valueOf(requestCode));
    }
}