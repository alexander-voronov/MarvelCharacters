package alexander.voronov.marvelcharacters.domain.repository

import alexander.voronov.marvelcharacters.domain.entities.MyResponse

interface ItemsRepository {
    fun getItems(
        onSuccess: (MyResponse) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}