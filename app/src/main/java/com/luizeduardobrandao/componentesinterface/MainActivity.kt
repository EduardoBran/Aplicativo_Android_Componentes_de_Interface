package com.luizeduardobrandao.componentesinterface

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.luizeduardobrandao.componentesinterface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Componente Toast
        binding.buttonToast.setOnClickListener(this)
        // Componente Snackbar
        binding.buttonSnack.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when (v.id) {

            // Toast
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "Toast", Toast.LENGTH_SHORT)
                // API 30+ ignora Toast.setGravity() em toasts padrão.
                // Exibindo a toast
                toast.show()
            }

            // Snackbar
            R.id.button_snack -> {
                // snackbar diferente da toast precisa informar onde ela vai aparecer
                val snack = Snackbar.make(binding.main, "Snackbar", Snackbar.LENGTH_SHORT)

                // customizando a snack
                snack.setTextColor(Color.MAGENTA)     // cor do texto
                snack.setBackgroundTint(Color.GREEN)  // cor do background
                // snack.setTextMaxLines(5)              // informando o numero de linhas (textos grandes)

                // Usuário pode interagir com a snackbar (neste exemplo aparecerá outro snackbar)
                snack.setAction("OutraSnack", View.OnClickListener {
                    Snackbar.make(binding.main, "Outra Snackbar aparecendo", Snackbar.LENGTH_SHORT).show()
                })

                // Exibindo a snackbar
                snack.show()
            }

        }
    }
}