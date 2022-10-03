package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository
import alexander.voronov.marvelcharacters.data.RetrofitItemsRepositoryImplementation
import alexander.voronov.marvelcharacters.databinding.ActivityMainBinding
import alexander.voronov.marvelcharacters.domain.entities.Result
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ItemsAdapter()
    private val itemsRepository: ItemsRepository = RetrofitItemsRepositoryImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.activityMainLoadButton.setOnClickListener { loadData() }
        initRecyclerView()
        showProgress(false)
    }

    private fun loadData() {
        showProgress(true)
        itemsRepository.getItems(
            onSuccess = {
                showProgress(false)
                onDataLoaded(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )
    }

    private fun onDataLoaded(data: List<Result>) {
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.itemRecyclerView.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.itemRecyclerView.isVisible = !inProgress
    }
}