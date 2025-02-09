package com.example.YliAliPeli

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var peli: YliAliPeli
    private lateinit var guessEditText: EditText
    private lateinit var statusText: TextView
    private lateinit var guessCountText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peli = YliAliPeli(1, 100)

        guessEditText = findViewById(R.id.editTextGuess)
        statusText = findViewById(R.id.statusText)
        guessCountText = findViewById(R.id.guessCount)
        val guessButton: Button = findViewById(R.id.guessButton)

        guessButton.setOnClickListener {
            val arvaus = guessEditText.text.toString().toIntOrNull()
            if (arvaus != null) {
                val tulos = peli.arvaa(arvaus)
                when (tulos) {
                    YliAliPeli.Arvaustulos.Low -> statusText.text = "Arvauksesi on liian pieni!"
                    YliAliPeli.Arvaustulos.High -> statusText.text = "Arvauksesi on liian suuri!"
                    YliAliPeli.Arvaustulos.Hit -> {
                        statusText.text = "Onneksi olkoon, arvasit oikein!"
                        guessButton.isEnabled = false
                    }
                }
                guessCountText.text = "Arvauksia: ${peli.guesses}"
            }
        }
    }
}
