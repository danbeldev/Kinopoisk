package com.example.kinopoisk.api.model.staff

data class StaffInfo(
    val age: Int? = null,
    val birthday: String? = null,
    val birthplace: String? = null,
    val death: String? = null,
    val deathplace: String? = null,
    val facts: List<String>? = null,
    val films: List<FilmStaff> = listOf(),
    val growth: String? = null,
    val hasAwards: Int? = null,
    val nameEn: String? = null,
    val nameRu: String? = null,
    val personId: Int? = null,
    val posterUrl: String? = null,
    val profession: String? = null,
    val sex: String? = null,
    val spouses: List<SpouseStaff>? = null,
    val webUrl: String? = null
)