package com.example.b07_group_19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostReqActivity extends Activity {
    public EditText texta22, texta31, texta37, texta48, texta67;
    int inta22,inta31,inta37,inta48,inta67;
    private Button result_button;
    private CheckBox admission; // Are they in stream or not?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postreq);

        texta22.setText(String.valueOf(findViewById(R.id.a22)));
        texta31.setText(String.valueOf(findViewById(R.id.a31)));
        texta37.setText(String.valueOf(findViewById(R.id.a37)));
        texta48.setText(String.valueOf(findViewById(R.id.a48)));
        texta67.setText(String.valueOf(findViewById(R.id.a67)));

        admission = (CheckBox)findViewById(R.id.admission_check);
        result_button = (Button)findViewById(R.id.submit);

        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(String.valueOf(texta22)) || TextUtils.isEmpty(String.valueOf(texta31)) ||
                        TextUtils.isEmpty(String.valueOf(texta37)) || TextUtils.isEmpty(String.valueOf(texta48)) ||
                        TextUtils.isEmpty(String.valueOf(texta67))) {
                    missingField();
                }
                else{
                    inta22 = Integer.parseInt(String.valueOf(texta22));
                    inta31 = Integer.parseInt(String.valueOf(texta31));
                    inta37 = Integer.parseInt(String.valueOf(texta37));
                    inta48 = Integer.parseInt(String.valueOf(texta48));
                    inta67 = Integer.parseInt(String.valueOf(texta67));
                    checkReq(inta22, inta31, inta37, inta48, inta67, admission);
                }
            }
        });

        Button backBtn = findViewById(R.id.homePostreq);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    public void checkReq(int a22, int a31, int a37, int a48, int a67, CheckBox admission){

        TextView textres = findViewById(R.id.result_text);
        String msg;
        int avg = (a22 + a31 + a37 + a48 + a67)/5;
        textres.setText(String.valueOf(findViewById(R.id.result_text)));

        if (admission.isChecked()){
            if (a48>73 && avg>70 && ((a22>60 && a37>60) || (a67>60 && a37>60) || (a22>60 && a67>60))){
                msg = "Result Message: Congratulations! You made it in";
                textres.setText(msg);
            }
        }
        else{
            if (a67>80 && a31>80){
                msg = "Since your not in stream for CS, admissions are competitive. However, You have a good chance at making it in!";
                textres.setText(msg);
            }
        }

        msg = "Sorry, you couldn't make it in. To see Computer Science POSt Requirements, please refer to: \\n https://www.utsc.utoronto.ca/cms/computer-science-post-requirements-2023";
        textres.setText(msg);
    }

    public void backToHome(){
        Intent intent = new Intent(PostReqActivity.this,StudentHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void missingField(){
        Toast.makeText(PostReqActivity.this,"Enter Missing Field",Toast.LENGTH_SHORT).show();
    }


}

