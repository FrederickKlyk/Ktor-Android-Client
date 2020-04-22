package de.klyk.ktorclient.ui.main.websocket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.klyk.ktorclient.databinding.WebsocketsResponderListItemBinding
import de.klyk.ktorclient.databinding.WebsocketsSenderListItemBinding

class WebsocketsFragmentAdapter : RecyclerView.Adapter<WebsocketsFragmentAdapter.BaseViewHolder<*>>() {

    private val chatMessagesList = mutableListOf<Pair<String, Int>>()

    fun addMessage(message: String, viewType: Int) {
        chatMessagesList.add(message to viewType)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ChatType.SENDER.value -> {
                SenderViewHolder(WebsocketsSenderListItemBinding.inflate(inflater, parent, false))
            }
            ChatType.RESPONDER.value -> {
                ResponderViewHolder(WebsocketsResponderListItemBinding.inflate(inflater, parent, false))
            }
            else -> throw IllegalStateException("invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return chatMessagesList[position].second
    }

    override fun getItemCount(): Int = chatMessagesList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = chatMessagesList[position]

        when (holder) {
            is SenderViewHolder -> holder.bind(element.first)
            is ResponderViewHolder -> holder.bind(element.first)
            else -> throw IllegalArgumentException()
        }
    }

    /** Viewholder */
    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    inner class SenderViewHolder(val binding: WebsocketsSenderListItemBinding) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.viewModel = WebsocketsFragmentItemViewModel(item)
            binding.executePendingBindings()
        }
    }

    inner class ResponderViewHolder(val binding: WebsocketsResponderListItemBinding) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.viewModel = WebsocketsFragmentItemViewModel(item)
            binding.executePendingBindings()
        }
    }

}