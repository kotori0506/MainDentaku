package com.example.ktr.dentaku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainDentaku extends ActionBarActivity {
/*
 * 変数の宣言
 */


    /**
            * * 数字の種類の最大数
    * 入力最大値
    */
    private final int TYP_MAX = 10;

    private final int MAX_NMB = 999999;
    /**
     * 入力を受ける数値
     * 入力を受ける数値
     */
    private int mADate = 0;
    private int mBDate = 0;
    /**
     * 表示用変数
     */
    private int mDrawDate = 0;
    private EditText mAns;
    /**
     * 計算結果を保持する変数
     */
    private int mAnsDate = 0;
    /**
     * 四則演算のどれをやるかのフラグ
     */
    private int mModeFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dentaku);
/**
 * 変数の初期化
 */


        /**
         * 表示欄の取得
         */
        mAns = (EditText) findViewById(R.id.ansdate);


        /**
         * ボタンIDの取得
         */
        Button[] btns = new Button[TYP_MAX];

        btns[0] = (Button) findViewById(R.id.bt0);
        btns[1] = (Button) findViewById(R.id.bt1);
        btns[2] = (Button) findViewById(R.id.bt2);
        btns[3] = (Button) findViewById(R.id.bt3);
        btns[4] = (Button) findViewById(R.id.bt4);
        btns[5] = (Button) findViewById(R.id.bt5);
        btns[6] = (Button) findViewById(R.id.bt6);
        btns[7] = (Button) findViewById(R.id.bt7);
        btns[8] = (Button) findViewById(R.id.bt8);
        btns[9] = (Button) findViewById(R.id.bt9);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_root_layout);
        mainLayout.getChildAt(0);

        for (int i = 0; i < mainLayout.getChildCount(); i++) {
            Button buton = (Button) mainLayout.getChildAt(i);
        }

        /**
         * データの保存
         */


        /**
         * クリック・リスナーの登録
         */
        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushNo(Integer.parseInt(((Button) v).getText().toString()), true);
            }


        };

        for (int i = 0; i < TYP_MAX; i++) {
            btns[i].setOnClickListener(cl);
            mModeFlag = 0;
        }

        Button btC = (Button) findViewById(R.id.btC);
        btC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mModeFlag == 0)
                    mADate = 0;
                else
                    mBDate = 0;
                pushNo(0, false);
            }
        });

        pushNo(0, false);

        Button btHV = (Button) findViewById(R.id.btHistoryView);
        btHV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btHVOnclicknclick();
            }
        });


        /**
         * 足し算
         * 引き算
         * 掛け算
         * 割り算
         * の決定
         */

        Button btp = (Button)findViewById(R.id.btp);
        btp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mModeFlag = 1;

                pushNo(0, false);
            }
        });

        Button btm = (Button) findViewById(R.id.btm);
        btm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mModeFlag = 2;

                pushNo(0, false);
            }
        });

        Button btk = (Button) findViewById(R.id.btk);
        btk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mModeFlag = 3;

                pushNo(0, false);
            }
        });

        Button btw = (Button) findViewById(R.id.btw);
        btw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mModeFlag = 4;

                pushNo(0, false);
            }
        });


        /**
         * 四則演算の処理（イコールが押された時の処理）
         */

        Button bti = (Button) findViewById(R.id.bti);
        bti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (mModeFlag) {
                    case 1: {
                        mAnsDate = mADate + mBDate;
                        mModeFlag = 0;


                        HistoryActivity.mUpdateHistoryDateflag = 1;
                        HistoryActivity.HistoryDate = mAnsDate;
                        mADate = mAnsDate;
                        mBDate = 0;
                        break;
                    }
                    case 2: {
                        mAnsDate = mADate - mBDate;
                        mModeFlag = 0;

                        HistoryActivity.mUpdateHistoryDateflag = 1;
                        HistoryActivity.HistoryDate = mAnsDate;
                        mADate = mAnsDate;
                        mBDate = 0;
                        break;
                    }
                    case 3: {
                        mAnsDate = mADate * mBDate;
                        mModeFlag = 0;

                        HistoryActivity.mUpdateHistoryDateflag = 1;
                        HistoryActivity.HistoryDate = mAnsDate;
                        mADate = mAnsDate;
                        mBDate = 0;
                        break;
                    }
                    case 4: {
                        mAnsDate = mADate / mBDate;
                        mModeFlag = 0;

                        HistoryActivity.mUpdateHistoryDateflag = 1;
                        HistoryActivity.HistoryDate = mAnsDate;
                        mADate = mAnsDate;
                        mBDate = 0;

                        break;
                    }

                }

                pushNo(0, false);


                /**
                 * 履歴として計算結果を保存する
                 */
                saveButtonClick();

            }

        });


        /**
         * データ保存test
         */


    }

    private void pushNo(int no, boolean add) {

        /**
         * 数字を末尾に追加（桁を増やす）
         */
        if (add) {
            if (mModeFlag == 0) {
                int noNew = mADate * 10 + no;
                if (noNew <= MAX_NMB) mADate = noNew;
            } else {
                int noNew = mBDate * 10 + no;
                if (noNew <= MAX_NMB) mBDate = noNew;

            }
        }


        /**
         * 数字の更新
         */
        if (mModeFlag == 0) {
            mDrawDate = mADate;
            mAns.setText("" + mDrawDate);
        } else {
            mDrawDate = mBDate;
            mAns.setText("" + mDrawDate);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_dentaku, menu);
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
     * 履歴Activityへ移動
     */

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btHistory:
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void saveButtonClick() {

        mModeFlag = 0;
        mBDate = 0;

        mAns.setText("" + mDrawDate);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UPDATE_TIME", 3);
        editor.commit();

    }

    private void btHVOnclicknclick() {
        mAns.setText("" + mDrawDate);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        mAns.setText(sp.getString("SaveString", null), TextView.BufferType.NORMAL);
    }


}
