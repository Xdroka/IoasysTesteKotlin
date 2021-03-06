package pedro.com.ioasystestekotlin.ui.about

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_about.*
import org.koin.android.viewmodel.ext.android.viewModel
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityAboutBinding
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.about.AboutViewModel
import pedro.com.ioasystestekotlin.ui.ext.downloadPhoto
import pedro.com.ioasystestekotlin.ui.ext.toast

class AboutActivity : AppCompatActivity() {

    private val mViewModel: AboutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding: ActivityAboutBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_about)
        binding.vm = mViewModel

        setSupportActionBar(toolbarAboutId)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        creatingObserver()
        binding.executePendingBindings()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.about_menu, menu)
        title = mViewModel.getEnterprise().value?.name
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when {
        item?.itemId == android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }

    private fun creatingObserver() {

        mViewModel.getState().observe(this, Observer { viewState ->
            when (viewState?.state) {
                State.GETTING_DATA -> {
                    viewState.data?.let { photoUrl ->
                        imageEnterprise.downloadPhoto(
                                photoUrl = photoUrl,
                                enabledReDownload = true
                        )
                    }
                }

                State.FAILURE -> {
                    imageEnterprise.setImageResource(R.drawable.imageReport)
                }

                State.LOADING -> {
                    toast(viewState.data.toString())
                }

                State.WAITING_DATA -> {
                    mViewModel.loadImage()
                }

                else -> {
                }
            }
        })

    }
}
