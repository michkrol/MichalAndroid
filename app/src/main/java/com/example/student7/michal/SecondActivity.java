package com.example.student7.michal;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends ActionBarActivity {

    private TextView powitanie;
    private TextView sila;
    private RatingBar pasek;
    private EditText numer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        powitanie=(TextView) findViewById(R.id.powitanie);
        sila=(TextView) findViewById(R.id.sila);
        pasek=(RatingBar) findViewById(R.id.pasek);
        numer=(EditText) findViewById(R.id.numer);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");
        powitanie.setText("Cześć " + username + "!");
        String haslo = extras.getString("haslo");
        sila.setText("Siła hasła: " + silaSlownie(silaLiczba(haslo)));
        pasek.setProgress(silaLiczba(haslo));
    }

    public void zadzwonClicked(View view) {
        String tel = numer.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    private int silaLiczba(String haslo) {
        int sila = haslo.length() > 8 ? 8 : haslo.length();
        if(haslo.matches(".*[a-z].*")) sila++;
        if(haslo.matches(".*[A-Z].*")) sila++;
        if(haslo.matches(".*[0-9].*")) sila++;
        if(haslo.matches(".*[^a-zA-Z0-9].*")) sila++;
        return sila;
    }

    private String silaSlownie(int sila) {
        if(sila<10) return "słabe";
        if(sila<12) return "średnie";
        return "mocne";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
