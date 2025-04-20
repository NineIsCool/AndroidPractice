package com.example.androidpractice.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.androidpractice.domain.model.Profile
import com.example.androidpractice.domain.repository.IProfileRepository
import com.example.androidpractice.state.ProfileState
import com.example.androidpractice.utils.launchLoadingAndError

class ProfileViewModel(
    private val repository: IProfileRepository,
    private val navigation: NavHostController
) : ViewModel() {

    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            repository.getProfile()?.let { profile ->
                updateState(profile)
            }
        }
    }

    fun onEditProfileClicked() {
        navigation.navigate("edit_profile")
    }

    fun onSaveProfile(profile: Profile) {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            val profileSaved = repository.setProfile(profile = profile)
            updateState(profileSaved)
            navigation.popBackStack()
        }
    }

    private fun updateState(profile: Profile) {
        mutableState.fio = profile.fio
        mutableState.avatarUri = profile.avatarUri
        mutableState.resumeUrl = profile.resumeUrl
        mutableState.position = profile.position
        mutableState.email = profile.email
    }

    class MutableProfileState : ProfileState {
        override var fio: String by mutableStateOf("")
        override var avatarUri: String by mutableStateOf("")
        override var resumeUrl: String by mutableStateOf("")
        override var position: String by mutableStateOf("")
        override var email: String by mutableStateOf("")
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}