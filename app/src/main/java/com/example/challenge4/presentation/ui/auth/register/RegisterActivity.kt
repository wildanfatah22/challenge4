package com.example.challenge4.presentation.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.challenge4.databinding.ActivityRegisterBinding
import com.example.challenge4.domain.model.User
import com.example.challenge4.presentation.ui.auth.login.LoginActivity
import com.example.challenge4.presentation.ui.main.MainActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(
            this,
            RegisterViewModelFactory.getInstance(this)
        )[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

    }

    private fun setUpViews() {
        btnClicked()
    }

    private fun btnClicked() {
        binding.btnRegister.setOnClickListener {
            observeData()
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.goLogin.setOnClickListener {
            navigateToLoginScreen()
        }
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun observeData() {
        if (isValidInput()) {
            registerAccount()
        }
    }

    private fun isValidInput(): Boolean {
        val email = binding.edtEmailInput.text.toString()
        val name = binding.edtNameInput.text.toString()
        val password = binding.edtPasswordInput.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Email invalid!"
            return false
        }

        if (name.isEmpty()) {
            binding.edtName.error = "Name cannot be empty"
            return false
        }

        if (password.isEmpty()) {
            binding.edtPassword.error = "Password cannot be empty"
            return false
        } else if (password.length < 8) {
            binding.edtNameInput.error = "Password must contain a minimum 8 character"
            return false
        }

        return true
    }

    private fun registerAccount() {
        val email = binding.edtEmailInput.text.toString()
        val name = binding.edtNameInput.text.toString()
        val password = binding.edtPasswordInput.text.toString()

        val userAccount = User(
            id = 0,
            name = name,
            email = email,
            password = password
        )

        viewModel.getUserEmail(email).observe(this) { user ->
            if (user != null) {
                binding.edtEmail.error = "Email sudah terdaftar"
                binding.edtEmailInput.requestFocus()
                Toast.makeText(
                    this,
                    "Email sudah terdaftar, silahkan coba email lain",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()
                saveUserData(email)
                viewModel.registerUser(userAccount)
                navigateToMainActivity()
            }
        }
    }

    private fun saveUserData(email: String) {
        val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserLogin", true)
        editor.apply()

        val accountData = getSharedPreferences("userAccount", MODE_PRIVATE)
        val accountEdit = accountData.edit()
        accountEdit.putString("email", email)
        accountEdit.apply()
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}