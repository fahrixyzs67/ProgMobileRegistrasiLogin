package com.example.loginregisterfahridzal

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.CheckBox


class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var spinner: Spinner
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var ivTogglePassword: ImageView
    private lateinit var ivToggleConfirm: ImageView

    private var isPasswordVisible = false
    private var isConfirmVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        radioGroup = findViewById(R.id.radioGroupGender)
        spinner = findViewById(R.id.spinnerjurusan)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)
        ivTogglePassword = findViewById(R.id.ivTogglePassword)
        ivToggleConfirm = findViewById(R.id.ivToggleConfirm)
        val cbMembaca = findViewById<CheckBox>(R.id.cb1)
        val cbMenulis = findViewById<CheckBox>(R.id.cb2)
        val cbOlahraga = findViewById<CheckBox>(R.id.cb3)
        val cbMusik = findViewById<CheckBox>(R.id.cb4)
        val cbGame = findViewById<CheckBox>(R.id.cb5)
        val cbTraveling = findViewById<CheckBox>(R.id.cb6)
        val cbMemasak = findViewById<CheckBox>(R.id.cb7)


        // Spinner
        val jurusan = arrayOf(" Memilih Jurusan", "Teknik Informatika", "Teknik Industri", "Desain Komunikasi Visual", "Bisnis Digital", "Managemen Ritel")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jurusan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val listener = { text: String, isChecked: Boolean ->
            if (isChecked) {
                Toast.makeText(this, "$text dipilih", Toast.LENGTH_SHORT).show()
            }
        }

        cbMembaca.setOnCheckedChangeListener { _, isChecked ->
            listener("Membaca", isChecked)
        }

        cbMenulis.setOnCheckedChangeListener { _, isChecked ->
            listener("Menulis", isChecked)
        }

        cbOlahraga.setOnCheckedChangeListener { _, isChecked ->
            listener("Olahraga", isChecked)
        }

        cbMusik.setOnCheckedChangeListener { _, isChecked ->
            listener("Musik", isChecked)
        }

        cbGame.setOnCheckedChangeListener { _, isChecked ->
            listener("Bermain Game", isChecked)
        }

        cbTraveling.setOnCheckedChangeListener { _, isChecked ->
            listener("Traveling", isChecked)
        }

        cbMemasak.setOnCheckedChangeListener { _, isChecked ->
            listener("Memasak", isChecked)
        }

        // Toggle Password
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            etPassword.inputType =
                if (isPasswordVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPassword.setSelection(etPassword.text.length)
        }

        ivToggleConfirm.setOnClickListener {
            isConfirmVisible = !isConfirmVisible
            etConfirmPassword.inputType =
                if (isConfirmVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etConfirmPassword.setSelection(etConfirmPassword.text.length)
        }
        //Action Login
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Register
        btnRegister.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val genderId = radioGroup.checkedRadioButtonId
            val jurusan = spinner.selectedItem.toString()
            val hobiList = mutableListOf<String>()

            if (cbMembaca.isChecked) hobiList.add("Membaca")
            if (cbMenulis.isChecked) hobiList.add("Menulis")
            if (cbOlahraga.isChecked) hobiList.add("Olahraga")
            if (cbMusik.isChecked) hobiList.add("Musik")
            if (cbGame.isChecked) hobiList.add("Bermain Game")
            if (cbTraveling.isChecked) hobiList.add("Traveling")
            if (cbMemasak.isChecked) hobiList.add("Memasak")

            val hobi = if (hobiList.isNotEmpty()) hobiList.joinToString(", ") else "Tidak ada"


            if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Email harus menggunakan @gmail.com", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (genderId == -1) {
                Toast.makeText(this, "Pilih jenis kelamin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if ( jurusan == "Memilih Jurusan") {
                Toast.makeText(this, "Pilih Jurusan!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = findViewById<RadioButton>(genderId).text.toString()


            // 🔹 Dialog Konfirmasi dulu
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data yang anda masukan sudah benar?")
                .setPositiveButton("Iya") { _, _ ->

                    // 🔹 Kalau Iya → tampilkan berhasil
                    AlertDialog.Builder(this)
                        .setTitle("Registrasi Berhasil")
                        .setMessage(
                            "Nama: $nama\n" +
                                    "Email: $email\n" +
                                    "Gender: $gender\n" +
                                    "Jurusan: $jurusan\n" +
                                    "Hobi: $hobi"
                        )
                        .setPositiveButton("OK", null)
                        .show()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}