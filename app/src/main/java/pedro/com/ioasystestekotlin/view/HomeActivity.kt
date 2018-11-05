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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityHomeBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.StringObservable
import pedro.com.ioasystestekotlin.viewmodel.HomeViewModel
import pedro.com.ioasystestekotlin.viewmodel.Status

class HomeActivity : AppCompatActivity(), OnItemAdapterClickListener<Enterprise> {


    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.vm = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setSupportActionBar(binding.toolbarSearchId)

        retrievingHeaders()
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
                binding.vm?.searchListener()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.vm?.searchField?.value = StringObservable(newText ?: "")
                msgTextViewId.visibility = View.GONE
                return true
            }

        })
        return result
    }

    private fun creatingObservers() {
        binding.vm?.state?.observe(this, Observer { viewState ->

            binding.loadingProgressBarId.visibility = View.GONE
            when (viewState?.status) {
                Status.SUCCESS -> {
                    setupRecycler(binding.vm?.enterpriseList?.value!!)
                }

                Status.LOADING -> {
                    binding.loadingProgressBarId.visibility = View.VISIBLE
                }

                Status.FAILURE -> {
                    toast(viewState.data.toString())
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
        enterpriseRecyclerViewId.layoutManager = LinearLayoutManager(this)
        enterpriseRecyclerViewId.adapter = EnterprisesAdapter(enterpriseList, this)
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(t: Enterprise) {
        val intent = Intent(this, AboutActivity::class.java)
        intent.putExtra("description", t.description)
        intent.putExtra("photo", t.photo)
        intent.putExtra("enterpriseName", t.enterprise_name)
        startActivity(intent)
    }

}

