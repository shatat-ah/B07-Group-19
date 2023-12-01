package com.example.b07_group_19;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class PostReqActivity extends Activity {
    private EditText a22, a31, a37, a48, a67;
    private Button results;
    private CheckBox admission; // Are they in stream or not?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postreq);
    }

    public String checkReq(int a22, int a31, int a37, int a48, int a67, boolean admission){

        int avg = (a22 + a31 + a37 + a48 + a67)/5;

        if (admission == true){
            if (a48>73 && avg>70 && ((a22>60 && a37>60) || (a67>60 && a37>60) || (a22>60 && a67>60))){
                return "You made it in!\n";
            }
        }
        else{
            if (a67>80 && a31>80){
                return "You made it in!\n";
            }
        }

        return "Sorry, you couldn't make it in.\n To see Computer Science POSt Requirements, please refer to: \n https://www.utsc.utoronto.ca/cms/computer-science-post-requirements-2023";
    }

}
