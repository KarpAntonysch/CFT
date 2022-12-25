package com.example.cft

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cft.databinding.HistoryCardBinding
import com.example.cft.room.BankingInformationModel

class RvAdapter:ListAdapter<BankingInformationModel,RvAdapter.BankingViewHolder>(BankingComparator()) {

    class BankingViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = HistoryCardBinding.bind(view)
        fun bind(card:BankingInformationModel,position: Int) = with(binding){
            number.text =(position+1).toString()
            tvCard.text = card.bin
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card,parent,false)
        return BankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankingViewHolder, position: Int) {
        val currentCard = getItem(position)
        holder.bind(currentCard,position)
    }
    class BankingComparator : DiffUtil.ItemCallback<BankingInformationModel>() {
        override fun areItemsTheSame(oldItem: BankingInformationModel, newItem: BankingInformationModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BankingInformationModel, newItem: BankingInformationModel): Boolean {
            return oldItem == newItem
        }
    }


}