package com.luizeduardobrandao.componentesinterface

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.componentesinterface.databinding.ActivityTimeBinding
import java.util.Calendar

class TimeActivity :
    AppCompatActivity(),
    View.OnClickListener,
    TimePickerDialog.OnTimeSetListener
{

    private val binding: ActivityTimeBinding by lazy { ActivityTimeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonTimePicker.setOnClickListener(this)
        binding.buttonTimePickerCurrent.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            // Exibe um diálogo com o horário definido
            R.id.button_time_picker -> {
                TimePickerDialog(
                    this,
                    this,
                    17,
                    35,
                    true).show()
            }

            // Exibe um diálogo com o horário atual
            R.id.button_time_picker_current -> {
                val now = Calendar.getInstance()
                val currentHour = now.get(Calendar.HOUR_OF_DAY)
                val currentMinute = now.get(Calendar.MINUTE)

                TimePickerDialog(
                    this,
                    this,
                    currentHour,
                    currentMinute,
                    true
                ).show()
            }
        }
    }

    // TimePickerDialog - Resposta
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        binding.textTimePicker.text = getString(R.string.time_format, hourOfDay, minute)
        binding.textTimePickerCurrent.text = getString(R.string.time_format, hourOfDay, minute)
    }
}