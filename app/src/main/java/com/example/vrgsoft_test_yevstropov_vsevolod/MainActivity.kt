package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerList:RecyclerView
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerList = findViewById(R.id.recycler_list)
        val repozitory = Repozitory()
        val viewModelFactory = MainViewModelFactory(repozitory)

        val sleepDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


    }
}