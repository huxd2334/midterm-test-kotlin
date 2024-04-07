package com.example.a22iteb007_lecambinh.course_management.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a22iteb007_lecambinh.R
import com.example.a22iteb007_lecambinh.course_management.UpdateCourseActivity
import com.example.a22iteb007_lecambinh.course_management.helper.CourseDBHelper
import com.example.a22iteb007_lecambinh.course_management.model.Course

class CourseAdapter (private var courses:List<Course>, context: Context):RecyclerView.Adapter<CourseAdapter.ViewHolder>(){

    private val db: CourseDBHelper = CourseDBHelper(context)

    //ViewHolder class represent the item view
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvID:TextView = itemView.findViewById(R.id.tvCourseID)
        val tvName:TextView = itemView.findViewById(R.id.tvCourseName)
        val tvCredit:TextView = itemView.findViewById(R.id.tvCourseCredit)
        val tvSemester:TextView = itemView.findViewById(R.id.tvCourseSemester)
        val btnUpdate:ImageView = itemView.findViewById(R.id.btnUpdate)
        val btnDelete:ImageView = itemView.findViewById(R.id.btnDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]
        holder.tvName.text = course.name
        holder.tvID.text = course.id.toString()
        holder.tvCredit.text = course.credit.toString()
        holder.tvSemester.text = course.semester

        // button Update action
        holder.btnUpdate.setOnClickListener {
            val updateIntent = Intent(holder.itemView.context, UpdateCourseActivity::class.java).apply {
                putExtra("id", course.id)
            }
            holder.itemView.context.startActivity(updateIntent)
        }

        // button Delete action
        holder.btnDelete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(holder.itemView.context)
            alertDialog.setTitle("Delete ${course.name.toUpperCase()} course?")
            alertDialog.setMessage("Deleting will remove all this course's data")
            alertDialog.setPositiveButton("Delete", DialogInterface.OnClickListener{ dialog, which ->
                db.deleteCourse(course.id)
                refreshData(db.getAllCourses())
                Toast.makeText(holder.itemView.context, "Delete ${course.name} successfully", Toast.LENGTH_SHORT).show()
            })

            alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                // Do nothing
            })
            alertDialog.create()
            alertDialog.show()


        }
    }

    // function to F5 data in adapter
    fun refreshData(newCourse: List<Course>){
        courses = newCourse
        notifyDataSetChanged()
    }

}