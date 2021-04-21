package com.bofu.a20210421_fu_project.extensions

fun String.getUrl():String {
    return "https://cdn.yoox.biz/" + take(2) + "/" + this + "_11_f.jpg"
}