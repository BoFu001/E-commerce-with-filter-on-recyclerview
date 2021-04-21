package com.bofu.a20210421_fu_project.models.detail

data class RelatedSearches (
    val TapCategory: TapCategory,
    val TapBrand: TapBrand
)
data class TapBrand (
    val Attributes: BrandAttributes,
    val Department: String,
    val Gender: String,
    val NumItems: Long
)

data class TapCategory (
    val Attributes: CategoryAttributes,
    val Department: String,
    val Gender: String,
    val NumItems: Long
)