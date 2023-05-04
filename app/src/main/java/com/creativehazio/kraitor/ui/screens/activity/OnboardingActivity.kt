package com.creativehazio.kraitor.ui.screens.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.databinding.ActivityOnboardingBinding
import com.creativehazio.kraitor.ui.adapter.OnboardingViewPagerAdapter
import com.creativehazio.kraitor.ui.screens.fragment.onboarding.CommunityOnboardFragment
import com.creativehazio.kraitor.ui.screens.fragment.onboarding.CreateStunningArtOnboardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnboardingBinding
    private lateinit var textviewDots : Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //TODO Three fragments, create stunning arts save and share arts, community and inspirations, Navigate to sign in
        val fragmentList = arrayListOf(
            CreateStunningArtOnboardFragment(),
            CommunityOnboardFragment()
        )

        val adapter = OnboardingViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.apply {

            onboardingGetStartedButton.setOnClickListener {
                startActivity(Intent(this@OnboardingActivity, SignInActivity::class.java))
                finish()
            }

            onboardingViewpager2.adapter = adapter
            onboardingViewpager2
                .registerOnPageChangeCallback(object : OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        setUpIndicatorLayout(position)
                    }
                })
        }

    }

    fun setUpIndicatorLayout(position : Int){
        textviewDots = arrayOfNulls(2)
        binding.indicatorLayout.removeAllViews()

        for (i in textviewDots.indices) {
            textviewDots[i] = TextView(this)
            textviewDots[i]?.text = Html.fromHtml("&#8226")
            textviewDots[i]?.textSize = 35F
            textviewDots[i]?.setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            binding.indicatorLayout.addView(textviewDots[i])
        }

        textviewDots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))

    }

}










