package com.example.vrgsoft_test_yevstropov_vsevolod

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain

@SuppressLint("SetTextI18n")
@BindingAdapter("authorName")
fun TextView.setAuthorName(r: RedditDomain?): CharSequence? {
    r?.let {
        text = "Пользователь: " + r.author.toString()
    }
    return text
}

@SuppressLint("SetTextI18n")
@BindingAdapter("howMuchTimePassed")
fun TextView.setHowMuchTimePassed(r: RedditDomain?): CharSequence? {
    r?.let {
        val timeleft = System.currentTimeMillis() - it.createdUtc
        val sb = StringBuffer()
        val days = timeleft/86400000
        val hours = timeleft/3600000


    }
    return text
}

@SuppressLint("SetTextI18n")
@BindingAdapter("howMuchComments")
fun TextView.setHowMuchComments(r: RedditDomain?): CharSequence? {
    r?.let {
        when (it.commentCount) {
            null -> "Комментариев пока нет"
            0 -> "Комментариев пока нет"
            1 -> text = r.commentCount.toString() + " комментарий."
            else -> text = r.commentCount.toString() + " комментариев."
        }
        text = r.commentCount.toString() + "комментариев."
    }
    return text
}

@BindingAdapter("setImage")
fun bindImage(imgView: ImageView, r: RedditDomain?) {
    val imgUrl = r?.image
    imgUrl?.let {
        //val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(it)
            .into(imgView)
    }
}