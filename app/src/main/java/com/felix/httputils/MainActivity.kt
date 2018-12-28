package com.felix.httputils

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.felix.httputils.bean.TestGetRequest
import com.felix.httputils.bean.TestResponse
import com.felix.library.net.RestBaseCallBack
import com.felix.library.utils.HttpUtils
import com.felix.library.utils.MoshiUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listUrl = "http://5a2a624532152c0012fb9328.mockapi.io/api/homeMusic"

        kvBtGet.setOnClickListener {

            val request = TestGetRequest()
            HttpUtils.getData(listUrl, request, object : RestBaseCallBack<List<TestResponse>>() {
                override fun onResponse(data: List<TestResponse>?) {
                }

                override fun onFailure(error: Throwable?, code: Int, msg: String) {
                }

            })
        }

        kvBtPost.setOnClickListener {
        }
    }
}
