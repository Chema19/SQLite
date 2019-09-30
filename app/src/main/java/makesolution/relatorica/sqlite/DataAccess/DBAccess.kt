package makesolution.relatorica.sqlite.DataAccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import makesolution.relatorica.sqlite.Model.EntregaModel

class DBAccess(context:Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "SQLite.db"

        //TABLE
        private val TABLE_NAME = "Entrega"
        private val COL_ID = "id"
        private val COL_SOURCE = "source"
        private val COL_ADDRESS = "address"
        private val COL_RATING = "rating"
        private val COL_CREATEDON = "createdon"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME" +
                "($COL_ID INTEGER PRIMARY KEY,$COL_SOURCE TEXT,$COL_ADDRESS TEXT,$COL_RATING TEXT,$COL_CREATEDON TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }


    var allEntrega: ArrayList<EntregaModel>
        get() {
            val lstEntrega = ArrayList<EntregaModel>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val entregaModel = EntregaModel()

                    entregaModel.Id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    entregaModel.Source = cursor.getString(cursor.getColumnIndex(COL_SOURCE))
                    entregaModel.Address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS))
                    entregaModel.Rating = cursor.getString(cursor.getColumnIndex(COL_RATING))
                    entregaModel.Createdon = cursor.getString(cursor.getColumnIndex(COL_CREATEDON))

                    lstEntrega.add(entregaModel)
                } while (cursor.moveToNext())
            }
            return lstEntrega
        }
        set(value) {}

    //CRUD
    fun addEntrega(entrega: EntregaModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, entrega.Id)
        values.put(COL_SOURCE, entrega.Source)
        values.put(COL_ADDRESS, entrega.Address)
        values.put(COL_RATING, 0)
        values.put(COL_CREATEDON, entrega.Createdon)

        db.insert(TABLE_NAME, null,values)
        db.close()
    }

    fun updateEntrega(entrega: EntregaModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, entrega.Id)
        values.put(COL_SOURCE, entrega.Source)
        values.put(COL_ADDRESS, entrega.Address)
        values.put(COL_RATING, entrega.Rating)
        values.put(COL_CREATEDON, entrega.Createdon)

        db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(entrega.Id.toString()))
        db.close()
    }

    fun deleteEntrega(entrega: EntregaModel){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(entrega.Id.toString()))
        db.close()
    }

}