package com.eraysirdas.countriesbook.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eraysirdas.countriesbook.R


fun ImageView.intoGlide(imageUrl : String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imageUrl)
        .into(this)
}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius=40f
        start()
    }
}
//data binding
@BindingAdapter("android:downloadUrl")
fun downloadImage(view : ImageView, url : String?){
    view.intoGlide(url, placeholderProgressBar(view.context))
}