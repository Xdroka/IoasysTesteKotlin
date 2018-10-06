package pedro.com.ioasystestekotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import pedro.com.ioasystestekotlin.R

class LoginActivity : AppCompatActivity() {
    private lateinit var pSiginButton: Button
    private lateinit var pEmailInput: EditText
    private lateinit var pPasswordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

//        val binding:ActivityLoginBinding = setContentView(this, R.layout.activity_login)
//        val binding2:LoginActivityBinding = setContentView(this, R.layout.activity_login)
        pSiginButton = findViewById(R.id.sigin_button)
        pEmailInput = findViewById(R.id.email_input_text)
        pPasswordInput = findViewById(R.id.password_input_text)
    }

  /*   Função para verificar se o email é válido
    private fun isEmail(email: String): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }
*/
}