package com.example.imageserchapplication.pojo;

import android.text.TextUtils;

/**
 * Created by Incrust on 23-01-2019.
 */

public class Labels {


    String label_id;
    String label_comment;
    String label_name;


    public Labels(String label_id, String label_name, String label_comment
                 ) {


        this.label_id=label_id;
        this.label_name=label_name;
        this.label_comment=label_comment;

    }
    public Labels() {
    }


    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getLabel_comment() {
        return label_comment;
    }

    public void setLabel_comment(String label_comment) {
        this.label_comment = label_comment;
    }

    public String getLabel_name() {

        if(label_name != null) {
            return label_name;
        }else
        {
            return "";
        }
    }

    public void setLabel_name(String label_name) {

        this.label_name = label_name;
    }
}
