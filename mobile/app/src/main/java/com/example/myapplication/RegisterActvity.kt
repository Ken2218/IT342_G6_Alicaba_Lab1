package com.example.myapplication

// --- PASTE THESE IMPORTS TO FIX THE RED TEXT ---
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
// ------------------------------------------------

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Make sure your XML file is named 'activity_register.xml'
        // and has IDs: etUsername, etEmail, etFirstName, etLastName, etPassword, btnRegister
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnRegister.setOnClickListener {
            val user = User(
                username = etUsername.text.toString(),
                email = etEmail.text.toString(),
                firstName = etFirstName.text.toString(),
                lastName = etLastName.text.toString(),
                password = etPassword.text.toString()
            )

            RetrofitClient.instance.registerUser(user).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Success!", Toast.LENGTH_SHORT).show()
                        // Uncomment when LoginActivity is created:
                        // startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this@RegisterActivity, "Failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}