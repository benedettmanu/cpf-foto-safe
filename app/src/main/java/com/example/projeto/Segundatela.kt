package com.example.projeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.projeto.databinding.ActivitySegundatelaBinding

class Segundatela : AppCompatActivity() {

    private lateinit var binding: ActivitySegundatelaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundatelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cpfEditText = findViewById<EditText>(R.id.editUsername)
        val senhaEditText = findViewById<EditText>(R.id.editPassword)

        binding.btLogin.setOnClickListener {
            val cpf = cpfEditText.text.toString()
            val senha = senhaEditText.text.toString()

            if (cpf == "08993356912" && senha == "123456") {
                val navegarterceiraTela = Intent(this, Terceiratela::class.java)
                navegarterceiraTela.putExtra("CPF", cpf)
                startActivity(navegarterceiraTela)
            }
            else {
                AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("As credenciais informadas estão erradas")
                    .setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }
                    .show()
            }
        }

    }

}