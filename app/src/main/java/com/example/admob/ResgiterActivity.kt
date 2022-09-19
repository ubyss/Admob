package com.example.admob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register_acitivity.*

class ResgiterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)

        auth = Firebase.auth

        btnRegister.setOnClickListener {

            val email: String = txtEmailregister.text.toString()
            val password: String = txtPasswordregister.text.toString()

            if(
                email.isEmpty() ||
                password.isEmpty()
            ){
                Toast.makeText(this, "Todos os campos devem ser preeenchidos", Toast.LENGTH_SHORT).show()
            } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
            }
        }

    }

    private fun updateUI(user: FirebaseUser?) {

    }

}