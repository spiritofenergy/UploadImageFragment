package com.kodex.uploadimagefragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.kodex.uploadimagefragment.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity() : AppCompatActivity() {
   private lateinit var image :ImageView
   private lateinit var btnBrowse: Button
   private lateinit var btnUpload: Button
   private lateinit var btnUploadFragment: Button

   private lateinit var binding: ActivityMainBinding

   private var storageRef = Firebase.storage

    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        storageRef = FirebaseStorage.getInstance()



        image =findViewById(R.id.firebaseimage)
        btnBrowse =findViewById(R.id.btn_browse)
        btnUpload =findViewById(R.id.upload_imagebtn)
      //  btnUploadFragment =findViewById(R.id.upload_go_to_fragment)

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                image.setImageURI(it)
                uri = it!!
            })

        btnBrowse.setOnClickListener {
            galleryImage.launch("image/*")
        }


      btnUpload.setOnClickListener {
        storageRef.getReference("images").child(System.currentTimeMillis().toString())
            .putFile(uri)
            .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { task ->
                            val userId = FirebaseAuth.getInstance().currentUser!!.uid

                            val mapImage = mapOf(
                                "url" to it.toString()
                            )
                            val databaseReference = FirebaseDatabase.getInstance().getReference("userImages")
                            databaseReference.child(userId).setValue(mapImage)
                                .addOnSuccessListener {
                                            Log.d("check","databaseReference")
                                    }
                                .addOnFailureListener{ error ->
                                    Log.d("check",it.toString())

                                }
                        }
            }
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = Navigation.findNavController(this,R.id.frag_host)
        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }


}





