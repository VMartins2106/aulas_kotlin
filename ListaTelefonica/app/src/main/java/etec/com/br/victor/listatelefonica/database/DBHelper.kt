package etec.com.br.victor.listatelefonica.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import etec.com.br.victor.listatelefonica.model.ContactModel
import etec.com.br.victor.listatelefonica.model.UserModel

class DBHelper(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT UNIQUE, userPass TEXT)",
        "INSERT INTO users (userName, userPass) VALUES ('admin', 'password')",
        "CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, email TEXT, phone TEXT, imageId INT)",
        "INSERT INTO contact (name, address, email, phone,imageId) VALUES('Maria', 'Caieiras', 'maria@gmail.com', '11995837482', -1)",
        "INSERT INTO contact (name, address, email, phone,imageId) VALUES('João', 'Franco', 'joao@gmail.com', '11928748273', -1)",
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    /* ------------------------------------------------------------------------------
                                    CRUD USERS
    ------------------------------------------------------------------------------*/

    fun insertUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("userName", username)
        contentValues.put("userPass", password)
        val res = db.insert("users", null, contentValues)
        db.close()
        return res
    }

    fun updateUser(id: Int, username: String, password: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("userName", username)
        contentValues.put("userPass", password)
        val res = db.update("users", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteUser(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val res = db.delete("users", "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun getUser(username: String, password: String): UserModel {
        val db = this.writableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE userName = ? AND userPass = ?",
            arrayOf(username, password)
        )

        var userModel = UserModel()

        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("userName")
            val passIndex = c.getColumnIndex("userPass")

            userModel = UserModel(
                id = c.getInt(idIndex),
                username = c.getString(nameIndex),
                password = c.getString(passIndex)
            )
        }
        db.close()
        return userModel
    }

    fun login(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE userName = ? AND userPass = ?",
            arrayOf(username, password)
        )
        var userModel = UserModel()

        return if (c.count == 1) {
            db.close()
            true
        } else {
            db.close()
            false
        }
    }

    /* ------------------------------------------------------------------------------
                                   CRUD CONTACTS
   ------------------------------------------------------------------------------*/

    fun insertContact(name: String, address: String, email: String, phone: String, imageId: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", imageId)
        val res = db.insert("contact", null, contentValues)
        db.close()
        return res
    }

    fun updateContact(id: Int, name: String, address: String, email: String, phone: String, imageId: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", imageId)
        val res = db.update("contact", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteContact(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val res = db.delete("contact", "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun getContact(id: Int): ContactModel {
        val db = this.writableDatabase
        val c = db.rawQuery(
            "SELECT * FROM contact WHERE id = ?",
            arrayOf(id.toString())
        )

        var contactModel = ContactModel()

        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val addressIndex = c.getColumnIndex("address")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")

            contactModel = ContactModel(
                id = c.getInt(idIndex),
                name = c.getString(nameIndex),
                address = c.getString(addressIndex),
                email = c.getString(emailIndex),
                phone = c.getString(phoneIndex),
                imageId = c.getInt(imageIdIndex),
            )
        }
        db.close()
        return contactModel
    }

    fun getAllContact(): ArrayList<ContactModel> {
        val db = this.writableDatabase
        val c = db.rawQuery("SELECT * FROM contact", null)

        var listContactModel = ArrayList<ContactModel>()

        if (c.count > 0) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val addressIndex = c.getColumnIndex("address")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")
            do{
                val contactModel = ContactModel(
                    id = c.getInt(idIndex),
                    name = c.getString(nameIndex),
                    address = c.getString(addressIndex),
                    email = c.getString(emailIndex),
                    phone = c.getString(phoneIndex),
                    imageId = c.getInt(imageIdIndex),
                )
                listContactModel.add(contactModel)
            }while(c.moveToNext())
        }
        db.close()
        return listContactModel
    }
}