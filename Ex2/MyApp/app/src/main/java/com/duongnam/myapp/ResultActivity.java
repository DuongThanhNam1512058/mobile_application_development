package com.duongnam.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    private TextView textCallMain;
    private TextView textCostF,textCostT,textLabelF, textLabelT;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textCallMain = (TextView)findViewById(R.id.tv_callmain);
        textCostF = (TextView) findViewById(R.id.tv_fom);
        textCostT = (TextView) findViewById(R.id.tv_to);
        textLabelF =(TextView) findViewById(R.id.tv_fromselect);
        textLabelT = (TextView) findViewById(R.id.tv_toselect);



        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getBundleExtra(MainActivity.BUNBLE);
            if(bundle != null) {
                textCostF.setText(bundle.getString(MainActivity.COST_F));
                textCostT.setText(bundle.getString(MainActivity.COST_T));
                textLabelF.setText(bundle.getString(MainActivity.LABEL_F));
                textLabelT.setText(bundle.getString(MainActivity.LABEL_T));
            }else {

            }
        }

        // call MainActivity
        textCallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
