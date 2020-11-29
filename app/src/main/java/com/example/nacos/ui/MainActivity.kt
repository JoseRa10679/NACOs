package com.example.nacos.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nacos.R
import com.example.nacos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //<editor-folder desc = " Menu ">

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.version -> {
                lateinit var version: String
                var packageInfo: PackageInfo? = null
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, 0)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }


                if (packageInfo != null) version = packageInfo.versionName

                Toast.makeText(
                    this@MainActivity,
                    "Función Renal versión: $version\n@Josera. Mayo 2020",
                    Toast.LENGTH_SHORT
                ).apply {
                    setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }

            }

            R.id.action_settings -> {
                Intent(this, AcercadeActivity::class.java).apply {
                    startActivity(this)
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

//</editor-folder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.webEscGuias.movementMethod = LinkMovementMethod.getInstance()

        binding.dabigatran.setOnClickListener {
            Intent(this, DabigatranActivity::class.java).also {
                startActivity(it)
            }

        }

        binding.apixaban.setOnClickListener {
            Intent(this, ApixabanActivity::class.java).also {
                startActivity(it)
            }

        }

        binding.edoxaban.setOnClickListener {
            Intent(this, EdoxabanActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.rivaroxaban.setOnClickListener {
            Intent(this, RivaroxabanActivity::class.java).also {
                startActivity(it)
            }

        }
    }
}


