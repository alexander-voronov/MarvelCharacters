package alexander.voronov.marvelcharacters.domain.entities

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Long,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)