package com.example.a22iteb007_lecambinh

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb007_lecambinh.course_management.CourseActivity
import com.example.a22iteb007_lecambinh.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCourse.setOnClickListener {
            val courseIntent = Intent(this, CourseActivity::class.java)
            startActivity(courseIntent)
        }

        binding.btnSolve.setOnClickListener {
            var calIntent = Intent(this, SolveEquation::class.java)
            startActivity(calIntent)
        }

        binding.btnCheck.setOnClickListener {
            var checkIntent = Intent(this, CheckPrimeNum::class.java)
            startActivity(checkIntent)
        }

        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val navView:NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_manage -> {
                    var courseIntent = Intent(this, CourseActivity::class.java)
                    startActivity(courseIntent)
                }
                R.id.nav_cal->{
                    var calIntent = Intent(this, SolveEquation::class.java)
                    startActivity(calIntent)
                }
                R.id.nav_check->{
                    var checkIntent = Intent(this, CheckPrimeNum::class.java)
                    startActivity(checkIntent)
                }

            }
            true
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}