package com.example.imageserchapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.imageserchapplication.R;
import com.example.imageserchapplication.pojo.Labels;

import java.util.ArrayList;

public class CommentAdpter extends RecyclerView.Adapter<CommentAdpter.ContactHolder> {

    // List to store all the contact details
    private ArrayList<Labels> formList;
    private Context mContext;

    // Counstructor for the Class
    public CommentAdpter(ArrayList<Labels> formList, Context context) {
        this.formList = formList;
        this.mContext = context;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.custom_form, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public int getItemCount() {
        return formList == null? 0: formList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder( ContactHolder holder, final int position) {
        Labels monthInDayModel = formList.get(position);

        holder.txtComment.setText(""+monthInDayModel.getLabel_comment());

        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public

    }

    // This is your ViewHolder class that helps to populate data to the view
    public class ContactHolder extends RecyclerView.ViewHolder {

        TextView txtComment;

        public ContactHolder(View itemView) {
            super(itemView);

            txtComment = itemView.findViewById(R.id.txtComment);

        }


    }
}
