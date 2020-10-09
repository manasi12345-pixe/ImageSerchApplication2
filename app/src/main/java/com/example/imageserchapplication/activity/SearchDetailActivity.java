package com.example.imageserchapplication.activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imageserchapplication.R;
import com.example.imageserchapplication.adapter.CommentAdpter;
import com.example.imageserchapplication.pojo.Labels;
import com.example.imageserchapplication.util.DataBaseHandlerComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView toolbarBackImg,imgSearch;
    private TextView toolbarName;
    private String imagetitle,imageurl,imageid;
    private RecyclerView recyviewComments;
    private Button btnComment;
    private EditText edtComment;
    public static ArrayList<Labels> commentList = new ArrayList<>();
    DataBaseHandlerComment db1 = new DataBaseHandlerComment(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        if (getIntent().hasExtra("imageurl")) {
            imageurl = getIntent().getStringExtra("imageurl");
        }

        if (getIntent().hasExtra("imagetitle")) {
            imagetitle = getIntent().getStringExtra("imagetitle");
        }
        if (getIntent().hasExtra("imageid")) {
            imageid = getIntent().getStringExtra("imageid");
        }
        init();
        intiListners();
    }

    public void init() {
        toolbarBackImg = findViewById(R.id.toolbarBackImg);
        toolbarName = findViewById(R.id.toolbarName);
        imgSearch = findViewById(R.id.imgSearch);
        recyviewComments=findViewById(R.id.recyviewComments);
        btnComment=findViewById(R.id.btnComment);
        edtComment=findViewById(R.id.edtComment);
    }

    public void intiListners() {
        toolbarBackImg.setOnClickListener(this);
        btnComment.setOnClickListener(this);

        if(imagetitle!= null )
        {
            toolbarName.setText(imagetitle+"");
        }else
        {
            toolbarName.setText("");
        }


        try{
            Glide.with(SearchDetailActivity.this)
                    .load(Uri.parse(imageurl))
                    .into(imgSearch);

            List<Labels> getComments = db1.getAllContacts(imageid);

            Log.v("@@@@@@",""+getComments.size());

            commentList.clear();
            for (int i=0;i<getComments.size();i++)
            {

                Labels getform = new Labels();
                getform.setLabel_id(getComments.get(i).getLabel_id());
                getform.setLabel_name(getComments.get(i).getLabel_name());
                getform.setLabel_comment(getComments.get(i).getLabel_comment());

                commentList.add(getform);

            }

            if(commentList != null && commentList.size()> 0) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyviewComments.setLayoutManager(layoutManager);
                CommentAdpter commentadapter = new CommentAdpter(commentList, SearchDetailActivity.this);
                recyviewComments.setAdapter(commentadapter);
                commentadapter.notifyDataSetChanged();
                edtComment.setText("");
            }else
            {
                commentList.clear();
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbarBackImg:

                onBackPressed();
                break;
            case R.id.btnComment:
                if(TextUtils.isEmpty(edtComment.getText()) && TextUtils.getTrimmedLength(edtComment.getText()) == 0)
                {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Comments",
                            Toast.LENGTH_SHORT)
                            .show();
                }else {

                    try {

                        db1.addContact(new Labels(imageid, imagetitle, edtComment.getText().toString()));
                        List<Labels> getComments = db1.getAllContacts(imageid);

                        Log.v("@@@@@@",""+getComments.size());

                        commentList.clear();
                        for (int i=0;i<getComments.size();i++)
                        {

                           Labels getform = new Labels();
                            getform.setLabel_id(getComments.get(i).getLabel_id());
                            getform.setLabel_name(getComments.get(i).getLabel_name());
                            getform.setLabel_comment(getComments.get(i).getLabel_comment());

                            commentList.add(getform);

                        }

if(commentList != null && commentList.size()> 0) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyviewComments.setLayoutManager(layoutManager);
    CommentAdpter commentadapter = new CommentAdpter(commentList, SearchDetailActivity.this);
    recyviewComments.setAdapter(commentadapter);
    commentadapter.notifyDataSetChanged();
    edtComment.setText("");
}else
{
    commentList.clear();
}

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                break;


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
