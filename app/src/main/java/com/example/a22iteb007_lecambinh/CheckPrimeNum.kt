package com.example.a22iteb007_lecambinh

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a22iteb007_lecambinh.databinding.ActivityCheckPrimeNumBinding
import kotlin.math.sqrt

class CheckPrimeNum : AppCompatActivity() {
    private lateinit var binding: ActivityCheckPrimeNumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckPrimeNumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enable "Up" button on MainActivity.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Check prime number"


        binding.btnClear.setOnClickListener {
            binding.etCheckNumber.setText("")
            binding.tvResultCheck.setText("Result here")
        }

        binding.btnCheck.setOnClickListener {
            val etNum = binding.etCheckNumber.text.toString()

            if(etNum.isBlank()){
                Toast.makeText(this, "Do not leave number empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val num = etNum.toDouble()
                val i:Int
                var flag:Int = 1

                // check 2 to sqrt(num)
                // if n is divisible between 2 & sqrt(num) => not prime
                for (k in sqrt(num).toInt() downTo 2 step 1){
                    if(num % k == 0.0) {
                        flag = 0
                        break
                    }
                }
                //corner case
                if (num < 2)
                    flag = 0  //=> not prime

                if(flag == 1)
                    isPrime()
                else
                    notPrime()
            }
        }
    }

    private fun isPrime() {
        val etNum = binding.etCheckNumber.text.toString()
        binding.tvResultCheck.text = "$etNum is Prime number"
    }

    fun notPrime(){
        val etNum = binding.etCheckNumber.text.toString()
        binding.tvResultCheck.text = "$etNum is not Prime number"
    }
}