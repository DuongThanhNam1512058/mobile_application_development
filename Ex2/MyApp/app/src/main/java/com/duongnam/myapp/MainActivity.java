package com.duongnam.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private Spinner spnFromCountry, spnToCountry;
    private Button btnConvert;
    private  EditText editCost;

    private  final  double USD_TO_VND = 22770.15 ;
    private  final  double JPY_TO_VND =  211.56;
    private final  double USD_TO_JPY = 107.63;

    public static  final  String COST_F = "frommoney";
    public static final  String COST_T  = "tomoney";
    public  static  final  String LABEL_F = "labelfrom";
    public  static  final  String LABEL_T = "labelto";
    public static final String BUNBLE = "bunble";

    private int fromMoney = 0,toMoney = 0;
    private  String fromSeclect,fromMTT, toSeclect, toMTT;
    private double cost = 0.0,result =0.0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnToCountry = (Spinner) findViewById(R.id.spn_tocountry);
        spnFromCountry = (Spinner) findViewById(R.id.spn_fromcountry);
        btnConvert = (Button) findViewById(R.id.btn_convert);
        editCost = (EditText) findViewById(R.id.edt_cost);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnToCountry.setAdapter(adapter);
        // 0->VND
        // 1 -> USD
        // 2 -> JPY
        spnFromCountry.setAdapter(adapter);


        spnFromCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromMoney = spnFromCountry.getSelectedItemPosition();
                fromSeclect = spnFromCountry.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spnToCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toMoney = spnToCountry.getSelectedItemPosition();
                toSeclect = spnToCountry.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    // call activity_result
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cost = Double.parseDouble(editCost.getText().toString());
                /*.................................................................*/
                if(fromMoney == 0)
                {
                    fromMTT = "  VND";
                }else if(fromMoney == 1){
                    fromMTT = "  USD";
                }
                else  if(fromMoney == 2) {
                    fromMTT = "  JPY";
                }

                if(toMoney == 0){
                    toMTT = "  VND";
                }else if(toMoney == 1){
                    toMTT = "  USD";
                }
                else  if(toMoney == 2) {
                    toMTT = "  JPY";
                }
                /*.................................................................*/
                if(fromMoney == toMoney){
                    result = cost;
                }else {
                    if (fromMoney == 0 ) {

                        if(toMoney == 1) {
                            //viet nam sang mi
                            result = cost/USD_TO_VND;
                        }else {
                            //viet nam sang nhat
                            result = cost/JPY_TO_VND;
                        }
                    }else if(fromMoney == 1) {
                        //my qua vien nam
                        if (toMoney == 0){
                            result = cost*USD_TO_VND;
                        } else {
                            // my qua  nhat
                            result = cost*USD_TO_JPY;
                        }

                    }else {
                        if (fromMoney == 2) {
                            if (toMoney == 0){
                                //nhat qua viet nam
                                result = cost*JPY_TO_VND;
                            } else {
                                //nhat qua mi
                                result = cost/USD_TO_JPY;
                            }
                        }
                    }
                }
                /*.................................................................*/

                /*.................................................................*/
                //call activity_rest
                Log.d(TAG, "onItemSelected: " + fromMoney);
                Log.d(TAG, "onItemSelected: " + toMoney);
                Log.d(TAG, "onItemSelected: " + fromSeclect);
                Log.d(TAG, "onItemSelected: " + toSeclect);
                Log.d(TAG, "onClick: " + result);
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(COST_F,  String.valueOf(cost) + fromMTT );
                bundle.putString(COST_T,String.valueOf(result) + toMTT);
                bundle.putString(LABEL_F,fromSeclect);
                bundle.putString(LABEL_T,toSeclect);
                intent.putExtra(BUNBLE,bundle);
                startActivity(intent);
            }
        });



    }


}
