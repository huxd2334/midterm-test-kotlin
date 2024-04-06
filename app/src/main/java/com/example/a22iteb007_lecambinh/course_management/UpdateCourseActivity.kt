package com.example.a22iteb007_lecambinh.course_management

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.databinding.ActivityUpdateCourseBinding

class UpdateCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCourseBinding
    private lateinit var db: CourseDBHelper
    private var courseID:Int  = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CourseDBHelper(this)
        courseID = intent.getIntExtra("id", -1)
        if(courseID == -1){
            finish()
            return
        }

        val course = db.getCourseByID(courseID)
        binding.etNameUpdate.setText(course.name)
        binding.etCreditUpdate.setText(course.credit.toString())
        binding.etSemesterUpdate.setText(course.semester)

        binding.btnSaveUpdate.setOnClickListener {
            val newName = binding.etNameUpdate.text.toString()
            val newCredit = binding.etCreditUpdate.text.toString().toInt()
            val newSemester = binding.etSemesterUpdate.text.toString()
            val updatedCourse = Course(courseID, newName, newCredit, newSemester)
            db.editCourse(updatedCourse)
            finish()
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}