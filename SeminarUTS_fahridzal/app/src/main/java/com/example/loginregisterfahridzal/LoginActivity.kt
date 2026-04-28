package com.example.loginregisterfahridzal

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var ivTogglePassword: ImageView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 🔹 Inisialisasi
        etEmail = findViewById(R.id.etEmailLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
        ivTogglePassword = findViewById(R.id.ivTogglePasswordLogin)

        // 🔹 Toggle Password
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            etPassword.inputType =
                if (isPasswordVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            etPassword.setSelection(etPassword.text.length)
        }

        // 🔹 Klik tombol Login
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // 1. Reset error sebelumnya (opsional agar bersih)
            etEmail.error = null
            etPassword.error = null

            // 2. Validasi Input Kosong secara terpisah
            if (email.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong!"
                etEmail.requestFocus() // Fokus ke kolom email
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong!"
                etPassword.requestFocus() // Fokus ke kolom password
                return@setOnClickListener
            }

            // 3. Validasi Login (Email & Password Salah secara Spesifik)
            if (email != "fahridzal123@gmail.com") {
                etEmail.error = "Email tidak terdaftar!"
                etEmail.requestFocus()
            }
            else if (password != "fahri123") {
                etPassword.error = "Password salah!"
                etPassword.requestFocus()
            }
            else {
                // Jika semuanya benar
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // 🔹 Pindah ke halaman Register (HARUS DI LUAR)
        tvRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}