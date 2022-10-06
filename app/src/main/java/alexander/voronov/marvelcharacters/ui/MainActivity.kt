package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.app
import alexander.voronov.marvelcharacters.databinding.ActivityMainBinding
import alexander.voronov.marvelcharacters.domain.entities.Result
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity(), ItemsContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ItemsAdapter()
    private lateinit var presenter: ItemsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        presenter = extractPresenter()
        presenter.attach(this)
    }

    private fun extractPresenter(): ItemsContract.Presenter =
        lastCustomNonConfigurationInstance as? ItemsContract.Presenter
            ?: ItemsPresenter(app.itemsRepository)

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): ItemsContract.Presenter {
        return presenter
    }

    private fun initViews() {
        binding.activityMainLoadButton.setOnClickListener {
            presenter.onLoad()
        }
        initRecyclerView()
        showProgress(false)
    }

    override fun showItems(items: List<Result>) {
        adapter.setData(items)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.itemRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.itemRecyclerView.adapter = adapter
    }
}