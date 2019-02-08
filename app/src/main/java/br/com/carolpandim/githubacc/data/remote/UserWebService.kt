package br.com.carolpandim.githubacc.data.remote

import br.com.carolpandim.githubacc.data.local.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebService {
    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>
}