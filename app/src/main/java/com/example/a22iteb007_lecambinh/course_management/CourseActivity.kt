package com.example.a22iteb007_lecambinh.course_management

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.text.toUpperCase
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22iteb007_lecambinh.course_management.adapter.CourseAdapter
import com.example.a22iteb007_lecambinh.course_management.helper.CourseDBHelper
import com.example.a22iteb007_lecambinh.databinding.ActivityCourseBinding

class CourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseBinding
    private lateinit var db: CourseDBHelper
    private lateinit var courseAdapter: CourseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enable "Up" button on MainActivity.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Course management"

        db = CourseDBHelper(this)
        courseAdapter = CourseAdapter(db.getAllCourses(), this)

        binding.rvCourse.layoutManager = LinearLayoutManager(this)
        binding.rvCourse.adapter = courseAdapter

        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrBlank()){

                    val searchResult = db.searchCourse(newText)
                    courseAdapter.refreshData(searchResult)
                }else{
                    courseAdapter.refreshData(db.getAllCourses())
                }
                return true

            }
        })

        binding.btnAdd.setOnClickListener {
            val myIntent = Intent(this, AddCourseActivity::class.java)
            startActivity(myIntent)
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        }







    }

    override fun onResume() {
        super.onResume()
        courseAdapter.refreshData(db.getAllCourses())
    }


}