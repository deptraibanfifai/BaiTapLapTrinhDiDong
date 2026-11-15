package com.sothuchi.goods

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "goods", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE product (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ProductName TEXT,
                ProductDescription TEXT,
                ProductPrice REAL
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS product")
        onCreate(db)
    }

    // INSERT
    fun insertProduct(product: Product): Long {
        val cv = ContentValues().apply {
            put("ProductName", product.name)
            put("ProductDescription", product.description)
            put("ProductPrice", product.price)
        }
        return writableDatabase.insert("product", null, cv)
    }

    // READ ALL
    fun getAllProducts(): List<Product> {
        val list = mutableListOf<Product>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM product", null)
        while (cursor.moveToNext()) {
            list.add(
                Product(
                    id          = cursor.getInt(0),
                    name        = cursor.getString(1),
                    description = cursor.getString(2),
                    price       = cursor.getDouble(3)
                )
            )
        }
        cursor.close()
        return list
    }

    // UPDATE
    fun updateProduct(product: Product): Int {
        val cv = ContentValues().apply {
            put("ProductName", product.name)
            put("ProductDescription", product.description)
            put("ProductPrice", product.price)
        }
        return writableDatabase.update("product", cv, "id=?", arrayOf(product.id.toString()))
    }

    // DELETE
    fun deleteProduct(id: Int): Int =
        writableDatabase.delete("product", "id=?", arrayOf(id.toString()))
}