package com.example.b07_group_19;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PostReqActivity extends Activity {
    public EditText texta22, texta31, texta37, texta48, texta67;
    private Button results;
    private CheckBox admission; // Are they in stream or not?

    int a22 = Integer.parseInt(String.valueOf(texta22));
    int a31 = Integer.parseInt(String.valueOf(texta31));
    int a37 = Integer.parseInt(String.valueOf(texta37));
    int a48 = Integer.parseInt(String.valueOf(texta48));
    int a67 = Integer.parseInt(String.valueOf(texta67));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postreq);
    }

    public String checkReq(int a22, int a31, int a37, int a48, int a67, CheckBox admission){

        int avg = (a22 + a31 + a37 + a48 + a67)/5;

        if (admission.isChecked()){
            if (a48>73 && avg>70 && ((a22>60 && a37>60) || (a67>60 && a37>60) || (a22>60 && a67>60))){
                return "Congratulations!, you made it in!\n";
            }
        }
        else{
            if (a67>80 && a31>80){
                return "Since your not in stream for CS, admissions are competitive.\n However, You have a good chance at making it in!\n";
            }
        }

        return "Sorry, you couldn't make it in.\n To see Computer Science POSt Requirements, please refer to: \n https://www.utsc.utoronto.ca/cms/computer-science-post-requirements-2023";
    }

    public void missingField(){
        Toast.makeText(PostReqActivity.this,"Enter Missing Field",Toast.LENGTH_SHORT).show();
    }

}
