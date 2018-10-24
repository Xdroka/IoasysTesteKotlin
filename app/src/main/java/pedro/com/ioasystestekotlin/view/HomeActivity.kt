package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityHomeBinding
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.StringLiveData
import pedro.com.ioasystestekotlin.viewmodel.HomeViewModel


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.vm = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setSupportActionBar(binding.toolbarSearchId)

        binding.vm?.setHeader(
                intent?.extras?.get("token") as String,
                intent?.extras?.get("uid") as String,
                intent?.extras?.get("client") as String
        )
        binding.vm?.observables?.messageToast?.observe(this, Observer<StringLiveData> { t ->
            val message = t?._text ?: ""
            if (message != "") {
                toast(message)
            }
        })

        binding.vm?.observables?.changeActivity?.observe(this, Observer<EnabledChange> { t ->
            if (t?.enableChange == true) {
//                val intent = Intent(this@HomeActivity,)
                //mandar photo, nameEnterprise, description
            }
        })
        binding.executePendingBindings()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val result = super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        title = ""
        val searchView = menu?.findItem(R.id.item_search)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.search_hint)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                binding.vm?.searchListener()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.vm?.searchField?.value = StringLiveData(newText ?: "")
                return true
            }

        })
        return result
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

