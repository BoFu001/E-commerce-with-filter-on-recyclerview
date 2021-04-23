package com.bofu.a20210421_fu_project.models.detail

data class Size (
    val Id: Long,
    val Name: String,
    val IsoTwoLetterCountryCode: String,
    val DefaultSizeLabel: String,
    val AlternativeSizeLabel: String,
    var isSelected: Boolean = false
)