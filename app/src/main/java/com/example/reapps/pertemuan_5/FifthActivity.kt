package com.example.reapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reapps.R
import com.example.reapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding
    private var isCollapsed = false
    private var compactMode = false
    private var selectedSortId = R.id.sort_latest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Improvisasi Toolbar"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        binding.btnTop.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0)
        }

        setupScrollListener()
        setupAppBarEffect()
    }

    private fun setupScrollListener() {
        binding.nestedScrollView.viewTreeObserver.addOnGlobalLayoutListener {
            updateScrollProgress(binding.nestedScrollView.scrollY)
        }

        binding.nestedScrollView.setOnScrollChangeListener { v, _, scrollY, _, _ ->
            updateScrollProgress(scrollY)

            binding.btnTop.visibility = if (scrollY > 250) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun updateScrollProgress(scrollY: Int) {
        val child = binding.nestedScrollView.getChildAt(0) ?: return
        val totalContentHeight = child.height
        val visibleHeight = binding.nestedScrollView.height
        val maxScroll = (totalContentHeight - visibleHeight).coerceAtLeast(1)

        val progress = ((scrollY.toFloat() / maxScroll.toFloat()) * 100).toInt().coerceIn(0, 100)
        binding.progressRead.progress = progress

    }

    private fun setupAppBarEffect() {
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val fullyCollapsed = kotlin.math.abs(verticalOffset) >= appBarLayout.totalScrollRange

            if (fullyCollapsed && !isCollapsed) {
                isCollapsed = true
                supportActionBar?.title = "Fifth Collapsed"
                supportActionBar?.subtitle = null
                binding.toolbar.elevation = 12f
            } else if (!fullyCollapsed && isCollapsed) {
                isCollapsed = false
                supportActionBar?.title = "Activity Fifth"
                supportActionBar?.subtitle = "Improvisasi Toolbar"
                binding.toolbar.elevation = 0f
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.menu_compact_mode)?.isChecked = compactMode
        menu.findItem(selectedSortId)?.isChecked = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_profile -> {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_about -> {
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_compact_mode -> {
                compactMode = !item.isChecked
                item.isChecked = compactMode

                val newSize = if (compactMode) 14f else 16f
                binding.tvDesc1.textSize = newSize
                binding.tvDesc2.textSize = newSize
                binding.tvDesc3.textSize = newSize
                binding.tvDesc4.textSize = newSize
                binding.tvDesc5.textSize = newSize

                Toast.makeText(
                    this,
                    if (compactMode) "Compact Mode ON" else "Compact Mode OFF",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }

            R.id.sort_latest, R.id.sort_popular, R.id.sort_name -> {
                item.isChecked = true
                selectedSortId = item.itemId
                Toast.makeText(this, "Sort: ${item.title}", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}