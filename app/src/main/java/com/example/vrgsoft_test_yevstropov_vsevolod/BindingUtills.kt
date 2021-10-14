package com.example.vrgsoft_test_yevstropov_vsevolod

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain

@SuppressLint("SetTextI18n")
@BindingAdapter("authorName")
fun TextView.setAuthorName(r: RedditDomain?) {
    r?.let {
        text = "Пользователь: " + r.author.toString()
    }

}

@SuppressLint("SetTextI18n")
@BindingAdapter("howMuchComments")
fun TextView.setHowMuchComments(r: RedditDomain?) {
    r?.let {
        when (it.commentCount) {
            null -> text ="Комментариев пока нет"
            0 -> text ="Комментариев пока нет"
            1 -> text = r.commentCount.toString() + " комментарий."
            else -> text = r.commentCount.toString() + " комментариев."
        }
      //  text = r.commentCount.toString() + "комментариев."
    }
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
@BindingAdapter("setImageDetail")
fun bindImageDetail(imgView: ImageView, r: RedditDomain?) {
    val imgUrl = r?.image
    imgUrl?.let {
        //val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(it)
            .into(imgView)
    }
}
@SuppressLint("SetTextI18n")
@BindingAdapter("howMuchTimePassed")
fun TextView.setHowMuchTimePassed(r: RedditDomain?) {
    val relativeDate = DateUtils.getRelativeTimeSpanString(
        r!!.createdUtc*1000,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE
    )
    text = relativeDate


//    var sb = StringBuilder()
//    val timeleft = System.currentTimeMillis() - r?.createdUtc!!
//
//    if (r != null) {
//        text = DateUtils.getRelativeTimeSpanString(r.createdUtc)
//    }

//    r?.let {
//        val timeleft = System.currentTimeMillis() - it.createdUtc!!
//        val year = (timeleft / 31622400000)
//        val month = timeleft / 2592000000
//        val week = timeleft / 604800000
//        val days = timeleft / 86400000
//        val hours = timeleft / 3600000
//        val minute = timeleft / 60000
//        val seconds = timeleft / 1000

//        if (year > 0) {
//            sb.setLength(0)
//            sb.append(year)
//            sb.append(" г. назад")
//        } else if (month > 0) {
//            sb.setLength(0)
//            sb.append(month)
//            sb.append(" мес. назад")
//        } else if (week > 0) {
//            sb.setLength(0)
//            sb.append(week)
//            sb.append(" нед. назад")
//        } else if (days > 0) {
//            sb.setLength(0)
//            sb.append(days)
//            sb.append(" дн. назад")
//        } else if (hours > 0) {
//            sb.setLength(0)
//            sb.append(hours)
//            sb.append(" ч. назад")
//        } else if (minute > 0) {
//            sb.setLength(0)
//            sb.append(minute)
//            sb.append(" мин. назад")
//        } else {
//            sb.setLength(0)
//            sb.append(seconds)
//            sb.append(" сек. назад")
//        }
    //   }
    //   return sb
}