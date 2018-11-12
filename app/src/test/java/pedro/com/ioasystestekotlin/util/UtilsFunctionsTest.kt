package pedro.com.ioasystestekotlin.util

import org.junit.Assert.assertFalse
import org.junit.Test
import pedro.com.ioasystestekotlin.domain.ext.validateEmail
import pedro.com.ioasystestekotlin.domain.ext.validatePassword

class UtilsFunctionsTest {
// Email
    //teste para email valido
    @Test
    fun validEmail() {
        val email = "teste@ioasys.com.br"
        assert(email.validateEmail())
    }

    //teste para email sem '.com'
    @Test
    fun emailWithoutDotCom() {
        val email = "teste@ioasys.br"
        assert(email.validateEmail())
    }

    //teste para email sem '@'
    @Test
    fun invalidEmail() {
        val email = "testeioasys.com.br"
        assertFalse(email.validateEmail())
    }

    //teste para email sem '@'
    @Test
    fun invalidEmailWithoutDomain() {
        val email = "teste"
        assertFalse(email.validateEmail())
    }

// Senha
    //senha válida
    @Test
    fun passswordValid(){
        val test = "senha"
        assert(test.validatePassword())
    }

    //senha inválida (menor que 4 caracteres
    @Test
    fun passswordInvalid(){
        val test = "123"
        assertFalse(test.validatePassword())
    }

}
