package etec.com.br.victor.bdsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.Contacts.SettingsColumns.KEY

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "database.db", null, 1){

    val sql = arrayOf(
        "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(50), userPass VARCHAR(15))",
        "INSERT INTO usuario (userName, userPass) VALUES ('user', 'pass')",
        "INSERT INTO usuario (userName, userPass) VALUES ('admin', '123')",
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE usuario")
        onCreate(db)
    }

    fun usuarioInsert(username: String, password: String) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("userName", username)
        contentValues.put("userPass", password)
        val res = db.insert("usuario", null, contentValues)
        db.close()

        return res
    }
    fun usuarioUpdate(id: Int, username: String, password: String) : Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("userName", username)
        contentValues.put("userPass", password)
        val res = db.update("usuario", contentValues, "id = ?", arrayOf(id.toString()))
        db.close()

        return res
    }
    fun usuarioDelete(id: Int) : Int{
        val db = this.writableDatabase
        val res = db.delete("usuario", "id=?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun usuarioSelectAll(): Cursor{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM usuario", null)
        db.close()
        return c
    }

    fun usuarioSelectById(id: Int): Cursor{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM usuario WHERE id=?", arrayOf(id.toString()))
        db.close()
        return c
    }

    fun usuarioObjectSelectById(id: Int): User{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM usuario WHERE id=?", arrayOf(id.toString()))
        var usuario = User()
        if(c.count == 1){
            c.moveToFirst()

            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("userName")
            val passwordIndex = c.getColumnIndex("userPass")

            val id = c.getInt(idIndex)
            val userName = c.getString(nameIndex)
            val password = c.getString(passwordIndex)

            usuario = User(id,userName,password)
        }
        db.close()
        return usuario
    }

    fun usuarioListSelectAll() : ArrayList<User>{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM usuario", null)
        val listaUsuario: ArrayList<User> = ArrayList()

        if(c.count>0){
            c.moveToFirst()
            do{
                val idIndex = c.getColumnIndex("id")
                val nameIndex = c.getColumnIndex("userName")
                val passwordIndex = c.getColumnIndex("userPass")

                val id = c.getInt(idIndex)
                val userName = c.getString(nameIndex)
                val password = c.getString(passwordIndex)

                listaUsuario.add(User(id,userName,password))
            }while(c.moveToNext())
        }
        db.close()
        return listaUsuario
    }

}