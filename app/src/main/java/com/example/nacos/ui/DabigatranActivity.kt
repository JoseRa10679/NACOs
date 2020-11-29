package com.example.nacos.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nacos.R
import com.example.nacos.databinding.ActivityDabigatranBinding
import com.example.nacos.viewmodels.DabigatranViewModel

class DabigatranActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener,
    View.OnLongClickListener {

    private val binding by lazy{
        ActivityDabigatranBinding.inflate(layoutInflater)
    }

    private val mViewModel: DabigatranViewModel by lazy {
        ViewModelProvider(this).get(DabigatranViewModel::class.java)
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
                        this@DabigatranActivity,
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

        setSupportActionBar(binding.toolbarDAB)

        with(binding){
           riesgo.setOnCheckedChangeListener(this@DabigatranActivity)
           mas80annos.setOnCheckedChangeListener(this@DabigatranActivity)
           creatinina3050.setOnCheckedChangeListener(this@DabigatranActivity)
           entre7580.setOnCheckedChangeListener(this@DabigatranActivity)
           verapamil.setOnCheckedChangeListener(this@DabigatranActivity)

           riesgo.setOnLongClickListener(this@DabigatranActivity)

            mViewModel.getResultadoD().observe(this@DabigatranActivity,{
                resultado.text = it
            })

            mViewModel.getComentarios().observe(this@DabigatranActivity,{
                comentarios.text = it
            })
        }

    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val cadena1 = getString(R.string.contrindicado_en_funci_n_renal_inferior_a_30_ml_h)
        val cadena2 = getString(R.string.verapamilo)

        with(binding){
            when(buttonView?.id){
                mas80annos.id->{
                    when{
                        buttonView.isChecked || verapamil.isChecked-> mViewModel.setResultadoD(getString(R.string._110mg_cada_12))
                        creatinina3050.isChecked || entre7580.isChecked || riesgo.isChecked -> mViewModel.setResultadoD(getString(
                            R.string._150_110_cada_12))
                        else-> mViewModel.setResultadoD(getString(R.string._150mg_cada_12_horas))
                    }

                }

                verapamil.id->{
                    when{
                        buttonView.isChecked || mas80annos.isChecked -> mViewModel.setResultadoD(getString(
                            R.string._110mg_cada_12))
                        creatinina3050.isChecked || entre7580.isChecked || riesgo.isChecked-> mViewModel.setResultadoD(getString(
                            R.string._150_110_cada_12))
                        else-> mViewModel.setResultadoD(getString(R.string._150mg_cada_12_horas))
                    }

                    comentarios.text = if(buttonView.isChecked){
                        cadena1.plus(cadena2)
                    }else{
                        cadena1
                    }

                }


                creatinina3050.id, entre7580.id, riesgo.id ->{

                    mViewModel.setResultadoD(if(!mas80annos.isChecked && !verapamil.isChecked){
                        if(creatinina3050.isChecked || entre7580.isChecked || riesgo.isChecked) {
                            getString(R.string._150_110_cada_12)
                        }else{
                            getString(R.string._150mg_cada_12_horas)
                        }
                    }else{
                        getString(R.string._110mg_cada_12)
                    })

                    mViewModel.setComentarios(if (verapamil.isChecked){
                        cadena1.plus(cadena2)
                    }else{
                        cadena1
                    })

                }

            }
        }

    }

    override fun onLongClick(v: View?): Boolean {
        if(v?.id == binding.riesgo.id){
            Toast.makeText(applicationContext, R.string.strTag11, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.TOP, 0, 0)
                val toastView = view
                toastView.setPadding(5,5,5,5)
                toastView.setBackgroundColor(ContextCompat.getColor(toastView.context, R.color.colorFondoAcerca))
                show()
            }
        }
        return false
    }
}
