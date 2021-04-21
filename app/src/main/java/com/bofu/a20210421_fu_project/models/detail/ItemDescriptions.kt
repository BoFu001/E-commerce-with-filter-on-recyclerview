package com.bofu.a20210421_fu_project.models.detail

data class ItemDescriptions (
    val SizeInfo: String,
    val ProductInfo: List<String>,
    val BrandInfo: String,
    val ShippingInfo: ShippingInfo
)