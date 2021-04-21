package com.bofu.a20210421_fu_project.models.detail

data class SocialDetails (
    val ItemLabel: String,
    val ItemImageURL: String,
    val ItemURL: String,
    val Facebook: Facebook,
    val Twitter: Facebook,
    val Google: Facebook,
    val Pinterest: Facebook
)

data class Facebook (
    val ItemURL: String
)