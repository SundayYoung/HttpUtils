package com.felix.httputils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.felix.httputils.bean.TestGetRequest
import com.felix.httputils.net.BaseServerResponse
import com.felix.httputils.net.RestBaseCallBack
import com.felix.httputils.utils.HttpUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getUrl = "http://5a2a624532152c0012fb9328.mockapi.io/api/homeMusic"

        kvBtGet.setOnClickListener {

            val request = TestGetRequest()
            HttpUtils.getData(getUrl, request, object : RestBaseCallBack<BaseServerResponse>(){
                override fun onResponse(data: BaseServerResponse?) {

                }

                override fun onFailure(error: Throwable?, code: Int, msg: String) {
                }

            })
        }

        kvBtPost.setOnClickListener {

        }
    }
}
