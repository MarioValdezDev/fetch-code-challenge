package mx.mariovaldez.fetchcodechallenge.home.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HiringUI(
    val id: Int,
    val listId: Int,
    val name: String
) : Parcelable
