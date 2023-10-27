package com.example.challenge4.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge4.R
import com.example.challenge4.databinding.ActivityMainBinding
import com.example.challenge4.domain.model.Note
import com.example.challenge4.presentation.adapter.NoteAdapter
import com.example.challenge4.presentation.ui.addnote.AddNoteActivity
import com.example.challenge4.presentation.ui.auth.login.LoginActivity
import com.example.challenge4.presentation.ui.editnote.EditNoteActivity


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
        setSupportActionBar(binding.toolbar)
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
                        startEditNoteActivity(note)
                    }
                })
            } else {
                visibilityEmptyData(true)
            }
        }
    }

    private fun startEditNoteActivity(note : Note) {
        val intent = Intent(this, EditNoteActivity::class.java)
        intent.putExtra(EditNoteActivity.KEY_DATA, note)
        intent.putExtra(EditNoteActivity.KEY_ID, note.id)
        startActivity(intent)
    }

    private fun visibilityEmptyData(boolean: Boolean) {
        binding.noData.visibility = if (boolean) View.VISIBLE else View.GONE
        binding.rvNote.visibility = if (boolean) View.GONE else View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                showLogoutConfirmationDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Log out Confirmation")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton("Yes") { dialog, which ->
            performLogout()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun performLogout() {
        clearUserLoginState()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun clearUserLoginState() {
        val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserLogin", false)
        editor.apply()
    }
}