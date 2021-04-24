package com.bofu.a20210421_fu_project.extensions

fun Int.getOption():Int {
    return when (val option = this % 3){
        0 -> 3
        else -> option
    }
}