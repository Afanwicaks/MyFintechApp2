package com.example.mvmm1632

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider as ViewModelProvider1

class MainActivity : AppCompatActivity() {

    private lateinit var etInputSatu : EditText
    private lateinit var etInputDua : EditText
    private lateinit var btnTambah : Button
    private lateinit var btnKurang : Button
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel() as T
            }
        }
        mainViewModel = ViewModelProvider1(this, factory)[MainViewModel::class.java]

        etInputSatu = findViewById(R.id.et_input_satu)
        etInputDua = findViewById(R.id.et_input_dua)
        btnTambah = findViewById(R.id.btn_tambah)
        btnKurang = findViewById(R.id.btn_kurang)

        mainViewModel.hasil.observe(this, Observer{hasil ->
            etInputSatu.setText(hasil.toString())
            etInputDua.setText("0.0")
        })

        btnTambah.setOnClickListener{
            mainViewModel.tambah(
                    etInputSatu.text.toString().toFloat(),
                    etInputDua.text.toString().toFloat()
            )
        }
        btnKurang.setOnClickListener{
            mainViewModel.kurang(
                    etInputSatu.text.toString().toFloat(),
                    etInputDua.text.toString().toFloat()
            )
        }
    }
}

