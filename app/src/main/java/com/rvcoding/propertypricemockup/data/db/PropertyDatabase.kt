package com.rvcoding.propertypricemockup.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rvcoding.propertypricemockup.domain.Property

@Database(
    entities = [Property::class],
    version = 1,
    exportSchema = false
)
internal abstract class PropertyDatabase : RoomDatabase() {
    abstract val dao: PropertyDao

    companion object {
        private const val DATABASE_NAME = "property_db"

        fun createDb(context: Context): PropertyDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = PropertyDatabase::class.java,
                name = DATABASE_NAME
            ).build()
        }

        fun createDbForTesting(context: Context): PropertyDatabase {
            return Room.inMemoryDatabaseBuilder(
                context = context,
                klass = PropertyDatabase::class.java,
            ).build()
        }
    }
}