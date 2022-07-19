package com.example.tbc_course_14.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.tbc_course_14.models.Content

class MyDiffUtil(
    private val oldList:List<Content>,
    private val newList:List<Content>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                false
            }
            else -> true
        }
    }
}