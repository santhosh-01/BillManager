package com.example.billmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.billmanager.entity.Quantity

@Database(
    entities = [Quantity::class],
    version = 1
)
@TypeConverters(DateTimeTypeConverter::class)
abstract class BillManagerDatabase : RoomDatabase() {

    abstract fun getQuantityDao() : QuantityDao

    companion object {

        @Volatile
        private var INSTANCE: BillManagerDatabase? = null

        fun getInstance(context: Context): BillManagerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context,
                            BillManagerDatabase::class.java,
                            "article_db").build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}