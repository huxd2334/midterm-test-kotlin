package com.example.a22iteb007_lecambinh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.course_management.CourseActivity
import com.example.a22iteb007_lecambinh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCourse.setOnClickListener {
            var courseIntent = Intent(this, CourseActivity::class.java)
            startActivity(courseIntent)
        }

    }
}