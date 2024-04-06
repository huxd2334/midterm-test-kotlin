package com.example.a22iteb007_lecambinh.course_management

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class CourseDBHelper(context: Context) : android.database.sqlite.SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object{
        private const val DATABASE_NAME = "course_management"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "courses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_CREDIT = "credit"
        private const val COLUMN_SEMESTER = "semester"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_CREDIT INTEGER," +
                "$COLUMN_SEMESTER TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertCourse(course: Course){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, course.name)
            put(COLUMN_CREDIT, course.credit)
            put(COLUMN_SEMESTER, course.semester)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllCourses(): List<Course>{
        val list = mutableListOf<Course>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
         while(cursor.moveToNext()){
             val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
             val name = cursor.getString(cursor.getColumnIndexOrThrow((COLUMN_NAME)))
             val credit = cursor.getInt(cursor.getColumnIndexOrThrow((COLUMN_CREDIT)))
             val semester = cursor.getString(cursor.getColumnIndexOrThrow((COLUMN_SEMESTER)))

             val course = Course(id, name, credit, semester)
             list.add(course)

         }
        cursor.close()
        db.close()
        return list
    }

    fun getCourseByID(courseID:Int): Course {
        val db = readableDatabase
        val query = "Select * from $TABLE_NAME where $COLUMN_ID = $courseID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow((COLUMN_NAME)))
        val credit = cursor.getInt(cursor.getColumnIndexOrThrow((COLUMN_CREDIT)))
        val semester = cursor.getString(cursor.getColumnIndexOrThrow((COLUMN_SEMESTER)))

        cursor.close()
        db.close()
        return  Course(id, name, credit, semester)
    }
    fun editCourse(course: Course){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, course.name)
            put(COLUMN_CREDIT, course.credit)
            put(COLUMN_SEMESTER, course.semester)
        }
        val whereClause  = "$COLUMN_ID=?"
        val whereArg = arrayOf(course.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArg)
        db.close()
    }

    fun deleteCourse(courseID:Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArg = arrayOf(courseID.toString())
        db.delete(TABLE_NAME, whereClause, whereArg)
        db.close()
    }
}