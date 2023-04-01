package etec.com.br.victor.loginmvvm

class PersonRepository {

    fun login(email: String, password: String): Boolean{
        return (email=="admin" && password=="pass")
    }

}