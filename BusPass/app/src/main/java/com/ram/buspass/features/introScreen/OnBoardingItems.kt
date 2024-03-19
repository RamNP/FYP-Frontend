package com.ram.buspass.features.introScreen

import com.ram.buspass.R

class OnBoardingItems(
    val image: Int,
    val title: Int,
    val desc: Int
) {
    companion object {
        fun getData(): List<OnBoardingItems> {
            return listOf(
                OnBoardingItems(R.mipmap.ic_maps, R.string.OnBoardingTitle1, R.string.OnBoardingDesc1),
                OnBoardingItems(R.mipmap.ic_bus, R.string.OnBoardingTitle1, R.string.OnBoardingDesc1),
                OnBoardingItems(R.mipmap.img_2, R.string.OnBoardingTitle1, R.string.OnBoardingDesc1),

            )
        }
    }
}