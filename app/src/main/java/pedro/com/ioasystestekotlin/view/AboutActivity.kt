package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_about.*
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityAboutBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.util.ImageUtil
import pedro.com.ioasystestekotlin.viewmodel.AboutViewModel
import pedro.com.ioasystestekotlin.viewmodel.State
import pedro.com.ioasystestekotlin.viewmodel.ViewState

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
        binding.vm = AboutViewModel(getBundleEnterprise())
        creatingObserver(binding)

        binding.executePendingBindings()

        setSupportActionBar(binding.toolbarAboutId)
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        return true
    }

    private fun creatingObserver(binding: ActivityAboutBinding) {

        binding.vm?.viewState?.observe(this, Observer { viewState ->
            when (viewState?.state) {
                State.GETTING_DATA -> {
                    ImageUtil.downloadPhoto(
                            this,
                            binding.imageEnterpriseId,
                            viewState.data!!
                    )
                    binding.vm?.viewState?.postValue(ViewState(null, State.WAITING_DATA))
                }

                State.FAILURE -> {
                    binding.imageEnterpriseId.setImageResource(R.drawable.imageReport)
                }

                else -> {
                    binding.vm?.loadImage()
                }
            }

        })

        toolbarAboutId.setNavigationOnClickListener {
            finish()
        }
    }
}
