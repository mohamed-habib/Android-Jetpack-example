package com.android.mohamed_habib.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.mohamed_habib.ui.R
import com.android.mohamed_habib.ui.base.BaseRecyclerViewAdapter
import com.bumptech.glide.Glide


object BindingAdapters {

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:data")
    @JvmStatic
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clear()
                addData(items)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:liveData")
    @JvmStatic
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: LiveData<List<T>>?) {
        items?.value?.let { itemList ->
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clear()
                addData(itemList)
            }
        }
    }

    @BindingAdapter("android:imageUrl", "android:placeHolder")
    @JvmStatic
    fun setImageUrl(
        imageView: ImageView, url: String?, placeHolder: Drawable? = imageView.context.getDrawable(
            R.drawable.ic_place_holder
        )
    ) {
        url?.let {
            Glide.with(imageView.context)
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .into(imageView)

        }
    }


    @BindingAdapter("android:fadeVisible")
    @JvmStatic
    fun setFadeVisible(view: View, visible: Boolean? = true) {
        if (view.tag == null) {
            view.tag = true
            view.visibility = if (visible == true) View.VISIBLE else View.GONE
        } else {
            view.animate().cancel()
            if (visible == true) {
                if (view.visibility == View.GONE)
                    view.fadeIn()
            } else {
                if (view.visibility == View.VISIBLE)
                    view.fadeOut()
            }
        }
    }
}