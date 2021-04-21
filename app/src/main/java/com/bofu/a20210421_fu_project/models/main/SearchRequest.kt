package com.bofu.a20210421_fu_project.models.main

data class SearchRequest (
    val tags: List<Any?>,
    val department: String,
    val inheritedMicroDesignerIDS: String,
    val sortID: Long,
    val sortValue: String,
    val season: String,
    val gender: String,
    val page: Long,
    val productsPerPage: Long,
    val textSearch: String,
    val microDesigners: List<Any?>,
    val attributes: Attributes
)

class Attributes