package com.example.lesson5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var mutableList = mutableListOf(User("shem","shem@live.com", "password"))

    private var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun onClear(){
        val email = findViewById<EditText>(R.id.editTextText)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        email.text.clear()
        password.text.clear()
        checkBox.isChecked = false;
    }
    fun onCreate(v: View){
        onClear()
        val intent = Intent(this, CreateAccountActivity::class.java)
        intent.putExtra("users", mutableList as Serializable)
        resultContracts.launch(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                this.mutableList = data.getSerializableExtra("users") as MutableList<User>
            }
        }
    }

    fun onShow(v: View){
        var checkBox = findViewById<CheckBox>(R.id.checkBox)
        var password = findViewById<EditText>(R.id.editTextTextPassword)

        if(checkBox.isChecked)
        {
            password.transformationMethod = null
        }
        else
        {
            password.transformationMethod = PasswordTransformationMethod()
        }
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
                onClear()
                val intent = Intent(this, ShoppingActivity::class.java)
                intent.putExtra("name", user.name)
                startActivity(intent)
            }
        }
    }
}