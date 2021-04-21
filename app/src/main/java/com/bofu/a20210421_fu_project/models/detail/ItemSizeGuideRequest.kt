package com.bofu.a20210421_fu_project.models.detail

data class ItemSizeGuideRequest (
    val SizeTypeID: Long,
    val Gender: String,
    val BrandID: Long,
    val MacroCategoryID: Long,
    val Dept: String
)