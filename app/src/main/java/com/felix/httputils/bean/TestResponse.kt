package com.felix.httputils.bean

/**
 * Created by liuhaiyang on 2017/12/13.
 */
class TestResponse {

    var title: String? = null
    var albumList: MutableList<MusicAlbum>? = null

    class MusicAlbum {
        var url: String? = null
        var content: String? = null
        var type: Int = 0
    }
}