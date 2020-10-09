package com.example.imageserchapplication.util

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

open class JsonObjectRequestWithHeader(method: Int, url: String?, requestBody: JSONObject?, listener: Response.Listener<*>?, errorListener: Response.ErrorListener?) : JsonObjectRequest(method, url, requestBody, listener as Response.Listener<JSONObject>?, errorListener) {
    override fun getBodyContentType(): String {
        return "application/json"
    }
}