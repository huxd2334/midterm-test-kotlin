package com.example.a22iteb007_lecambinh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.databinding.ActivitySolveEquationBinding

class SolveEquation : AppCompatActivity() {
    private lateinit var binding: ActivitySolveEquationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolveEquationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enable "Up" button on MainActivity.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Solve linear equation"

        binding.btnClear.setOnClickListener {
            binding.etANumber.setText("")
            binding.etBNumber.setText("")
            binding.tvResultEquation.setText("x =")
        }

        binding.btnDoSolve.setOnClickListener {
            val a = binding.etANumber.text.toString()
            val b = binding.etBNumber.text.toString()

            if (a.isBlank() || b.isBlank()){
                Toast.makeText(this, "Do not leave a or b empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val a_num = a.toInt()
                val b_num = b.toInt()

                if(a_num != 0 ){
                    // a is not = 0 => handle x = -b/a
                    val x = ((-b_num.toDouble()) / (a_num.toDouble())).toString()
                    binding.tvResultEquation.text = x

                }
                else if (b_num == 0){
                    // b is also = 0 => always true
                    binding.tvResultEquation.text = "Reality"

                }
                else{
                    // no solution
                    binding.tvResultEquation.text = "No solution"

                }


            }
        }

    }
}