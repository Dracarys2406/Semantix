package com.alaric.aigamerecommender.ui.features.profile.profiledetails

interface ProfileDetailsEffect {
    data class ShowToast(val message: String) : ProfileDetailsEffect
}