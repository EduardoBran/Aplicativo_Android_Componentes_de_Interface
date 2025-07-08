package com.luizeduardobrandao.componentesinterface

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.luizeduardobrandao.componentesinterface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    View.OnClickListener,
    AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener,
    CompoundButton.OnCheckedChangeListener
{

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
        // Função para carregar os valores do spinner dinâmico
        // loadSpinner()

        // Componente Spinner Eventos
        binding.buttonGetSpinner.setOnClickListener(this)
        binding.buttonSetSpinner.setOnClickListener(this)
        binding.spinnerDinamico.onItemSelectedListener = this

        // Componente Seekbar (pode atribuir um valor definido para iniciar)
        binding.seekbar.setOnSeekBarChangeListener(this)
        binding.seekbar.progress = 25

        // Componente Switch
        // binding.switchOnOff.isChecked = true // aplicação inicia com switch marcado.
        binding.switchOnOff.setOnCheckedChangeListener(this)

        // Componente Checkbox
        // binding.checkboxOnOff.isChecked = true // aplicação inicia com checkbox marcado.
        binding.checkboxOnOff.setOnCheckedChangeListener(this)

        // Componente RadioGroup com RadioButtons
        // binding.radioYes.isChecked = true // aplicação inicia com radiobutton marcado.
        binding.radioYes.setOnCheckedChangeListener(this)
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


            // Spinner Eventos (utilizando spinner dinamico)
            R.id.button_get_spinner -> {
                // Retorna o texto do elemento selecionado
                // val str = binding.spinnerDinamico.selectedItem
                // Retorna a posição/index do elemento selecionado
                // val id1 = binding.spinnerDinamico.selectedItemId
                // Também retorna a posição/index do elemento selecionado
                loadSpinner()
            }

            R.id.button_set_spinner -> {
                // Atribui uma posição ao elemento do spinner
                // binding.spinnerDinamico.setSelection(2)
            }
        }
    }

    // Propriedades de "AdapterView.OnItemSelectedListener" (Evento de Item Selecionado)
    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        // parent: AdapterView - Adapter onde a seleção ocorreu
        // view: View - Layout do elemento clicado
        // position: Int - Posição do elemento selecionado na lista
        // id: Long - Posição da linha selecionada

        Toast.makeText(this, "$position - $id", Toast.LENGTH_SHORT).show()
    }

    // Evento que pode ser disparado quando o adapter se torna vazio
    override fun onNothingSelected(parent: AdapterView<*>) {
        TODO("Not yet implemented")
    }

    // Carrega valores no spinner dinâmico
    private fun loadSpinner(){
        val list = listOf<String>("Dinamico1", "Dinamico2", "Dinamico3", "Dinamico4")

        // val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list)
        // val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        // val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list)

        binding.spinnerDinamico.adapter = adapter

    }

    // Propriedades de "SeekBar.OnSeekBarChangeListener"
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        // fromUser é true quando a alteração é feita pelo toque no elemento
        // Se a atribuição é feita por código, fromUser é false

        // Recuperando o valor
        binding.textSeekbar.text = "$progress - $fromUser"
    }

    // Quando o componente começa a ser arastado
    override fun onStartTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Start tracking", Toast.LENGTH_SHORT).show()
    }

    // Trata evento de toque final no Seekbar
    override fun onStopTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Stop tracking", Toast.LENGTH_SHORT).show()
    }

    // Propriedade de "CompoundButton.OnCheckedChangeListener"
    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {

        // verificando qual componente está sendo marcado (switch ou checkbox)
        when (buttonView.id) {
            // Switch
            R.id.switch_on_off -> {
                val str = "Switch: $isChecked"
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
            }

            // Checkbox
            R.id.checkbox_on_off -> {
                val str = "Checkbox: $isChecked"
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
            }

            // RadioGroup com RadioButtons
            R.id.radio_yes -> {
                val str = "Radio Yes: $isChecked"
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
            }
        }
    }
}