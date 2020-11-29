package com.example.nacos.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nacos.R
import com.example.nacos.databinding.ActivityApixabanBinding
import com.example.nacos.viewmodels.ApixabanViewModel

class ApixabanActivity : AppCompatActivity(), View.OnLongClickListener {

    private val binding by lazy {
        ActivityApixabanBinding.inflate(layoutInflater)
    }

    /*
     *  Declaramos la variable by lazy para que se active solo si se necesita
     *
     * */
    private val miViewModel: ApixabanViewModel by lazy {
        ViewModelProvider(this).get(ApixabanViewModel::class.java)
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
                    this@ApixabanActivity,
                    "Función Renal versión: $version\n@Josera. Mayo 2020",
                    Toast.LENGTH_SHORT
                ).apply {
                    setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }

            }
            R.id.action_settings -> {
                Intent(this, AcercadeActivity::class.java).also {
                    startActivity(it)
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }

//</editor-folder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAPI)

        with(binding) {
            ochentaA.setOnLongClickListener(this@ApixabanActivity)
            sesentakA.setOnLongClickListener(this@ApixabanActivity)
            creatinina15.setOnLongClickListener(this@ApixabanActivity)

            ochentaA.setOnCheckedChangeListener { _, _ -> valor() }
            sesentakA.setOnCheckedChangeListener { _, _ -> valor() }
            creatinina15.setOnCheckedChangeListener { _, _ -> valor() }
            funcionRenalA.setOnCheckedChangeListener { _, _ -> valor() }

            miViewModel.getResultadoA().observe(this@ApixabanActivity, {
                resultadoA.text = it
            })
        }


    }


    private fun valor() {

        with(binding) {
            var v = 0
            if (ochentaA.isChecked) v++
            if (sesentakA.isChecked) v++
            if (creatinina15.isChecked) v++
            if (funcionRenalA.isChecked || v >= 2) {
                miViewModel.setResultadoA(getString(R.string._25_cada_12_horas))
            } else {
                miViewModel.setResultadoA(getString(R.string._5mg_cada_12_horas))
            }
        }

    }

    /**
     * Called when a view has been clicked and held.
     *
     * @param v The view that was clicked and held.
     * @return true if the callback consumed the long click, false otherwise.
     */
    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            binding.ochentaA.id, binding.sesentakA.id, binding.creatinina15.id -> {
                Toast.makeText(applicationContext, R.string.strTag10, Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.TOP, 0, 0)
                    val toastView = view
                    toastView.setBackgroundColor(
                        ContextCompat.getColor(
                            toastView.context,
                            R.color.colorAccent
                        )
                    )
                    show()
                }

            }
        }
        return false
    }
}
