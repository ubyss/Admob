package com.example.admob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_result.*

class PlacarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: com.example.admob.Result
        val db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)


        binding.bt_salvar.setOnClickListener{

            val usuariosMap = hashMapOf(
                "nome" to "Marcos",
                "placar" to 10,
            )
            db.collection("Usuários").document("Marcos")
                .set(usuariosMap).addOnCompleteListener {
                    Log.d("DB", "Sucesso ao salvar os dados do usuario")
                }.addOnFailureListener {

                }
        }


    }
}