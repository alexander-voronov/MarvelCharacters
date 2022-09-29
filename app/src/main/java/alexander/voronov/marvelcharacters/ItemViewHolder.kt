package alexander.voronov.marvelcharacters

import alexander.voronov.marvelcharacters.databinding.ElementRecyclerviewBinding
import alexander.voronov.marvelcharacters.entities.Result
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.element_recyclerview, parent, false)
) {
    private val binding = ElementRecyclerviewBinding.bind(itemView)

    fun bind(itemEntity: Result) {

        val thumbnailLink: String =
            itemEntity.thumbnail.path + "." + itemEntity.thumbnail.extension

        binding.thumbnailImageView.load(thumbnailLink)
        binding.itemIdTextView.text = itemEntity.id.toString()
        binding.itemNameTextView.text = itemEntity.name
    }
}