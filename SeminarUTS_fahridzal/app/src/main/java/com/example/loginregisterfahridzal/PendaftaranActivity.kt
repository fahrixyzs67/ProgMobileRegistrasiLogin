package com.example.loginregisterfahridzal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText

class PendaftaranActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran)

        // --- 1. LOGIKA NAVIGASI NAVBAR ---
        val btnHome = findViewById<ImageButton>(R.id.btn_home)
        val btnHasil = findViewById<ImageButton>(R.id.btn_hasil)

        btnHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        btnHasil.setOnClickListener {
            // Navigasi ke HasilActivity
            startActivity(Intent(this, HasilActivity::class.java))
        }


        // Inisialisasi View
        val tilNama = findViewById<TextInputLayout>(R.id.tilNama)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPhone = findViewById<TextInputLayout>(R.id.tilPhone)

        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)

        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spinnerSeminar = findViewById<Spinner>(R.id.spinnerSeminar)
        val cbSetuju = findViewById<CheckBox>(R.id.cbSetuju)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 1. Setup Spinner (Data Hardcode)
        val listSeminar = arrayOf(
            "Pilih Seminar",
            "Android Modern",
            "Web Development",
            "Cyber Security",
            "UI/UX Design",
            "Cloud Computing"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSeminar)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeminar.adapter = adapter

        // 2. Validasi Real-time: Nama
        etNama.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) tilNama.error = "Nama wajib diisi" else tilNama.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 3. Validasi Real-time: Email
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                if (email.isEmpty()) tilEmail.error = "Email wajib diisi"
                else if (!email.contains("@")) tilEmail.error = "Email harus mengandung '@'"
                else tilEmail.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 4. Validasi Real-time: Nomor HP (Tanpa angka 0 di depan)
        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val phone = s?.toString() ?: ""
                if (phone.isEmpty() || !phone.startsWith("8")) {
                    etPhone.setText("8")
                    etPhone.setSelection(etPhone.text?.length ?: 1)// Pindahkan kursor ke akhir
                    return
                }
                if (phone.isEmpty()) {
                    tilPhone.error = "Nomor HP wajib diisi"
                } else if (phone.startsWith("0")) {
                    tilPhone.error = "Jangan awali dengan 0, langsung angka 8"
                } else if (phone.length < 9 || phone.length > 12) {
                    tilPhone.error = "Input 9-12 digit angka"
                } else {
                    tilPhone.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // // --- TOMBOL SUBMIT ---//
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val selectedSeminarPos = spinnerSeminar.selectedItemPosition

            // Logika Nomor WA (+62)
            val fullPhoneNumber = "+62$phone"

            // 1. Validasi Field Kosong (Harus dicek sebelum dialog muncul)
            if (nama.isBlank() || email.isBlank() || phone.isBlank() ||
                selectedGenderId == -1 || selectedSeminarPos == 0
            ) {
                Toast.makeText(this, "Tolong lengkapi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // --- BAGIAN ADAPTER DI DALAM SINI SUDAH DIHAPUS AGAR PILIHAN TIDAK TER-RESET ---

            // 2. Validasi Checkbox
            if (!cbSetuju.isChecked) {
                Toast.makeText(this, "Ceklis persetujuan dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Tampilkan Dialog Konfirmasi jika validasi di atas lolos
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Data")
            builder.setMessage("Apakah data yang Anda isi sudah benar?")

            // Tombol YA
            builder.setPositiveButton("Ya") { dialog, which ->
                // Jika klik Ya, baru pindah ke HasilActivity
                val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

                // Mengambil item yang sedang terpilih sebelum intent dikirim
                val seminar = spinnerSeminar.selectedItem.toString()

                val intent = Intent(this, HasilActivity::class.java).apply {
                    putExtra("NAMA", nama)
                    putExtra("EMAIL", email)
                    putExtra("PHONE", fullPhoneNumber)
                    putExtra("GENDER", gender)
                    putExtra("SEMINAR", seminar)
                }

                Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

            // Tombol TIDAK
            builder.setNegativeButton("Tidak") { dialog, which ->
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

}