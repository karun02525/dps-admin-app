package org.dps.admin.network

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import org.dps.admin.model.*
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/compassLocation/rest/address/autocomplete?queryString=airtel")
    fun setSuggestionsAsync(@Query("city") cityName: String): Deferred<Response<Suggestion>>

    @GET("/api/admin/students")
    fun getStudentDataAsync(@Query("class_id") class_id: String): Deferred<Response<StudentModel>>

    @GET("/api/teacher")
    fun getTeacherAsync(): Deferred<Response<TeacherModel>>

    @GET("/api/admin/classes")
    fun getClassesAsync(): Deferred<Response<Classes>>

    @GET("/api/admin/parents")
    fun getParentAsync(): Deferred<Response<ParentListModel>>

    @POST("/api/admin/classes")
    fun createClassesAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/admin/assign-teacher")
    fun assignTeacherAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/admin/assign-rollno")
    fun assignRollNoAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/student/auth/register")
    fun createStudentAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/student/auth/parent-reg")
    fun createParentAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/teacher/auth/register")
    fun createTeacherAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

}