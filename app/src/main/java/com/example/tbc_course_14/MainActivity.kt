package com.example.tbc_course_14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbc_course_14.adapter.CustomAdapter
import com.example.tbc_course_14.databinding.ActivityMainBinding
import com.example.tbc_course_14.models.Content
import com.example.tbc_course_14.models.ContentInfo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val contentAdapter by lazy {
        CustomAdapter(ContentInfo.logoContent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.mainRecycler.adapter = contentAdapter

        binding.refresher.setOnRefreshListener {
            contentAdapter.notifyDataSetChanged()  //diffUtil ვერ ვამუშავე :(
//            contentAdapter.setData(ContentInfo.logoContent)
            binding.refresher.isRefreshing = false
        }


        binding.floatingAdd.setOnClickListener {
            intent()
        }

        contentAdapter.removeClick = {
            removeItem(it)
        }
        contentAdapter.editClick = { name, position ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("edit", true)
            intent.putExtra("name", name.name)
            intent.putExtra("position", position)
            startActivity(intent)


        }


    }


    private fun removeItem(it: Content) {
        ContentInfo.logoContent = ContentInfo.logoContent.toMutableList()
        ContentInfo.logoContent.remove(it)
        contentAdapter.setData(ContentInfo.logoContent)
    }

    private fun intent() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }




}