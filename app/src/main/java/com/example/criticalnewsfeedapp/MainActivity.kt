package com.example.criticalnewsfeedapp

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.criticalnewsfeedapp.databinding.ActivityMainBinding
import com.example.criticalnewsfeedapp.databinding.FragmentArticlesListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BiometricActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setSupportActionBar(findViewById(R.id.topAppBar))

        val navController = binding.navHostFragment.getFragment<NavHostFragment>().findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.articlesListingFragment
            )
        )
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
        setContentView(binding.root)
    }

    fun setActionBarTitle(title: String) {
        binding.topAppBar.title = title
    }
}