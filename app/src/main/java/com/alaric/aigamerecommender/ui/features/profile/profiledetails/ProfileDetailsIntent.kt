package com.alaric.aigamerecommender.ui.features.profile.profiledetails

interface ProfileDetailsIntent {
    object OnDeleteFromLibrary : ProfileDetailsIntent
    object OnEditNote : ProfileDetailsIntent

}