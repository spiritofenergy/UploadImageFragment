package com.kodex.uploadimagefragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kodex.uploadimagefragment.databinding.ActivityRecycleBinding

class ActivityRecycle : AppCompatActivity() {
    private lateinit var binding : ActivityRecycleBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getImages()
    }


    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImagesAdapter(mList)
        binding.recyclerView.adapter = adapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getImages() {
        firebaseFirestore.collection("images")
            .get().addOnSuccessListener {
                for (i in it){
                    mList.add(i.data["pic"].toString())
                }
                adapter.notifyDataSetChanged()
            }
    }

}