package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityAboutBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.util.downloadPhoto
import pedro.com.ioasystestekotlin.viewmodel.AboutViewModel
import pedro.com.ioasystestekotlin.viewmodel.Status

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
//        binding.vm = AboutViewModel(getBundleEnterprise())
        binding.vm = ViewModelProviders.of(this).get(AboutViewModel::class.java).also { vm ->
            vm.setEnterprise(getBundleEnterprise())
        }

        setSupportActionBar(toolbarAboutId)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        creatingObserver(binding)
        binding.executePendingBindings()

    }

    private fun getBundleEnterprise(): Enterprise {
        return Enterprise(
                enterprise_name = intent.extras?.getString("enterpriseName"),
                description = intent.extras?.getString("description"),
                photo = intent.extras?.getString("photo")
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.about_menu, menu)
        title = binding.vm?.enterprise?.value?.enterprise_name

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when {
        item?.itemId == android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }


    private fun creatingObserver(binding: ActivityAboutBinding) {

        binding.vm?.viewState?.observe(this, Observer { viewState ->
            when (viewState?.status) {
                Status.GETTING_DATA -> {
                    binding.imageEnterpriseId.downloadPhoto(
                            this,
                            viewState.data!!,
                            aboutScreen = true
                    )
                }

                Status.FAILURE -> {
                    binding.imageEnterpriseId.setImageResource(R.drawable.imageReport)
                }

                Status.LOADING -> {
                    toast(viewState.data.toString())
                }

                else -> {
                    binding.vm?.loadImage()
                }
            }
        })

    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
