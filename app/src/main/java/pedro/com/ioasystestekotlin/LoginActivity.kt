package pedro.com.ioasystestekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var pSiginButton: Button
    private lateinit var pEmailInput: EditText
    private lateinit var pPasswordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pSiginButton = findViewById(R.id.sigin_button)
        pEmailInput = findViewById(R.id.email_input_text)
        pPasswordInput = findViewById(R.id.password_input_text)
    }

    override fun onStart() {
        super.onStart()

        pSiginButton.setOnClickListener {
            Toast.makeText(this, "Olá", Toast.LENGTH_LONG)
        }


    }
}
