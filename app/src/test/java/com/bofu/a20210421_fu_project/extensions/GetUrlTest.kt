package com.bofu.a20210421_fu_project.extensions

import com.google.common.truth.Truth
import org.junit.Test

class GetUrlTest {

    private val cod10A = "41763935FH"
    private val cod10B = "11434387RA"
    private val cod10C = "45401202HB"

    @Test
    fun getUrlResultTest() {
        val urlA = cod10A.getUrl()
        Truth.assertThat(urlA).isEqualTo("https://cdn.yoox.biz/41/41763935FH_11_f.jpg")

        val urlB = cod10B.getUrl()
        Truth.assertThat(urlB).isEqualTo("https://cdn.yoox.biz/11/11434387RA_11_f.jpg")

        val urlC = cod10C.getUrl()
        Truth.assertThat(urlC).isEqualTo("https://cdn.yoox.biz/45/45401202HB_11_f.jpg")
    }
}