package com.example.challenge4.presentation.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.databinding.ActivityLoginBinding
import com.example.challenge4.presentation.ui.auth.register.RegisterActivity
import com.example.challenge4.presentation.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory.getInstance(this))[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
        val userLogin = sharedPreferences.getBoolean("isUserLogin", false)
        if (userLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btnClicked()

    }

    private fun btnClicked() {
        binding.btnLogin.setOnClickListener {
            if (isValidInput()) {
                validateAccount(
                    binding.edtEmailInput.text.toString(),
                    binding.edtPasswordInput.text.toString()
                )
            }
        }

        binding.goRegister.setOnClickListener {
            navigateToRegisterScreen()
        }
    }


    private fun validateAccount(email: String, password: String) {

        viewModel.getUser(email, password).observe(this) { user ->
            if (user != null) {
                val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isUserLogin", true)
                editor.apply()

                val accountData = getSharedPreferences("userAccount", MODE_PRIVATE)
                val accountEdit = accountData.edit()
                accountEdit.putString("email", email)
                accountEdit.apply()

                navigateToMainScreen()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(): Boolean {
        val email = binding.edtEmailInput.text.toString()
        val password = binding.edtPasswordInput.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Email invalid!"
            return false
        }

        if (password.isEmpty()) {
            binding.edtPassword.error = "Password cannot be empty"
            return false
        }

        return true
    }


    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun navigateToRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}