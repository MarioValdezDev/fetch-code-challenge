package mx.mariovaldez.fetchcodechallenge.home.data.models

import com.google.gson.annotations.SerializedName

data class HiringRequest(

    @SerializedName("id")
    val id: Int,

    @SerializedName("listId")
    val listId: Int,

    @SerializedName("name")
    val name: String?
)