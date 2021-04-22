package com.bofu.a20210421_fu_project.extensions

fun  List<String>.format():String {
    var descriptionContent = ""
    this.forEach{
        descriptionContent += it + "\n"
    }
    return descriptionContent
}