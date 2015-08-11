package com.example.ktr.dentaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


/**
 * 履歴に保存されたデータをhistoryActivityで表示する為の処理
 */

public class HistoryActivity extends ActionBarActivity {


    /**
     * グローバル変数
     * 保存する為の一時データを保管する変数
     */
    public static int HistoryDate = 0;
    public static int mUpdateHistoryDateflag = 0;


    private int mDrowDateHSfast = 0;
    private int mDrowDateHSsecond = 0;

    private EditText mAnsHSfast;
    private EditText mAnsHSsecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        /**
         * 表示欄の取得
         */



        /**
         * グローバル変数の確認(tst)
         */
        if (mUpdateHistoryDateflag == 1) {

            mDrowDateHSsecond = mDrowDateHSfast;

            mDrowDateHSfast = HistoryDate;

            mUpdateHistoryDateflag = 0;
        }

        mAnsHSfast.setText("f" + mDrowDateHSfast);
        mAnsHSsecond.setText("s" + mDrowDateHSsecond);


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


    /**
     * 電卓に戻る


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, MainDentaku.class);
                startActivity(intent);
                break;
        }
    }
     */
}
