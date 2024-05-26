package com.ram.buspass.helper.resource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ram.buspass.helper.resource.remote.api.ApiConstants


@Database(entities = [Entities::class], version = 6, exportSchema = false
)abstract class DatabaseHelper : RoomDatabase() {

    // Abstract method
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: DatabaseHelper? = null
        // Return the database helper's instance
        fun getDatabaseInstance(context: Context): DatabaseHelper {
            synchronized(context) {
                return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }
        /**
         * Builds and returns the database instance using the provided application context.
         * @param context The application context.
         * @return The database instance.
         */

        private fun buildDatabase(context: Context): DatabaseHelper {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseHelper::class.java,
                ApiConstants.LOCAL_DATABASE_NAME
            ).fallbackToDestructiveMigration().build() // auto migrate the database
        }

        /**
         * delete the database instance.
         * @param context The application context.
         * @return The boolean response.
         */

        fun clearDatabase(context: Context): Boolean {
            val dbFile = context.getDatabasePath(ApiConstants.LOCAL_DATABASE_NAME)
            return if (dbFile.exists()) {
                dbFile.delete()
            } else {
                false
            }
        }
    }
}