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

        // 🔹 Toggle Password (Show / Hide)
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

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Gunakan email @gmail.com", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 Contoh validasi sederhana (dummy)
//            if (email == "admin@gmail.com" && password == "12345") {
//                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
//            }
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener {
                // langsung login berhasil
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            }

            // 🔹 Klik ke halaman Register
            tvRegister.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}