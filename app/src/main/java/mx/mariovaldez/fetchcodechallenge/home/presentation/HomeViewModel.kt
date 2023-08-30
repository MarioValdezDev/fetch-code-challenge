package mx.mariovaldez.fetchcodechallenge.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.fetchcodechallenge.home.domain.usecases.GetHiring
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getHiring: GetHiring,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Default)
    val state: StateFlow<HomeState> get() = _state


    fun fetchData() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            kotlin.runCatching {
                getHiring()
            }
                .onSuccess {
                    _state.value = HomeState.Success(it)
                }
                .onFailure {
                    println("Error: $it")
                    _state.value = HomeState.Error
                }
        }

    }

    init {
        fetchData()
    }

    sealed class HomeState {
        object Default : HomeState()
        object Loading : HomeState()
        data class Success(val hiring: Map<Int,List<HiringUI>>) : HomeState()
        object Error : HomeState()
    }
}