package com.example.challenge4.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.R
import com.example.challenge4.databinding.ActivityLoginBinding
import com.example.challenge4.view.MainActivity
import com.example.challenge4.viewmodel.login.LoginViewModel
import com.example.challenge4.viewmodel.login.LoginViewModelFactory
import com.example.challenge4.model.local.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authRepository: AuthRepository
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory(authRepository))[LoginViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.view = binding.root
        observeData()
    }

    private fun observeData() {
        viewModel.auth.observe(this) { token ->
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
}