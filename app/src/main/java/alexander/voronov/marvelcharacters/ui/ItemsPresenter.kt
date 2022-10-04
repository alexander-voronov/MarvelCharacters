package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository

class ItemsPresenter(
    private val itemsRepository: ItemsRepository
) : ItemsContract.Presenter {
    private var view: ItemsContract.View? = null

    override fun attach(view: ItemsContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onLoad() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        itemsRepository.getItems(
            onSuccess = {
                view?.showProgress(false)
                view?.showItems(it.data.results)
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
            }
        )
    }
}