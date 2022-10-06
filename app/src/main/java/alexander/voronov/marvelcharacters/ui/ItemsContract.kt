package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.domain.entities.Result

interface ItemsContract {

    interface View {
        fun showItems(items: List<Result>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onLoad()
    }
}