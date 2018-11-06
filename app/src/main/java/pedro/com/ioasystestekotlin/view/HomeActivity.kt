package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityHomeBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.StringObservable
import pedro.com.ioasystestekotlin.util.toast
import pedro.com.ioasystestekotlin.viewmodel.HomeViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

class HomeActivity : AppCompatActivity(), OnItemAdapterClickListener {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.vm = viewModel
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
                viewModel.searchListener()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchField.value = StringObservable(newText ?: "")
                msgTextViewId.visibility = View.GONE
                return true
            }

        })
        return result
    }

    private fun creatingObservers() {
        viewModel.getState().observe(this, Observer { viewState ->

            loadingProgressBarId.visibility = View.GONE
            when (viewState?.state) {
                State.SUCCESS -> {
                    viewModel.getState().value?.data?.let { list ->
                        list[0].enterprise_name?.let { toast(it) }
                        setupRecycler(list)
                    }
                }

                State.LOADING -> {
                    binding.loadingProgressBarId.visibility = View.VISIBLE
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

    private fun retrievingHeaders() = binding.vm?.setHeader(
            intent?.extras?.get("token").toString(),
            intent?.extras?.get("uid").toString(),
            intent?.extras?.get("client").toString()
    )

    private fun setupRecycler(enterpriseList: List<Enterprise>) {
        enterpriseRecyclerViewId.layoutManager = LinearLayoutManager(this).also {
        }
        enterpriseRecyclerViewId.adapter = EnterprisesAdapter(enterpriseList, this)
    }

    override fun onItemClick(enterprise: Enterprise) {
        val intent = Intent(this, AboutActivity::class.java)
        intent.putExtra("description", enterprise.description)
        intent.putExtra("photo", enterprise.photo)
        intent.putExtra("enterpriseName", enterprise.enterprise_name)
        startActivity(intent)
    }

}

