package com.anagabriela.ec03k

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anagabriela.ec03k.databinding.ItemBinding
import com.squareup.picasso.Picasso

class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    private val bindig =ItemBinding.bind(view)
    fun bind(image:String){
        Picasso.get().load(image).into(bindig.img)
    }
}