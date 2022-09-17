package com.example.newwordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.newwordle.FourLetterWordList.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wordToGuess = getRandomFourLetterWord()

        val editText = findViewById(R.id.et_guess) as EditText

        val textView1 = findViewById(R.id.t_guessOne) as TextView
        val textView2 = findViewById(R.id.t_guessTwo) as TextView
        val textView3 = findViewById(R.id.t_guessThree) as TextView
        val textView4 = findViewById(R.id.t_correct) as TextView

        val button = findViewById(R.id.b_checkGuess) as Button

        fun checkGuess(guess: String, wordToGuess: String) : String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (guess[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }

        button.setOnClickListener(){
            textView1.isVisible = true;
            textView1.text = checkGuess(editText.toString(), wordToGuess)

            if(textView1.equals(wordToGuess)){
                textView4.isVisible = true;
                textView4.text = textView1.text;
            }
            else{
                button.setOnClickListener(){
                    textView2.isVisible = true;
                    textView2.text = checkGuess(editText.toString(), wordToGuess)
                    if(textView2.equals(wordToGuess)){
                        textView4.isVisible = true;
                        textView4.text = textView2.text;
                    }
                    else{
                        button.setOnClickListener() {
                            textView3.isVisible = true;
                            textView3.text = checkGuess(editText.toString(), wordToGuess)
                            if(textView3.equals(wordToGuess)){
                                textView4.isVisible = true;
                                textView4.text = textView3.text;
                            }
                            else{
                                textView4.isVisible = true;
                                textView4.text = "WRONG! The CORRECT answer is " + wordToGuess;
                            }
                        }
                    }
                }
            }
        }

    }
}