package com.bofu.a20210421_fu_project.models.detail

data class ItemDetailData (

    val Cod10: String,
    val Brand: Brand,
    val Category: Category,
    val FormattedPrice: FormattedPrice,
    val ItemDescriptions: ItemDescriptions,
    val Colors: ArrayList<Color>,
    val Sizes: ArrayList<Size>,

    val ImageUrls: ImageUrls,
    val SocialDetails: SocialDetails,
    val RelatedSearches: RelatedSearches,
    val CommonFormattedPrices: CommonFormattedPrices,
    val Department: String,
    val Quantity: Long,
    val BrandID: Long,
    val Title: Author,
    val MacroCategory: String,
    val MicroCategoryPlural: String,
    val Author: Author,
    val Price: Price,
    val HasInfoPrice: Boolean,
    val HasCustomInfoPriceLayer: Boolean,
    val HasValidSizes: Boolean,
    val HasValidColors: Boolean,
    val HasFastDelivery: Boolean,
    val ColorSizeQty: List<ColorSizeQty>,
    val Wearability: String,
    val HasProposition65: Boolean,
    val RemoteWarehouse: String,
    val IsSoldOut: Boolean,
    val IsFinalSale: Boolean,
    val IsBaby: Boolean,
    val IsPets: Boolean,
    val IsLiquid: Boolean,
    val IsBook: Boolean,
    val IsDenim: Boolean,
    val IsSunglassHut: Boolean,
    val IsArt: Boolean,
    val ShowSizeGuide: Boolean,
    val ItemSizeGuideRequest: ItemSizeGuideRequest,
    val Season: String,
    val SaleLine: String,
    val SalesLineID: String,
    val IsArteria: Boolean,
    val Currency: String,
    val MicroCategoryAttribute: MicroCategoryAttribute,
    val MacroCategoryAttribute: MacroCategoryAttribute,
    val Composition: Composition,
    val BreadcrumbsURL: String,
    val ShowRetailPrice: Boolean,
    val HasToRenderDesignArtInfo: Boolean,
    val IsOneSize: Boolean,
    val ErrorCode: Long
)