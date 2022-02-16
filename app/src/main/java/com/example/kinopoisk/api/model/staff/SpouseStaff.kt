package com.example.kinopoisk.api.model.staff

data class SpouseStaff(
    val children: Int?,
    val divorced: Boolean?,
    val divorcedReason: String?,
    val name: String?,
    val personId: Int?,
    val relation: String?,
    val sex: String?,
    val webUrl: String?
)