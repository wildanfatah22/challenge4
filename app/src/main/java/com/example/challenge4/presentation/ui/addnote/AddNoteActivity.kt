package com.example.challenge4.presentation.ui.addnote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.databinding.ActivityAddNoteBinding
import com.example.challenge4.domain.model.Note
import com.example.challenge4.presentation.ui.BottomSheetNavFragment
import com.example.challenge4.presentation.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddNoteActivity : AppCompatActivity() {

    private var noteId = -1
    var selectedColor = "#171C26"

    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: AddNoteViewModel by lazy {
        ViewModelProvider(
            this,
            AddNoteViewModelFactory.getInstance(this)
        )[AddNoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteId = intent.getIntExtra("noteId", -1)

        val currentDate = getCurrentDate()
        binding.tvDate.text = currentDate

        btnClicked()

    }

    private fun addNote() {

        val addNote = Note(
            id = 0,
            title = binding.edtTitle.text.toString(),
            subTitle = binding.edtSubtitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            date = binding.tvDate.text.toString(),
//            color = binding.playerDescEdit.text.toString(),
        )

        viewModel.insertNote(addNote)
        Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT)
            .show()
        startActivity(Intent(this, MainActivity::class.java)).apply {
            finishAffinity()
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        return currentDate
    }

    private fun btnClicked() {
        binding.imgMore.setOnClickListener {
            val bottomSheetFragment = BottomSheetNavFragment.newInstance(noteId)
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetFragmentTag")
        }

        binding.ivTick.setOnClickListener {
            if(validationInput()){
                addNote()
            }
        }
    }

    private val BroadcastReceiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {

            var actionColor = p1!!.getStringExtra("action")

            when(actionColor!!){

                "Blue" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Green" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Black" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                else -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }
            }
        }

    }

    private fun validationInput(): Boolean {
        if (binding.edtTitle.text.isNullOrEmpty()) {
            binding.edtTitle.error = "Title Cannot be Empty"
            return false
        }

        if (binding.edtSubtitle.text.isNullOrEmpty()) {
            binding.edtSubtitle.error = "Subtitle Cannot be Empty"
            return false

        }
        if (binding.edtDescription.text.isNullOrEmpty()) {
            binding.edtDescription.error = "Description Cannot be Empty"
            return false

        }
        return true
    }

}