package com.example.b07_group_19;

public class PostReqActivity {
    int a22 = 0, a31 = 0, a37 = 0, a48 = 0, a67 = 0; // Marks for each course
    int admission = 0; // Are they in stream or not?

    public void getReq(int a22, int a31, int a37, int a48, int a67, int admission){

    }

    public int computeAvg(int a22, int a31, int a37, int a48, int a67){
        return (a22 + a31 + a37 + a48 + a67)/5;
    }

    public String checkReq(int a22, int a31, int a37, int a48, int a67, int admission){

        int avg = computeAvg(a22,a31,a37,a48,a67);

        if (admission == 0){
            if (a48>70 && avg>70){
                return "You made it in!\n";
            }
        }
        else{
            boolean a31firsttime = true, a67firsttime = true;
            if (a67>70 && a31>70 && a31firsttime == true && a67firsttime == true){
                return "You made it in!\n";
            }
        }

        return "Sorry, you couldn't make it in.\n To see Computer Science POSt Requirements, please refer to: \n https://www.utsc.utoronto.ca/cms/computer-science-post-requirements-2023";
    }

}
