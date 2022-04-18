package com.hopeitservice.cartcheckout.cart.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CartModel::class], version = 1)
abstract class DatabaseHelper: RoomDatabase() {
    abstract fun cartDao() : CartDao

//    private class DatabaseHelperCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback(){
//
//    }

    companion object {
        private var INSTANCE: DatabaseHelper? = null



//        @Synchronized
//        fun getInstance(ctx: Context): DatabaseHelper{
//            if (instance==null){
//                instance = Room.databaseBuilder(
//                    ctx.applicationContext,
//                    DatabaseHelper::class.java,
//                    "cartDB"
//                ).fallbackToDestructiveMigration()
//                  .build()
//            }
//            return instance!!
//        }

        fun getDatabase(
            context: Context,
        ): DatabaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "cart_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}