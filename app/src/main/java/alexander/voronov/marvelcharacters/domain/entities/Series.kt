package alexander.voronov.marvelcharacters.domain.entities

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: String
)