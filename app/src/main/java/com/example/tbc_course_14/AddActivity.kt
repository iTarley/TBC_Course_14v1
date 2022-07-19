package com.example.tbc_course_14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbc_course_14.adapter.CustomAdapter
import com.example.tbc_course_14.databinding.ActivityAddBinding
import com.example.tbc_course_14.models.Content
import com.example.tbc_course_14.models.ContentInfo

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private var edit: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edit = intent.getBooleanExtra("edit", false)
        val getName = intent.getStringExtra("name").toString()

        if (edit) {

            binding.apply {
                itemActivityTextView.text = getString(R.string.edit_item)
                nameEditText.apply {
                    setText(getName)
                    isFocusable = false
                }
                binding.saveBtn.setOnClickListener {
                    editItem()
                }

            }


        } else {
            binding.saveBtn.setOnClickListener {
                addItem()
            }
        }


    }


    private fun editItem() {
        val getPosition = intent.getIntExtra("position", 0)
        ContentInfo.logoContent[getPosition].apply {
            name = binding.nameEditText.text.toString()
            description = binding.descriptionEditText.text.toString()
            finish()
        }
    }

    private fun addItem() {
        ContentInfo.logoContent.add(
            Content(
                binding.nameEditText.text.toString(),
                binding.descriptionEditText.text.toString()
            )
        )
        finish()
    }
}