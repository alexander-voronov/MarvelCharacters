package alexander.voronov.marvelcharacters.domain.entities

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
)