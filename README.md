# HttpUtils
根据Retrofit和Okhttp封装的网络请求库。只需对照接口编写对应的request(入参) 和 response(出参) 的Java bean，就可以完成网络请求~

例如：

## Get
        HttpUtils.getData(getUrl, TestGetRequest(), object : RestBaseCallBack<TestResponse>(){
                override fun onResponse(data: TestResponse?) {

                }

                override fun onFailure(error: Throwable?, code: Int, msg: String) {
                }

            })
            
 ## Post
        HttpUtils.postData(url, TestGetRequest(), object : RestBaseCallBack<TestResponse>(){
                override fun onResponse(data: TestResponse?) {

                }

                override fun onFailure(error: Throwable?, code: Int, msg: String) {
                }

            })
            
 ## Upload
        val partMap = HashMap<String, RequestBody>()
        val file = File(img.path)
        val fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file)
        val dataText = RequestBody.create(MediaType.parse("application/json"), "文本内容")
        partMap["image\"; filename=\"" + file.name] = fileBody
        partMap["data"] = dataText
        
        HttpUtils.upload(url, partMap, object : RestBaseCallBack<BaseServerResponse>(){
                override fun onResponse(response: BaseServerResponse?) {

                }

                override fun onFailure(error: Throwable?, code: Int, msg: String) {
                }

            })
