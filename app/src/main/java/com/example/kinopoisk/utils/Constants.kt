package com.example.kinopoisk.utils

object Constants {
    const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    const val FILM_URL = "/api/v2.2/films"
    const val FILM_INFO_ID_URL = "/api/v2.2/films/{id}"
    const val BUDGET_FILM_ID_URL = "/api/v2.2/films/{id}/box_office"
    const val FACT_FILM_ID_URL = "/api/v2.2/films/{id}/facts"
    const val STAFf_URL = "/api/v1/staff"
    const val SIMILAR_ID_URL = "/api/v2.2/films/{id}/similars"
    const val SEQUEL_AND_PREQUEL_ID_URL = "/api/v2.1/films/{id}/sequels_and_prequels"
    const val SEASONS_ID_URL = "/api/v2.2/films/{id}/seasons"
    const val PREMIERE_URL = "/api/v2.2/films/premieres"
    const val RELEASE_URL = "/api/v2.1/films/releases"
    const val TOP_FILM_URL = "/api/v2.2/films/top"
    const val IMAGE_ID_URL = "/api/v2.2/films/{id}/images"
    const val REVIEW_URL = "/api/v1/reviews"
    const val STAFF_INF0_ID_URL = "/api/v1/staff/{id}"
    const val REVIEW_ID_URL = "/api/v1/reviews/details"
    const val FILM_VIDEO_ID_URL = "/api/v2.2/films/{id}/videos"
    const val FILM_DISTRIBUTION_ID_URL = "/api/v2.2/films/{id}/distributions"
    const val SEARCH_PERSON_URL = "/api/v1/persons"

    const val BASE_USER_URL = "http://10.0.2.2:5000"
    const val AUTHORIZATION_URL = "/Authorization"
    const val REGISTRATION_URL = "/Registration"
    const val USER_INFO_URL = "/User/Info"
    const val USER_FAVORITE_FILM_URL = "/Movie/User/Favorite"
    const val USER_FAVORITE_FILM_ID_KINOPOISK_URL = "/Movie/User/Favorite/{idKinopoisk}"
    const val USER_WATCH_LATER_FILM_URL = "/Movie/WatchLater"
    const val USER_FAVORITE_CHECK_FILM_URL = "/Movie/User/Favorite/Check"
    const val USER_WATCH_LATER_CHECK_FILM_URL = "/WatchLater/CheckFilm"
    const val USER_UPDATE_PASSWORD_URL = "/User/Update/Password"
    const val SHOP_FILM_URL = "/Shop"

    const val TOKEN_SHARED = "token_share"

    const val TOKEN_KEY = "ab67ce7d-90cf-4d1b-b354-7474b82c9f38"
}