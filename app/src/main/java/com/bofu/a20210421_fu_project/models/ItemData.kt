package com.bofu.a20210421_fu_project.models

data class ItemData (
    val Cod10: String,
    val Brand: String,
    val BrandID: Long,
    val Author: Author,
    val MicroCategoryCode: String,
    val MicroCategory: String,
    val MicroCategoryPlural: String,
    val MacroCategoryCode: String,
    val MacroCategory: String,
    val MacroCategoryPlural: String,
    val FullPrice: Long,
    val DiscountedPrice: Long,
    val IsSoldout: Boolean,
    val Sizes: List<Size>,
    val Colors: List<Color>,
    val HasFlipSide: Boolean,
    val HasModelImage: Boolean,
    val SalesLineID: String,
    val FormattedFullPrice: String,
    val FormattedDiscountedPrice: String,
    val DiscountedPriceEur: Long,
    val HasAlternativeImage: Boolean,
    val Title: String? = null
)