package com.gdsc.todo

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavHostFragment()
    }

    private fun setupNavHostFragment() {
        val navHost = NavHostFragment.create(R.navigation.nav_graph)

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_main_container, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commit()
    }
}