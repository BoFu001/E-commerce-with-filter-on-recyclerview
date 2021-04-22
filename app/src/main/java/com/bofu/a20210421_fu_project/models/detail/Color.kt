package com.bofu.a20210421_fu_project.models.detail

data class Color (
    val ColorID: Long,
    val ColorCode: String,
    val Code10: String,
    val Name: String,
    val Rgb: String,
    var isSelected: Boolean = false
)