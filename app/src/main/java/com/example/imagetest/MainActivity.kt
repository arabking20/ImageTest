package com.example.imagetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.example.imagetest.model.view.FragementListMovie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //super.getFragmentManager.beginTransaction()(R.id.container)

        if (findViewById<FragmentContainerView>(R.id.container) !=null)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FragementListMovie())

            .commit()
    }
}