package com.example.admob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val btnStart: Button = findViewById(R.id.startBtn)
        val nameIpt: EditText = findViewById(R.id.nameIpt)
        btnStart.setOnClickListener {
            if (nameIpt.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }else{
                val dbFire = Firebase.firestore
                val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

                val name = hashMapOf(
                    "name" to nameIpt.text.toString(),
                )

                dbFire.collection("/${currentUser}").document("name")
                    .set(name)
                    .addOnSuccessListener {
                        Log.d("Firestore", "Sucess")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error adding document", e)
                    }
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameIpt.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}





