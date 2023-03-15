package etec.com.br.victor.crud_objetos_listview

class Usuario(var userName: String, var password: String){
    override fun toString(): String {
        return "$userName ($password)"
    }
}