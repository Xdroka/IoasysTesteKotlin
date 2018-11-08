package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.content.Intent
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
import pedro.com.ioasystestekotlin.util.hide
import pedro.com.ioasystestekotlin.util.show
import pedro.com.ioasystestekotlin.util.toast
import pedro.com.ioasystestekotlin.viewmodel.HomeViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

class HomeActivity : AppCompatActivity(), OnItemAdapterClickListener {

    private val mViewModel by viewModel<HomeViewModel>()

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
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                mViewModel.searchListener()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mViewModel.searchField.value = StringObservable(newText ?: "")
                msgTextView.hide()
                return true
            }

        })
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
        enterpriseRecyclerView.layoutManager = LinearLayoutManager(this).also {

        }
        enterpriseRecyclerView.adapter = EnterprisesAdapter(enterpriseList, this)
    }

    override fun onItemClick(enterprise: Enterprise) {
        val intent = Intent(this, AboutActivity::class.java)
        intent.putExtra("description", enterprise.description)
        intent.putExtra("photo", enterprise.photo)
        intent.putExtra("enterpriseName", enterprise.enterprise_name)
        startActivity(intent)
    }

}

