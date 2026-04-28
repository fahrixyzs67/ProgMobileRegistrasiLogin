package com.example.loginregisterfahridzal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HasilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasildaftar)

        // Inisialisasi TextView
        val tvNama = findViewById<TextView>(R.id.tvHasilNama)
        val tvEmail = findViewById<TextView>(R.id.tvHasilEmail)
        val tvPhone = findViewById<TextView>(R.id.tvHasilPhone)
        val tvGender = findViewById<TextView>(R.id.tvHasilGender)
        val tvSeminar = findViewById<TextView>(R.id.tvHasilSeminar)
        val btnBeranda = findViewById<Button>(R.id.btnBeranda)

        // Menangkap data dari Intent
        val nama = intent.getStringExtra("NAMA")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")
        val gender = intent.getStringExtra("GENDER")
        val seminar = intent.getStringExtra("SEMINAR")

        // Menampilkan data ke TextView
        tvNama.text = nama
        tvEmail.text = email
        tvPhone.text = phone
        tvGender.text = gender
        tvSeminar.text = seminar

        // Tombol Beranda untuk kembali ke HomeActivity
        btnBeranda.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            // Clear task agar user tidak bisa kembali ke halaman hasil saat menekan tombol back
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}