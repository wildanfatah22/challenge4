package com.example.challenge4.presentation.ui.addnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

//    private var noteId = -1
//    var selectedColor = "#171C26"

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
        return dateFormat.format(Date())
    }

    private fun btnClicked() {
        binding.imgMore.setOnClickListener {
//            val bottomSheetFragment = BottomSheetNavFragment.newInstance(noteId)
//            bottomSheetFragment.show(supportFragmentManager, "BottomSheetFragmentTag")
        }

        binding.ivTick.setOnClickListener {
            if(validationInput()){
                addNote()
            }
        }

        binding.ibtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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