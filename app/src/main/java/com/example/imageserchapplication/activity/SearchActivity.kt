package com.example.imageserchapplication.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.Volley
import com.example.imageserchapplication.R
import com.example.imageserchapplication.activity.SearchActivity
import com.example.imageserchapplication.adapter.CustomGridAdapter
import com.example.imageserchapplication.pojo.Search
import com.example.imageserchapplication.pojo.SearchList
import com.example.imageserchapplication.util.IOUtils.Companion.getProgessDialog
import com.example.imageserchapplication.util.IOUtils.Companion.isNetworkAvailable
import com.example.imageserchapplication.util.IOUtils.Companion.setNetworkAlertForActivity
import com.example.imageserchapplication.util.JsonObjectRequestWithHeader
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private var gridSerachlist: GridView? = null
    private var edtSearch: EditText? = null
    private var imgSearch: ImageView? = null
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
        intiListners()
    }

    fun init() {
        SearchList = ArrayList()
        searchListArrayList = ArrayList()
        gridSerachlist = findViewById(R.id.gridSerachlist)
        edtSearch = findViewById(R.id.edtSearch)
        imgSearch = findViewById(R.id.imgSearch)
    }

    private fun intiListners() {
        imgSearch!!.setOnClickListener(this)
        edtSearch!!.setOnEditorActionListener { v, actionId, event ->
            if (isNetworkAvailable(this@SearchActivity)) {
                getList(JSONObject(), edtSearch!!.text.toString())
            } else {
                setNetworkAlertForActivity(this@SearchActivity)
            }
            false
        }
    }

    fun getList(jsonObject: JSONObject?, gettext: String) {
        dialog = getProgessDialog(this@SearchActivity)
        dialog!!.show()
        val GET_SEARCH__URL = "https://api.imgur.com/3/gallery/search/1?q=$gettext"
        val objectRequest: JsonObjectRequestWithHeader = object : JsonObjectRequestWithHeader(Method.GET, GET_SEARCH__URL, jsonObject, Response.Listener<Any?> { response ->
            Log.e("Response", response.toString())
            searchListArrayList!!.clear()
            SearchList!!.clear()
            if (response != null) {
                val gson = Gson()
                try {
                    val jsonObject = JSONObject(response.toString())
                    val jsonArray = jsonObject.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val jsonArrayjsonobject = jsonArray.getJSONObject(i)
                        if (jsonArrayjsonobject.has("images")) {
                            //get value of video
                            var   resultjsonobjectarr = jsonArray.getJSONObject(i).getJSONArray("images")
                            for (h in 0 until resultjsonobjectarr.length()) {
                                val resultjsonobject = resultjsonobjectarr.getJSONObject(h)
                                val searchlistpojo = SearchList()
                                searchlistpojo.title = resultjsonobject["description"].toString()
                                searchlistpojo.imageurl = resultjsonobject["link"].toString()
                                searchlistpojo.id = resultjsonobject["id"].toString()
                                SearchList!!.add(searchlistpojo)
                            }
                        }
                    }
                    if (SearchList != null && SearchList!!.size > 0) {
                        if (!this@SearchActivity.isFinishing && dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        gridSerachlist!!.adapter = CustomGridAdapter(this@SearchActivity, SearchList!!)
                    } else {
                        if (!this@SearchActivity.isFinishing && dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        Toast.makeText(applicationContext,
                                "No Record Found",
                                Toast.LENGTH_LONG)
                                .show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    if (!this@SearchActivity.isFinishing && dialog != null && dialog!!.isShowing) {
                        dialog!!.dismiss()
                    }
                }
            }
        }, Response.ErrorListener { error ->
            Log.e("Response", error.toString())
            if (!this@SearchActivity.isFinishing && dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Authorization"] = "Client-ID 137cda6b5008a7c"
                return params
            }
        }
        val socketTimeout = 30000 //30 seconds - change to what you want
        val policy: RetryPolicy = DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        objectRequest.retryPolicy = policy
        val queue = Volley.newRequestQueue(this@SearchActivity)
        queue.add(objectRequest)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgSearch -> if (isNetworkAvailable(this@SearchActivity)) {
                getList(JSONObject(), edtSearch!!.text.toString())
            } else {
                setNetworkAlertForActivity(this@SearchActivity)
            }
        }
    }

    companion object {
        var searchListArrayList: ArrayList<Search>? = null
        var SearchList: ArrayList<SearchList>? = null
    }
}