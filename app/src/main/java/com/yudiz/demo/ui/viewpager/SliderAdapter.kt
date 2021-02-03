package com.yudiz.demo.ui.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.yudiz.demo.R
import com.yudiz.demo.databinding.SliderFragmentOneBinding

class SliderAdapter(var context: Context) : PagerAdapter() {
    lateinit var binding: SliderFragmentOneBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = SliderFragmentOneBinding.inflate(LayoutInflater.from(context), container, false)
        binding.sliderIv.setImageDrawable(context.getDrawable(getImageAt(position)))
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    private fun getImageAt(position: Int): Int {
        return R.drawable.ic_launcher_foreground
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}