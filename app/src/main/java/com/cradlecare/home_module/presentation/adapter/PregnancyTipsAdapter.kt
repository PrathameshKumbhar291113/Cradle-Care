package com.cradlecare.home_module.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cradlecare.databinding.ItemTipsBinding
import com.cradlecare.utils.PregnancyTips

class PregnancyTipsAdapter(private val tipsList: List<PregnancyTips>) :
    RecyclerView.Adapter<PregnancyTipsAdapter.PregnancyTipsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PregnancyTipsViewHolder {
        val binding = ItemTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PregnancyTipsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PregnancyTipsViewHolder, position: Int) {
        val tip = tipsList[position]
        with(holder.binding){
            title.text = tip.titleTip
            tipDescription.text = tip.tipDescription
        }
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }

    class PregnancyTipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTipsBinding.bind(itemView)
    }
}
