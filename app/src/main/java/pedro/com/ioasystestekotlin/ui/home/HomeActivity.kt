package pedro.com.ioasystestekotlin.ui.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityHomeBinding
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise
import pedro.com.ioasystestekotlin.model.dataclass.StringObservable
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.home.HomeViewModel
import pedro.com.ioasystestekotlin.ui.about.AboutActivity
import pedro.com.ioasystestekotlin.ui.enterpriseslist.EnterprisesAdapter
import pedro.com.ioasystestekotlin.util.*

class HomeActivity : AppCompatActivity(), OnItemAdapterClickListener {

    private val mViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_home)
        binding.vm = mViewModel
        setSupportActionBar(binding.toolbarSearchId)

        creatingObservers()
        binding.executePendingBindings()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val result = super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        title = ""
        val searchView = menu?.findItem(R.id.searchViewId)?.actionView as SearchView

        searchView.dataBinding(
                onSubmit = {
                    mViewModel.searchListener()
                },
                onTextChanged = { newText ->
                    mViewModel.searchField.value = StringObservable(newText)
                    msgTextView.hide()
                }
        )

        return result
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(this, Observer { viewState ->
            loadingProgressBar.hide()

            Log.d("FEEDS",
                    "${viewState?.state} -  ${viewState?.data} " +
                            "- ${viewState?.throwable?.message}"
            )

            when (viewState?.state) {
                State.SUCCESS -> {
                    msgTextView.hide()
                    mViewModel.getState().value?.data?.let { list ->
                        setupRecycler(list)
                    }
                }

                State.LOADING -> {
                    loadingProgressBar.show()
                }

                State.FAILURE -> {
                    viewState.throwable?.message?.let {
                        toast(it)
                    }
                }

                else -> {
//                    do nothing
                }
            }
        })
    }

    private fun setupRecycler(enterpriseList: List<Enterprise>) {
        enterpriseRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = EnterprisesAdapter(enterpriseList, this@HomeActivity)
        }
    }

    override fun onItemClick(enterprise: Enterprise) {
        startActivity<AboutActivity>(
                mapOf(
                        Pair(first = "description", second = enterprise.description ?: ""),
                        Pair(first = "photo", second = enterprise.photo ?: ""),
                        Pair(first = "enterpriseName", second = enterprise.enterprise_name ?: "")
                )
        )
    }

}