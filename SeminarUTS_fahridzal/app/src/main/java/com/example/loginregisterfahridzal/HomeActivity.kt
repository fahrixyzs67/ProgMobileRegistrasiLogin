package com.example.loginregisterfahridzal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. Inisialisasi tombol berdasarkan ID di XML
        val btnHome = findViewById<ImageButton>(R.id.btn_home)
        val btnPendaftaran = findViewById<ImageButton>(R.id.btn_pendaftaran)
        val btnDaftar = findViewById<ImageButton>(R.id.btn_daftar)
        val btnDaftar1 = findViewById<Button>(R.id.btn_daftar_seminar_aktif)



        btnDaftar1.setOnClickListener {
            // Intent untuk pindah ke PendaftaranActivity
            val intent = Intent(this, PendaftaranActivity::class.java)
            startActivity(intent)
        }

        // 2. Logika klik untuk tombol Home
        btnHome.setOnClickListener {
            // Jika sudah di Home, mungkin tidak perlu pindah,
            // tapi ini contoh jika ingin pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 3. Logika klik untuk tombol Pendaftaran
        btnPendaftaran.setOnClickListener {
            val intent = Intent(this, PendaftaranActivity::class.java)
            startActivity(intent)
        }

        // 4. Logika klik untuk tombol Daftar/Hasil
        btnDaftar.setOnClickListener {
            val intent = Intent(this, HasilActivity::class.java)
            startActivity(intent)
        }
    }
}