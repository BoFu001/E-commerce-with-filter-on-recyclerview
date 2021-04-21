package com.bofu.a20210421_fu_project.models.detail

data class Category (
    val Name: String,
    val Url: String,
    val Rel: String,
    val Attributes: CategoryAttributes
)
data class CategoryAttributes (
    val Ctgr: List<String>
)