package com.bofu.a20210421_fu_project.models.detail

data class Brand (
    val Name: String,
    val Url: String,
    val Rel: String,
    val Attributes: BrandAttributes
)

data class BrandAttributes (
    val Dsgnrs: List<String>
)