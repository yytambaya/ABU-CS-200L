package com.tambayahub.abu_cs_200l;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder about_dialog;
    ArrayList<String> notes = new ArrayList<>();
    SharedPreferences courses_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView NotesList = findViewById(R.id.Notes);
        ArrayAdapter notes_adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, notes);
        NotesList.setAdapter(notes_adapter);
        //Initiating and creating Courses names' Storage.
        courses_sp = getSharedPreferences("courses", MODE_PRIVATE);
        SharedPreferences.Editor editor = courses_sp.edit();
        editor.clear();
        editor.putString("COSC211", "COSC211");
        editor.putString("COSC211-PQ", "COSC211-PQ");
        editor.putString("COSC211-LAB", "COSC211-LAB");
        editor.putString("COSC203", "COSC203");
        editor.putString("COSC203-PQ", "COSC203-PQ");
        editor.putString("COSC205", "COSC205");
        editor.putString("COSC205-PQ", "COSC205-PQ");
        editor.putString("MATH201", "MATH201");
        editor.putString("MATH201-PQ", "MATH201-PQ");
        editor.putString("MATH207", "MATH207");
        editor.putString("MATH207-PQ", "MATH207-PQ");
        editor.putString("MATH209", "MATH209");
        editor.putString("MATH209-PQ", "MATH209-PQ");
        editor.putString("GENS201", "GENS201");
        editor.putString("STAT201", "STAT201");
        editor.putString("STAT201-PQ", "STAT201-PQ");
        editor.commit();


        Map<String, ?> allEntries = courses_sp.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            notes.add(entry.getKey());
        }
        Collections.sort(notes);

        NotesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String course = NotesList.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, PDFViewer.class);
                intent.putExtra("course", course);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Toast.makeText(MainActivity.this, "Error: "+ errorCode, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            about_dialog = new AlertDialog.Builder(this);
            about_dialog.setTitle("About this app");
            about_dialog.setMessage("200 level courses, Computer Science, ABU Zaria, Nigeria.\n\n Created by TambayaHub");
            about_dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            about_dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
