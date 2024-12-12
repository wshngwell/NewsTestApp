package com.example.newstestapp.navigation

sealed class TabRowStates(open var id: Int) {

    data class BUSINESS_NEWS (override var id: Int = 0) : TabRowStates(id)
    data class HEALTH_NEWS(override var id: Int = 1) : TabRowStates(id)
    data class SPORT_NEWS(override var id: Int = 2) : TabRowStates(id)
}