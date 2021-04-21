package com.bofu.a20210421_fu_project.models.detail

data class ShippingInfo (
    val ShippingCosts: List<ShippingCost>,
    val ShippingNotes: List<ShippingNote>
)