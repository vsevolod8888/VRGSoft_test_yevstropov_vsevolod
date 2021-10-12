package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels


class MainActivity : AppCompatActivity(), RouteArchiveListener {
    lateinit var recyclerList: RecyclerView
    lateinit var adapter: Adapter
  //  lateinit var mainViewModel: MainViewModel
    val repozitory = Repozitory()
    val viewModelFactory = MainViewModelFactory(repozitory)
    //        mainViewModel =
//            ViewModelProvider(
//                this, viewModelFactory
//            ).get(MainViewModel::class.java)
    val mainViewModel:MainViewModel by viewModels<MainViewModel> { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        recyclerList = findViewById(R.id.recycler_list)
        adapter = Adapter(this)
        recyclerList.adapter = adapter

        mainViewModel.listRedditDomainVM.observe(
            this, Observer {
                adapter.submitList(it)//
            }
        )
    }

    override fun onClickK(itemDetail: RedditDomain) {
        mainViewModel.onClickDetail(itemDetail)
        val dialog = FragmentPicture()
        dialog.show(supportFragmentManager, "customDialog")

    }
}