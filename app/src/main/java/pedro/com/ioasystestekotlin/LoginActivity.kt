package pedro.com.ioasystestekotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import java.util.regex.Pattern

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
            if (isEmail(pEmailInput.text.toString())) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                pEmailInput.error = "Digite um email válido"
            }
        }


    }

    private fun isEmail(email: String): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }

}