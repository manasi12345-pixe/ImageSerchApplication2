package com.example.imageserchapplication.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.imageserchapplication.R
import com.example.imageserchapplication.activity.SearchDetailActivity
import com.example.imageserchapplication.pojo.SearchList

class CustomGridAdapter(private val context: Context, private val listData: List<SearchList>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): Any {
        return listData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null)
            holder = ViewHolder()
            holder.flagView = convertView.findViewById<View>(R.id.imageView_flag) as ImageView
            holder.rowView = convertView.findViewById(R.id.main_layout)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val searchlist = listData[position]
        Glide.with(context)
                .load(Uri.parse(searchlist.imageurl))
                .into(holder.flagView!!)
        holder.rowView!!.setOnClickListener { // TODO Auto-generated method stub
            val i = Intent(context, SearchDetailActivity::class.java)
            i.putExtra("imageurl", searchlist.imageurl)
            i.putExtra("imagetitle", searchlist.title)
            i.putExtra("imageid", searchlist.id)
            context.startActivity(i)
        }
        return convertView!!
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    fun getMipmapResIdByName(resName: String): Int {
        val pkgName = context.packageName

        // Return 0 if not found.
        val resID = context.resources.getIdentifier(resName, "mipmap", pkgName)
        Log.i("CustomGridView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }

    internal class ViewHolder {
        var flagView: ImageView? = null
        var rowView: ConstraintLayout? = null
    }

    init {
        layoutInflater = LayoutInflater.from(context)
    }
}