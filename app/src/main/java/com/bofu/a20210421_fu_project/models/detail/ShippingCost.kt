package com.bofu.a20210421_fu_project.models.detail

data class ShippingCost (
    val Title: String,
    val Cells: List<Cell>
)
data class Cell (
    val Values: List<String>
)