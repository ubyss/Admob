package com.example.admob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_placar.*
import kotlinx.android.synthetic.main.activity_result.*

class PlacarActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val dbFire = Firebase.firestore

        val db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        getData(dbFire)

    }

    fun getData(dbFire: FirebaseFirestore){

        val dados = ArrayList<String>()

        dbFire.collection("Usuários")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    dados.add("${document.data["nome"].toString()} - ${document.data["placar"].toString()}")


                    val lstContato = this.findViewById<ListView>(R.id.listView)
                    val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1, dados
                    )
                    lstContato.adapter = adapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("firestore", "Error getting documents.", exception)
            }
    }
}