package alexander.voronov.marvelcharacters

import alexander.voronov.marvelcharacters.data.RetrofitItemsRepositoryImplementation
import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository
import android.app.Application
import android.content.Context

class App : Application() {
    val itemsRepository: ItemsRepository by lazy { RetrofitItemsRepositoryImplementation() }
}

val Context.app: App get() = applicationContext as App