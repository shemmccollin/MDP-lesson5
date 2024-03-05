package com.example.lesson5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val mutableList = mutableListOf(User("shem","shem@live.com", "password"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSignin(v: View){
        val email = findViewById<EditText>(R.id.editTextText)
        val password = findViewById<EditText>(R.id.editTextTextPassword)

        if(email.text.isNullOrBlank())
        {
            Toast.makeText(this, "${email.hint} can not be empty", Toast.LENGTH_LONG).show()
        }
        else if(password.text.isNullOrBlank())
        {
            Toast.makeText(this, "${password.hint} can not be empty", Toast.LENGTH_LONG).show()
        }
        else
        {
            val user = mutableList.find { user -> user.email_mobile == email.text.toString() && user.password == password.text.toString() }

            if(user == null)
            {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
            }
            else
            {
                val intent = Intent(this, ShoppingActivity::class.java)
                intent.putExtra("name", user.name)
                startActivity(intent)
            }
        }
    }
}