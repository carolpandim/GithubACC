package br.com.carolpandim.githubacc.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import br.com.carolpandim.githubacc.data.UserRepository
import br.com.carolpandim.githubacc.data.local.MyDataBase
import br.com.carolpandim.githubacc.data.local.dao.UserDAO
import br.com.carolpandim.githubacc.data.remote.UserWebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MyDataBase {
        return Room.databaseBuilder(
                application,
                MyDataBase::class.java, "MyDatabase.db"
        )
                .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MyDataBase): UserDAO {
        return database.userDAO()
    }

    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
            webservice: UserWebService,
            userDao: UserDAO,
            executor: Executor
    ): UserRepository {
        return UserRepository(webservice, userDao, executor)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiWebservice(restAdapter: Retrofit): UserWebService{
        return restAdapter.create(UserWebService::class.java)
    }

    companion object {
        private val BASE_URL = "https://api.github.com/"
    }
}