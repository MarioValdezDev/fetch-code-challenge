package mx.mariovaldez.fetchcodechallenge.home.presentation.mappers

import mx.mariovaldez.fetchcodechallenge.domain.mapper.Mapper
import mx.mariovaldez.fetchcodechallenge.home.data.models.HiringRequest
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import javax.inject.Inject

internal class HiringUIMapper @Inject constructor() : Mapper<HiringRequest, HiringUI> {

    override fun map(value: HiringRequest): HiringUI = with(value) {
        HiringUI(
            id = id,
            listId = listId,
            name = name.toString()
        )
    }
}
