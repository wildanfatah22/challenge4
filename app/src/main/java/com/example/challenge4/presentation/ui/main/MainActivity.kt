package com.example.challenge4.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge4.databinding.ActivityMainBinding
import com.example.challenge4.domain.model.Note
import com.example.challenge4.presentation.adapter.NoteAdapter
import com.example.challenge4.presentation.ui.addnote.AddNoteActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory.getInstance(this)
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        setUpRecyclerView()
        observeData()
        btnClicked()
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvNote.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNote.addItemDecoration(itemDecoration)
    }

    private fun btnClicked() {
        binding.btnFab.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        viewModel.getAllNote.observe(this) { note ->
            if (note.isNotEmpty()) {
                visibilityEmptyData(false)
                adapter = NoteAdapter(note)
                binding.rvNote.adapter = adapter

                adapter.setOnItemClickCallback(object : NoteAdapter.OnItemClickCallback {
                    override fun onItemClicked(note: Note) {
//                        navigateToAnotherActivity(note, MainActivity::class.java)
                    }

                    override fun onEditClicked(note: Note) {
//                        navigateToAnotherActivity(note, MainActivity::class.java)
                    }

                    override fun onDeleteClicked(note: Note) {
                        dialogDelete(note)
                    }
                })
            } else {
                visibilityEmptyData(true)
            }
        }
    }

    private fun dialogDelete(note: Note) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Noter")
        builder.setMessage("Are you sure you want to delete this note?")
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deletePlayer(note)
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

//    private fun navigateToAnotherActivity(note: Note, activity: Class<*>) {
//        val intent = Intent(this, activity)
//        intent.putExtra("note", note)
//        startActivity(intent)
//    }

    private fun visibilityEmptyData(boolean: Boolean) {
        binding.noData.visibility = if (boolean) View.VISIBLE else View.GONE
        binding.rvNote.visibility = if (boolean) View.GONE else View.VISIBLE
    }
}