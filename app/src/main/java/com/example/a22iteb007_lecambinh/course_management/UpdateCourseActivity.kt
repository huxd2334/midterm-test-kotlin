package com.example.a22iteb007_lecambinh.course_management

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.course_management.helper.CourseDBHelper
import com.example.a22iteb007_lecambinh.course_management.model.Course
import com.example.a22iteb007_lecambinh.databinding.ActivityUpdateCourseBinding

class UpdateCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCourseBinding
    private lateinit var db: CourseDBHelper
    private var courseID:Int  = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enable "Up" button on CourseActivity.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Course management"

        db = CourseDBHelper(this)
        courseID = intent.getIntExtra("id", -1)
        if(courseID == -1){
            finish()
            return
        }

        val course = db.getCourseByID(courseID)
        binding.etID.setText(courseID.toString())

        // Disable for ID
        binding.etID.isFocusable = false
        binding.etID.isFocusableInTouchMode = false

        binding.etNameUpdate.setText(course.name)
        binding.etCreditUpdate.setText(course.credit.toString())
        binding.etSemesterUpdate.setText(course.semester)

        binding.btnSaveUpdate.setOnClickListener {
            val newName = binding.etNameUpdate.text.toString()
            val newCredit = binding.etCreditUpdate.text.toString().toIntOrNull() ?: 0
            val newSemester = binding.etSemesterUpdate.text.toString()

            // credit mustn't be 0
            if(newCredit == 0){
                Toast.makeText(this, "Credit is not Zero", Toast.LENGTH_SHORT).show()
            }
            // semester must be a roman numeral
            else if(isValidRomanNumeral(newSemester)){
                val updatedCourse = Course(courseID, newName, newCredit, newSemester)
                db.editCourse(updatedCourse)
                finish()
                Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Invalid semester input. It must be a Roman number", Toast.LENGTH_LONG).show()
            }
        }
    }

    // check if the semester is a roman numeral
    fun isValidRomanNumeral(input: String): Boolean {
        val romanNumeralPattern = Regex("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")
        return romanNumeralPattern.matches(input)
    }
}