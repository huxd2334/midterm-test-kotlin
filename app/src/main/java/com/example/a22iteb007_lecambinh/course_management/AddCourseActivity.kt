package com.example.a22iteb007_lecambinh.course_management

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.databinding.ActivityAddCourseBinding

class AddCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCourseBinding
    private lateinit var db: CourseDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CourseDBHelper(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val credit = binding.etCredit.text.toString().toInt()
            val semester = binding.etSemester.text.toString()
            val course = Course(0, name, credit, semester)
            db.insertCourse(course )
            finish()
            Toast.makeText(this, "Add successfully", Toast.LENGTH_SHORT).show()
        }



    }
}