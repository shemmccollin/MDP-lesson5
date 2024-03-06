package com.example.lesson5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val welcomeMsg = findViewById<TextView>(R.id.welcomeMsg)
        welcomeMsg.text = "Welcome ${intent.getStringExtra("name")}"
    }

    fun onImageClick(v: View){
        when(v.id){
            R.id.home -> {
                Toast.makeText(this, "Home and Kitchen category selected", Toast.LENGTH_LONG).show()
            }
            R.id.pet -> {
                Toast.makeText(this, "Pet supplies category selected", Toast.LENGTH_LONG).show()
            }
            R.id.beauty -> {
                Toast.makeText(this, "Beauty and personal care category selected", Toast.LENGTH_LONG).show()
            }
            R.id.toys -> {
                Toast.makeText(this, "Toys and games category selected", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }
}