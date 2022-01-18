package com.vj.readerpro.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.vj.readerpro.data.model.TimeFrameData
import com.vj.readerpro.databinding.ItemGroupSmsLayoutBinding

class GroupSMSAdapter(private val onTimeSelectListener: (timeDataData: TimeFrameData) -> Unit) : ListAdapter<TimeFrameData, GroupSMSAdapter.SMSVH>(OptionDiffUtil()) {

    private var isOption = true

    /*private val VIEW_TYPE_OPTION = 1337
    private val VIEW_TYPE_RESULT = 1338

    fun toggleViewType(optionFlow: Boolean = true) {
        isOption = optionFlow
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSVH {
        val view = ItemGroupSmsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SMSVH(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: SMSVH, position: Int) {
        val item = getItem(position)
        (holder.binding as? ItemGroupSmsLayoutBinding)?.let {
            it.timeFrame = item
            it.executePendingBindings()
            it.tvTimeFrame.setOnTouchListener { _, _ -> true }
        }

        /*when (holder.itemViewType) {
            VIEW_TYPE_OPTION -> {
                (holder.binding as? ItemPollOptionBinding)?.let {
                    it.option = item
                    it.executePendingBindings()
                    it.root.isSelected = (true == item.isSelected)
                    it.root.setOnClickListener {
                        onOptionSelectListener.invoke(item)
                    }
                }
            }
            VIEW_TYPE_RESULT -> {
                (holder.binding as? ItemPollResultBinding)?.let {
                    it.option = item
                    it.executePendingBindings()
                    it.seekbar.isSelected = (true == item.isSelected)
                    //disable seekbar manual dragging
                    it.seekbar.setOnTouchListener { _, _ -> true }
                }
            }
        }*/
    }

    inner class SMSVH(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}


private class OptionDiffUtil : DiffUtil.ItemCallback<TimeFrameData>() {
    override fun areItemsTheSame(oldItem: TimeFrameData, newItem: TimeFrameData): Boolean {
        return (oldItem == newItem)
    }

    override fun areContentsTheSame(oldItem: TimeFrameData, newItem: TimeFrameData): Boolean {
        return oldItem.index == newItem.index
    }
}