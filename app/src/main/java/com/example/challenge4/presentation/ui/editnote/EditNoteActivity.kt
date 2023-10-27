package com.example.challenge4.presentation.ui.editnote

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.databinding.ActivityEditNoteBinding
import com.example.challenge4.domain.model.Note
import com.example.challenge4.presentation.ui.BottomSheetNavFragment
import com.example.challenge4.presentation.ui.main.MainActivity

class EditNoteActivity : AppCompatActivity() {

    private var noteId = -1
    private lateinit var binding: ActivityEditNoteBinding
    private val viewModel: EditNoteViewModel by lazy {
        ViewModelProvider(
            this,
            EditNoteViewModelFactory.getInstance(this)
        )[EditNoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        btnClicked()
    }


    private fun editNote() {

        val addNote = Note(
            id = 0,
            title = binding.edtTitle.text.toString(),
            subTitle = binding.edtSubtitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            date = binding.tvDate.text.toString(),
        )

        viewModel.editNote(addNote)
        Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT)
            .show()
        startActivity(Intent(this, MainActivity::class.java)).apply {
            finishAffinity()
        }
    }

    private fun btnClicked() {
        binding.imgMore.setOnClickListener {
            val bottomSheetFragment = BottomSheetNavFragment.newInstance(noteId)
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetFragmentTag")
        }

        binding.ivTick.setOnClickListener {
            if(validationInput()){
                editNote()
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