package alexander.voronov.marvelcharacters

import alexander.voronov.marvelcharacters.entities.MyResponse

interface ItemsRepository {
    fun getItems(
        onSuccess: (MyResponse) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}