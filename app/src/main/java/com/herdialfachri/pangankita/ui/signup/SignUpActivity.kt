package com.herdialfachri.pangankita.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.herdialfachri.pangankita.R
import com.herdialfachri.pangankita.ui.data.HelperClass
import com.herdialfachri.pangankita.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupName: EditText
    private lateinit var signupUsername: EditText
    private lateinit var signupEmail: EditText
    private lateinit var signupPassword: EditText
    private lateinit var loginRedirectText: TextView
    private lateinit var signupButton: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Temukan Toolbar
        val toolbar: Toolbar = findViewById(R.id.profileback)
        setSupportActionBar(toolbar)

        supportActionBar?.title = ""

        // Atur navigation click listener
        toolbar.setNavigationOnClickListener {
            // Intent ke LoginActivity
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        signupName = findViewById(R.id.signup_name)
        signupEmail = findViewById(R.id.signup_email)
        signupUsername = findViewById(R.id.signup_username)
        signupPassword = findViewById(R.id.signup_password)
        loginRedirectText = findViewById(R.id.loginRedirectText)
        signupButton = findViewById(R.id.signup_button)

        signupButton.setOnClickListener {
            val name = signupName.text.toString()
            val email = signupEmail.text.toString()
            val username = signupUsername.text.toString()
            val password = signupPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Please fill all the form", Toast.LENGTH_SHORT)
                    .show()
            } else {
                database = FirebaseDatabase.getInstance()
                reference = database.getReference("users")

                val helperClass = HelperClass(name, email, username, password)
                reference.child(username).setValue(helperClass)

                Toast.makeText(
                    this@SignUpActivity,
                    "You have signed up successfully!",
                    Toast.LENGTH_SHORT
                ).show()

                // Redirect to LoginActivity and clear all previous activities
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        loginRedirectText.setOnClickListener {
            // Directly go to LoginActivity
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
