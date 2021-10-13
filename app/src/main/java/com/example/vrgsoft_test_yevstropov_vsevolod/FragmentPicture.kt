package com.example.vrgsoft_test_yevstropov_vsevolod

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uklontestzadanie.dagger.App
import com.example.vrgsoft_test_yevstropov_vsevolod.databinding.FragmentPictureBinding
import javax.inject.Inject


class FragmentPicture : Fragment() {
    lateinit var binding: FragmentPictureBinding

    //    val repozitory = Repozitory()
//    val viewModelFactory = MainViewModelFactory(repozitory) //
//    val mainViewModel: MainViewModel by activityViewModels { viewModelFactory }
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by activityViewModels { viewModelProvider }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as App).getappComponent().inject(this)
        binding = DataBindingUtil.inflate<FragmentPictureBinding>(
            inflater,
            R.layout.fragment_picture, container, false
        )

        Log.d("ppp", mainViewModel.toString())
        mainViewModel.choosenitem.observe(
            viewLifecycleOwner, Observer {
                Log.d("ppp", it.author)
                binding.redditDomain = it
            }
        )
        return binding.root
    }

}
