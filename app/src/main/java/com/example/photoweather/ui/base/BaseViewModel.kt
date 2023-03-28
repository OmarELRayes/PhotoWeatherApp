package com.example.photoweather.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<VS : ViewState> : ViewModel() {
    abstract val defaultViewState: VS

    protected val _viewStates by lazy { MutableStateFlow(defaultViewState) }
    val viewStates: StateFlow<VS> = _viewStates

    // SharedFlow version from viewStates Stateflow to to be used with single events like navigation.
    val viewEvents: SharedFlow<VS> = viewStates.shareIn(
        viewModelScope,
        SharingStarted.Lazily,
        replay = 0
    )
}
