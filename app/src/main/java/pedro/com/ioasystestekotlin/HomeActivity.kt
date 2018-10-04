package pedro.com.ioasystestekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar_search)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val result = super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

//        var searchview: View? = menu?.findItem(R.id.item_search)?.actionView

        return result
    }
}

