package alexander.voronov.marvelcharacters.ui

import alexander.voronov.marvelcharacters.domain.entities.Result
import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private val data = mutableListOf<Result>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Result>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}