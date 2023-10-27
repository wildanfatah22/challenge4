package com.example.challenge4.presentation.ui.editnote

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.databinding.ActivityEditNoteBinding
import com.example.challenge4.domain.model.Note
import com.example.challenge4.presentation.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

        val note = intent.getParcelableExtra<Note>(KEY_DATA) as Note
        noteId = intent.getIntExtra(KEY_ID, -1)

        val currentDate = getCurrentDate()
        binding.tvDate.text = currentDate

        loadNote(note)
        setUpViews()
    }

    private fun setUpViews() {
        btnClicked()
    }

    private fun loadNote(note: Note) {
        binding.apply {
            edtTitle.text = Editable.Factory.getInstance().newEditable(note.title)
            edtSubtitle.text = Editable.Factory.getInstance().newEditable(note.subTitle)
            tvDate.text = note.date
            edtDescription.text = Editable.Factory.getInstance().newEditable(note.description)
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun editNote() {

        val edit = Note(
            id = noteId, // Menggunakan noteId yang telah diambil dari intent
            title = binding.edtTitle.text.toString(),
            subTitle = binding.edtSubtitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            date = binding.tvDate.text.toString(),
        )

        viewModel.editNote(edit)
        Toast.makeText(this, "Edit data successfully", Toast.LENGTH_SHORT)
            .show()
        startActivity(Intent(this, MainActivity::class.java)).apply {
            finishAffinity()
        }
    }

    private fun btnClicked() {
//        binding.imgMore.setOnClickListener {
//            val bottomSheetFragment = BottomSheetNavFragment.newInstance(noteId)
//            bottomSheetFragment.show(supportFragmentManager, "BottomSheetFragmentTag")
//        }

        binding.ivTick.setOnClickListener {
            if(validationInput()){
                editNote()
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

    companion object {
        const val KEY_DATA = "Data"
        const val KEY_ID = "id"
    }
}