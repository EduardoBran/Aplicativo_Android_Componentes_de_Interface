package com.luizeduardobrandao.componentesinterface

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.luizeduardobrandao.componentesinterface.databinding.ActivityProgressBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class ProgressActivity : AppCompatActivity() {

    private val binding: ActivityProgressBinding by lazy { ActivityProgressBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Listener do botão para iniciar simulação
        binding.btnStartProgress.setOnClickListener {
            simulateProgress()
        }
    }

    private fun simulateProgress(){

        // Usa lifecycleScope para gerenciar coroutine ligada ao ciclo de vida
        lifecycleScope.launch {
            val max = binding.progressBarDeterminate.max
            for (i in 0..max step 5){
                delay(200) // simula trabalho de background
                binding.progressBarDeterminate.progress = i
                binding.tvProgressStatus.text = "Progresso $i%"
            }
        }
    }
}