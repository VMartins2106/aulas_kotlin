package etec.com.br.victor.bdsqlite

class User (val id:Int = 0, var username:String = "", var password:String = "") {

    override fun toString(): String {
        return "$id --> $username ($password)"
    }
}