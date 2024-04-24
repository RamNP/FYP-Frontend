package com.ram.buspass.features.introScreen

import com.ram.buspass.R

class OnBoardingItems(
    val image: Int,
    val title: Int,
) {
    companion object {
        fun getData(): List<OnBoardingItems> {
            return listOf(
                OnBoardingItems(R.mipmap.ic_onboarding1, R.string.OnBoardingTitle1,),
                OnBoardingItems(R.mipmap.ic_onboarding2, R.string.OnBoardingTitle2, ),
                OnBoardingItems(R.mipmap.ic_onboarding3, R.string.OnBoardingTitle3,),

            )
        }
    }
}