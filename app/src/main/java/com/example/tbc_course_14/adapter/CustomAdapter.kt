package com.example.tbc_course_14.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_14.databinding.GridViewBinding
import com.example.tbc_course_14.diffUtil.MyDiffUtil
import com.example.tbc_course_14.models.Content


typealias removeClick = (content:Content) -> Unit
typealias editClick = (content:Content,position:Int) -> Unit

class CustomAdapter(private var content: List<Content>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    lateinit var removeClick: removeClick
    lateinit var editClick: editClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return content.size
    }


    fun setData(newPersonList: List<Content>) {
        val diffUtil = MyDiffUtil(oldList = content, newList = newPersonList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        content = newPersonList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding:GridViewBinding):RecyclerView.ViewHolder(binding.root){


        private lateinit var currentItem: Content
        fun bind(){
            currentItem = content[adapterPosition]
            binding.apply {
                nameView.text = currentItem.name
                descriptionView.text = currentItem.description
                imageButtonRemove.setOnClickListener{
                    removeClick(
                        currentItem
                    )
                }
                imageButtonEdit.setOnClickListener{
                    editClick(
                        currentItem,adapterPosition
                    )
                }
            }
        }

    }
}