package pedro.com.ioasystestekotlin.model.util

import java.util.regex.Pattern

class UtilsData {
   companion object {
       fun isEmail(email: String): Boolean {
           val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
           val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
           return pattern.matcher(email).find()
       }

       fun isPassword(password: String): Boolean{
           return password.length >= 4
       }
   }
}