package com.example.vrgsoft_test_yevstropov_vsevolod

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vrgsoft_test_yevstropov_vsevolod.dagger.App
import com.example.vrgsoft_test_yevstropov_vsevolod.databinding.FragmentPictureBinding
import javax.inject.Inject

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.FileOutputStream


class FragmentPicture : Fragment() {
    lateinit var binding: FragmentPictureBinding

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
//        ActivityCompat.requestPermissions(requireActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE,1)
//        ActivityCompat.requestPermissions(requireActivity(),String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1)

        Log.d("ppp", mainViewModel.toString())
        mainViewModel.choosenitem.observe(
            viewLifecycleOwner, Observer {
                Log.d("ppp", it.author)
                binding.redditDomain = it
            }
        )
        binding.buttonSavePicture.setOnClickListener {
            checkPermission(binding.image)
        }
        return binding.root
    }

    private fun saveImageToGallery(imageView: ImageView) {
        val b: BitmapDrawable = imageView.drawable as BitmapDrawable
        val bitmap: Bitmap = b.bitmap
        var outputStream: FileOutputStream? = null
        //Images.Media.insertImage(requireActivity().contentResolver,bitmap,"ff","D")
       // var file: File = Environment.getExternalStorageDirectory()
        var file: File = Environment.getExternalStorageDirectory()
        var dir = File(file.absolutePath + "/${Environment.DIRECTORY_DCIM}/Camera")//"/DCIM/Camera"
        dir.mkdirs()
        val filename = String.format("%d.png", System.currentTimeMillis())
        val outfile = File(dir, filename)
        try {
            outputStream = FileOutputStream(outfile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        } catch (e: Exception) {
            e.printStackTrace()


        }finally {
            try {
                outputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun checkPermission(imageView: ImageView) {
        var permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                Toast.makeText(requireContext(), "Вы дали разрешение", Toast.LENGTH_SHORT).show()
                saveImageToGallery(imageView)
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(
                    requireContext(),
                    "Отказано в разрешении\n$deniedPermissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        TedPermission.with(requireContext())
            .setPermissionListener(permissionlistener)
            .setPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .check()
    }

}
