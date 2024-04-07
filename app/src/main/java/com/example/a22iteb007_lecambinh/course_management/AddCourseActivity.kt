package com.example.a22iteb007_lecambinh.course_management

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.course_management.helper.CourseDBHelper
import com.example.a22iteb007_lecambinh.course_management.model.Course
import com.example.a22iteb007_lecambinh.databinding.ActivityAddCourseBinding

class AddCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCourseBinding
    private lateinit var db: CourseDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enable "Up" button on CourseActivity.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Course management"

        db = CourseDBHelper(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val credit = binding.etCredit.text.toString().toIntOrNull() ?: 0
            val semester = binding.etSemester.text.toString()

            if( name.isNullOrBlank() || semester.isNullOrBlank() || credit == 0){
                Toast.makeText(this,"Do not leave empty any fields and credit is not 0", Toast.LENGTH_SHORT).show()
            }
            else if (isValidRomanNumeral(semester) && credit != 0) {
                val course = Course(0, name, credit, semester)
                db.insertCourse(course)
                finish()
                Toast.makeText(this, "Add successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid semester input. Please enter a Roman numeral.", Toast.LENGTH_LONG).show()
            }
        }
    }

    // validate check if the input is a roman number
    fun isValidRomanNumeral(input: String): Boolean {
        val romanNumeralPattern = Regex("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")
        return romanNumeralPattern.matches(input)
    }

}