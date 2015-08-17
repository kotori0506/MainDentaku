package com.example.ktr.dentaku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class HistoryActivity extends ActionBarActivity {


    private TextView mViewText;
    private TextView mViewText2;
    private TextView mViewText3;
    private TextView mViewText4;
    private TextView mViewText5;

    String[] result=new String[5];

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        for (int j=0;j<5;j++){
            result[j]="0";
        }


        pref = getSharedPreferences("PREF_TEST", MODE_PRIVATE);
        result[0] = pref.getString("PREF_RESULT", "-1");


        mViewText=(TextView)findViewById(R.id.First);
        mViewText2=(TextView)findViewById(R.id.Second);
        mViewText3=(TextView)findViewById(R.id.h3);
        mViewText4=(TextView)findViewById(R.id.h4);
        mViewText5=(TextView)findViewById(R.id.h5);



        mViewText.setText(result[0]);
        mViewText2.setText(result[0]);
        mViewText3.setText(result[1]);
        mViewText4.setText(result[2]);
        mViewText5.setText(result[3]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void IntentMain(View view) {

        switch (view.getId()) {
            case R.id.intent:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

        }
    }
}
