package com.kodex.uploadimagefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodex.uploadimagefragment.databinding.EachItemBinding
import com.squareup.picasso.Picasso

class ImagesAdapter (private var  mLst:List<String>): RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>(){

    inner class ImageViewHolder(var binding: EachItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with (holder.binding){
            with(mLst[position]){
                Picasso.get().load(this).into(imageView)
            }
        }
    }
    override fun getItemCount(): Int {
       return mLst.size
    }
}