package com.example.b07_group_19;

public class PostReqActivity {
    int a22 = 0, a31 = 0, a37 = 0, a48 = 0, a67 = 0; // Marks for each course
    boolean admission = true; // Are they in stream or not?

    public String checkReq(int a22, int a31, int a37, int a48, int a67, boolean admission){

        int avg = (a22 + a31 + a37 + a48 + a67)/5;

        if (admission == true){
            if (a48>73 && avg>70 && ((a22>60 && a37>60) || (a67>60 && a37>60) || (a22>60 && a67>60))){
                return "You made it in!\n";
            }
        }
        else{
            boolean a31firsttime = true, a67firsttime = true;
            if (a67>80 && a31>80 && a31firsttime == true && a67firsttime == true){
                return "You made it in!\n";
            }
        }

        return "Sorry, you couldn't make it in.\n To see Computer Science POSt Requirements, please refer to: \n https://www.utsc.utoronto.ca/cms/computer-science-post-requirements-2023";
    }

}
