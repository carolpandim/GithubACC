package br.com.carolpandim.githubacc.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.carolpandim.githubacc.data.local.converter.DateConverter
import br.com.carolpandim.githubacc.data.local.dao.UserDAO
import br.com.carolpandim.githubacc.data.local.entity.User

@Database(entities = [User::class],version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDataBase:RoomDatabase(){

    abstract fun userDAO(): UserDAO

    companion object {
        val INSTANCE: MyDataBase? = null
    }
}