package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityAboutBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.util.ImageUtil
import pedro.com.ioasystestekotlin.viewmodel.AboutViewModel

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
        val enterprise = Enterprise(
                enterprise_name = intent.extras.getString("enterpriseName"),
                description = intent.extras.getString("description"),
                photo = intent.extras.getString("photo")
        )
        binding.vm = AboutViewModel(enterprise)
        creatingObserver(binding)
        binding.executePendingBindings()
        setSupportActionBar(binding.toolbarAboutId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.about_menu, menu)
        title = binding.vm?.enterprise?.value?.enterprise_name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        return true
    }

    private fun creatingObserver(binding: ActivityAboutBinding) {
        binding.vm?.reload?.observe(this, Observer { reload ->
            when (reload) {
                true -> {
                    ImageUtil.downloadPhoto(
                            this,
                            binding.imageEnterpriseId,
                            binding.vm?.enterprise?.value?.photo!!
                    )
                    binding.vm!!.reload.postValue(false)
                }

                false -> {

                }
            }
        })
    }
}
