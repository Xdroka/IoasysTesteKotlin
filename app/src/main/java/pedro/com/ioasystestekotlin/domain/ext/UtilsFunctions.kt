package pedro.com.ioasystestekotlin.domain.ext

import java.util.regex.Pattern

fun String.validatePassword(): Boolean {
    return this.length >= 4
}

fun String.validateEmail(): Boolean {
    val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
    val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
    return pattern.matcher(this).find()
}
