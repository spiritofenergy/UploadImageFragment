package com.kodex.uploadimagefragment

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kodex.uploadimagefragment.databinding.FragmentUploadBinding
import com.kodex.uploadimagefragment.viewmodel.UploadViewModel

class UploadFragment : Fragment() {
    private lateinit var image : ImageView
    private lateinit var btnBrowse: Button
    private lateinit var btnUpload: Button

    private var storageRef = Firebase.storage

    private lateinit var uri: Uri
    private lateinit var binding: FragmentUploadBinding
    private lateinit var viewModel: UploadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = UploadViewModel()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUploadBinding.inflate(layoutInflater)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}