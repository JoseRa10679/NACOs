package com.example.nacos.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nacos.R
import com.example.nacos.databinding.ActivityEdoxabanBinding
import com.example.nacos.viewmodels.EdoxabanViewModel

class EdoxabanActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityEdoxabanBinding.inflate(layoutInflater)
    }


    private val mViewModel: EdoxabanViewModel by lazy {
        ViewModelProvider(this).get(EdoxabanViewModel::class.java)
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
                        this@EdoxabanActivity,
                        "Función Renal versión: $version\n@Josera. Mayo 2020",
                        Toast.LENGTH_SHORT
                    ).apply {
                       setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                       show()
                    }

            }
            R.id.action_settings->{
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
        setSupportActionBar(binding.toolbarEDO)

        with(binding){
            funcionRenalE.setOnCheckedChangeListener{ _, _ -> cajas()  }
            bajoPeso.setOnCheckedChangeListener{ _, _ ->cajas() }
            inhibidores.setOnCheckedChangeListener{ _, _ -> cajas()}


            mViewModel.getResultadoE().observe(this@EdoxabanActivity,{
                resultadoE.text = it
                Log.d("TAG", it.toString())
            })
        }

    }



    private fun cajas(){
        with(binding){
            mViewModel.setResulltadoE(if (funcionRenalE.isChecked || bajoPeso.isChecked || inhibidores.isChecked) {
                getString(R.string._30_edoxaban)
            }else{
                getString(R.string._60_edoxaban)
            })
        }
    }
}
