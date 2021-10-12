package com.example.vrgsoft_test_yevstropov_vsevolod

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.vrgsoft_test_yevstropov_vsevolod.databinding.FragmentPictureBinding
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class FragmentPicture : DialogFragment() {
    lateinit var binding: FragmentPictureBinding
    val repozitory = Repozitory()
    val viewModelFactory = MainViewModelFactory(repozitory) //
    val mainViewModel:MainViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPictureBinding>(
            inflater,
            R.layout.fragment_picture, container, false
        )

        //val mainViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
        Log.d("ppp",mainViewModel.toString())
        mainViewModel.choosenitem.observe(
            this, Observer {
                Log.d("ppp",it.author)
                binding.redditDomain = it

            }
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.setLayout(width, height)
    }

}