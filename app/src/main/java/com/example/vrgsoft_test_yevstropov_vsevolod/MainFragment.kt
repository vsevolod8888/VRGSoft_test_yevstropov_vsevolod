package com.example.vrgsoft_test_yevstropov_vsevolod

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain

class MainFragment:Fragment(), RouteArchiveListener {
    lateinit var recyclerList: RecyclerView
    lateinit var adapter: Adapter
    //  lateinit var mainViewModel: MainViewModel
    val repozitory = Repozitory()
    val viewModelFactory = MainViewModelFactory(repozitory) //activityViewModels byViewModels поучить
    val mainViewModel:MainViewModel by activityViewModels<MainViewModel> { viewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("ppp",mainViewModel.toString())

        recyclerList = view.findViewById(R.id.recycler_list)
        adapter = Adapter(this)
        recyclerList.adapter = adapter

        mainViewModel.listRedditDomainVM.observe(
            viewLifecycleOwner, Observer {
                adapter.submitList(it)//
            }
        )
        return view
    }
    override fun onClickK(itemDetail: RedditDomain) {
        mainViewModel.onClickDetail(itemDetail)
        this.findNavController().navigate(R.id.action_mainFragment_to_fragmentPicture)
    }
}