package org.dps.admin.network

import com.google.gson.JsonObject
import org.dps.admin.model.Suggestion
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import org.dps.admin.model.Classes
import org.dps.admin.model.StudentModel
import org.dps.admin.model.TeacherModel
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/compassLocation/rest/address/autocomplete?queryString=airtel")
    fun setSuggestionsAsync(@Query("city") cityName: String): Deferred<Response<Suggestion>>

    @GET("/api/get-student")
    fun getStudentDataAsync(@Query("class_id") class_id: String): Deferred<Response<StudentModel>>

    @GET("/api/teacher")
    fun getTeacherAsync(): Deferred<Response<TeacherModel>>

    @GET("/api/classes")
    fun getClassesAsync(): Deferred<Response<Classes>>

    @POST("/api/classes")
    fun createClassesAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/assign-teacher")
    fun assignTeacherAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

    @POST("/api/student/auth/register")
    fun createStudentAsync(@Body param:HashMap<String,Any>): Deferred<Response<JsonObject>>

}