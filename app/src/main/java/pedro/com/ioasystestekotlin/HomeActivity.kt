package pedro.com.ioasystestekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbarSearch = findViewById<Toolbar>(R.id.toolbar_search)
        setSupportActionBar(toolbarSearch)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        var searchView:SearchView = menu?.findItem(R.id.item_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                sendMessageToScreen("teste")
                return false
            }
        })
        return true
    }

    fun sendMessageToScreen(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

