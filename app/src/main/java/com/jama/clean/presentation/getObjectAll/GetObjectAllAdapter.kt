package com.jama.clean.presentation.getObjectAll

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jama.clean.databinding.ItemObjectBinding
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import okhttp3.internal.notify

class GetObjectAllAdapter :RecyclerView.Adapter<GetObjectAllAdapter.MyViewHolder>() {
    private val list = ArrayList<ObjectTypeData>()
    fun setData(list: List<ObjectTypeData>){
        this.list.clear()
        list.let {
            this.list.addAll(it)
        }
        notifyDataSetChanged()
    }
    inner class MyViewHolder(private val binding:ItemObjectBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: ObjectTypeData){
            binding.name.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemObjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])
}