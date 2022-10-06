package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.domain.entities.Result
import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository

class ItemsPresenter(
    private val itemsRepository: ItemsRepository
) : ItemsContract.Presenter {
    private var view: ItemsContract.View? = null

    private var itemsList: List<Result>? = null
    private var inProgress: Boolean = false

    override fun attach(view: ItemsContract.View) {
        this.view = view
        view.showProgress(inProgress)
        itemsList?.let { view.showItems(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onLoad() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        inProgress = true
        itemsRepository.getItems(
            onSuccess = {
                view?.showProgress(false)
                view?.showItems(it.data.results)
                itemsList = it.data.results
                inProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}