package com.example.challenge4.presentation.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.databinding.ActivityLoginBinding
import com.example.challenge4.data.local.repository.LocalRepository
import com.example.challenge4.presentation.ui.auth.register.RegisterActivity
import com.example.challenge4.presentation.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this, LoginViewModelFactory(
            LocalRepository(getSharedPreferences(
                LocalRepository.PREF_NAME, MODE_PRIVATE))
        ).get(
            LoginViewModel::class.java)

        observeData()
        setupButton()
    }

    private fun setupButton() {
        binding.goRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.isLoading.observe(this) {
            showProgressBar(it)
        }

        viewModel.auth.observe(this) {token ->
            if (token.isNotEmpty() && token.isNotBlank()) {
                viewModel.saveToken(token)
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.checkLogin()


    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}