package com.example.ktr.dentaku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class MainActivity extends ActionBarActivity {

//    public static TextView mFirestText;
//    public static TextView mSecondText;
//    public static int mflag;

    /**
     * 表示用変数
     */
    private TextView mAns;
    String[] ansStrArr;
    private String mTemp;

    /**
     * 連続でのkey入力を禁止する
     */

    private int mNoKey = 0;
    private int mSkey = 0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("PREF_TEST", MODE_PRIVATE);
        editor = pref.edit();
        /*double mSaveArry[10];
        for (int i=0;i<10;i++)
        {
            mSaveArry[i]=0.0;
        }*/

        initView();
    }

    private void initView() {
        mAns = (TextView) findViewById(R.id.TextView);

        Button btC = (Button) findViewById(R.id.btC);
        btC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAns.setText("");
            }
        });

    }

    public void onClickBtn(View view) {
        if (view.getTag() == null) {
            return;
        }
        String key = (String) view.getTag();

        mAns.setText(mAns.getText().toString() + key); // 1 3 5 7
        mNoKey = 1;

    }

    public void onClickKeyD(View view) {
        if (view.getTag() == null) {
            return;
        }

        if (mSkey == 0) {
            String key = (String) view.getTag();
            mAns.setText(mAns.getText().toString() + key); // 1 3 5 7
            mSkey = 1;
        }
    }

    public void onClickKey(View view) {
        if (view.getTag() == null) {
            return;
        }
        if (mNoKey == 1) {
            String key = (String) view.getTag();
            mAns.setText(mAns.getText().toString() + " " + key + " "); // 1 3 5 7
            mNoKey = 0;
            mSkey = 0;
        }
    }

    public void EnterOnClick(View view) {

        if (mNoKey == 1) {
            mSkey = 0;
            /**
             * 配列に入れ直す
             */
            String str = mAns.getText().toString();
            ansStrArr = str.split(" ");

            /**
             * 自作四則演算関数
             */
            myF();
        }
    }

    /**
     * 自作四則演算関数
     */
    private void myF() {
        /**
         *　掛け算、割り算を先にさがして計算
         * その後足し算、引き算を行う。
         */
        double a = 0;
        double b = 0;
        double c = 0;
        for (int j = 1; j < ansStrArr.length; j = j + 1) {
            if (ansStrArr[j].equals("*")) {
                a = Double.parseDouble(ansStrArr[j - 1]);
                b = Double.parseDouble(ansStrArr[j + 1]);
                c = a * b;
                ansStrArr[j + 1] = String.valueOf(c);
                ansStrArr[j - 1] = String.valueOf("0");
                ansStrArr[j] = String.valueOf("+");
            } else if (ansStrArr[j].equals("/")) {

                a = Double.parseDouble(ansStrArr[j - 1]);
                if (ansStrArr[j + 1].equals("0")) {

                } else {
                    b = Double.parseDouble(ansStrArr[j + 1]);
                    c = a / b;
                    ansStrArr[j + 1] = String.valueOf(c);
                    ansStrArr[j - 1] = String.valueOf("0");
                    ansStrArr[j] = String.valueOf("+");
                }
            }
        }
        for (int j = 1; j < ansStrArr.length - 1; j = j + 1) {
            if (ansStrArr[j].equals("-")) {
                a = Double.parseDouble(ansStrArr[j - 1]);
                b = Double.parseDouble(ansStrArr[j + 1]);
                c = a - b;
                ansStrArr[j + 1] = String.valueOf(c);
                ansStrArr[j - 1] = String.valueOf("0");
                ansStrArr[j] = String.valueOf("+");
            } else if (ansStrArr[j].equals("+")) {

                a = Double.parseDouble(ansStrArr[j - 1]);
                b = Double.parseDouble(ansStrArr[j + 1]);
                c = a + b;
                ansStrArr[j + 1] = String.valueOf(c);
            }
            /**
             *　配列の最後になった計算結果を一番目の配列に移動
             */
            for (int k = 0; k < ansStrArr.length; k++) {
                ansStrArr[0] = ansStrArr[k];
            }
        }

        /**
         *　計算し終わったデータをテキストに表示
         */
        mAns.setText(ansStrArr[0]);
        mNoKey = 1;
        mSkey = 1;
        
        /*mSaveArry.put(ansStrArr[0]);
        mSaveArry[1].put(ansStrArr[0]);
        editor.putString("PREF_RESULT", mSaveArry.toString());
        editor.commit();*/
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void IntentOnClick(View view) {

        switch (view.getId()) {
            case R.id.history:
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;

        }
    }
}
