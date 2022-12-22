package com.learning.uitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.learning.uitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    val manager = supportFragmentManager
    val fragments =arrayOf(ButtonsFragment(),
        ContainersFragment(),TextFragment(),WidgetsFragment(),HelpersFragment(),GoogleFragment(),LegacyFragment())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    movefragment(tab)
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    movefragment(tab)
                }
            })
            mainTabLayout.getTabAt(6)?.select()
        }

    }
    fun movefragment(tab: TabLayout.Tab?){
        Log.d("UiMainActivity","${tab!!.position}")
        when(tab.position){
            0-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[0]).commit()
            1-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[1]).commit()
            2-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[2]).commit()
            3-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[3]).commit()
            4-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[4]).commit()
            5-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[5]).commit()
            6-> manager.beginTransaction().replace(R.id.main_fragmentContainerView,fragments[6]).commit()
        }
    }

}