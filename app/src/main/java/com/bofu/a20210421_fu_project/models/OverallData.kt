package com.bofu.a20210421_fu_project.models

data class OverallData (
    val Items: ArrayList<ItemData>,
    val SearchRequest: SearchRequest,
    val Area: String,
    val Analytics: Analytics,
    val BrandBannerActionURL: String,
    val MobileFriendlyURL: String,
    val Department: String,
    val ErrorCode: Long
)