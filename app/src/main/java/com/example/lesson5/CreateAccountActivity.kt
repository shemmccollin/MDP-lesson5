package com.example.lesson5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.Serializable

class CreateAccountActivity : AppCompatActivity() {
    private var mutableList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        mutableList = intent.getSerializableExtra("users") as MutableList<User>
    }

    fun onContinue(v: View){
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)

        if(username.text.isNullOrBlank())
        {
            Toast.makeText(this, "Username can not be empty", Toast.LENGTH_LONG).show()
        }
        else if(email.text.isNullOrBlank())
        {
            Toast.makeText(this, "Email can not be empty", Toast.LENGTH_LONG).show()
        }
        else if(password.text.isNullOrBlank() || password.text.toString().count() < 6)
        {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show()
        }
        else if(confirmPassword.text.isNullOrBlank() || confirmPassword.text.toString().count() < 6)
        {
            Toast.makeText(this, "Confirm password must be at least 6 characters", Toast.LENGTH_LONG).show()
        }
        else{
            if(password.text.toString() != confirmPassword.text.toString()){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                return
            }
            if(mutableList.any{user -> user.email_mobile == email.text.toString()})
            {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_LONG).show()
                return
            }
            if(mutableList.any{user -> user.name == username.text.toString()})
            {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_LONG).show()
                return
            }

            Toast.makeText(this, "User successfully created", Toast.LENGTH_LONG).show()

            mutableList.add(User(username.text.toString(), email.text.toString(), password.text.toString() ))
            val intent = intent
            intent.putExtra("users", mutableList as Serializable)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}