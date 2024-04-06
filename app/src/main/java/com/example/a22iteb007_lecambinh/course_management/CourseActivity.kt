package com.example.a22iteb007_lecambinh.course_management

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a22iteb007_lecambinh.databinding.ActivityCourseBinding

class CourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseBinding
    private lateinit var db: CourseDBHelper
    private lateinit var courseAdapter: CourseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CourseDBHelper(this)
        courseAdapter = CourseAdapter(db.getAllCourses(), this)

        binding.rvCourse.layoutManager = LinearLayoutManager(this)
        binding.rvCourse.adapter = courseAdapter

        binding.btnAdd.setOnClickListener {
            val myIntent = Intent(this, AddCourseActivity::class.java)
            startActivity(myIntent)
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        }



    }

    override fun onResume() {
        super.onResume()
        courseAdapter.refreshData(db.getAllCourses())
    }
}