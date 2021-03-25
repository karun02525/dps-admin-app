package org.dps.admin.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dps.admin.App
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.model.TeacherData
import org.dps.admin.network.ApiStatus
import org.dps.admin.network.RestClient
import org.json.JSONObject

class ClassViewModel(private val restClient: RestClient) : ViewModel() {

     val msg = MutableLiveData<String>()
     val classData = MutableLiveData<List<DataClasses>>()
     val teacherList = MutableLiveData<List<TeacherData>>()

    init {
        getClasses()
        getTeacher()
    }

    fun createClass(classname: String, section: MutableList<String>) {
        val params: HashMap<String, Any> = HashMap()
        params["classname"] = classname
        params["section"] = section
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().createClassesAsync(params).await().let {
                    if (it.isSuccessful)
                        msg.value = JSONObject(it.body().toString()).optString("message")
                     else
                        msg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                msg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    private fun getClasses() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getClassesAsync().await().let {
                    if (it.isSuccessful)
                        classData.value=it.body()!!.data!!
                    else
                        msg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                msg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    private fun getTeacher() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getTeacherAsync().await().let {
                    if (it.isSuccessful)
                        teacherList.value=it.body()!!.data!!
                    else
                        msg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                msg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    fun assignTeacherAsync(class_id: String, section: String,teacher_id: String) {
        val params: HashMap<String, Any> = HashMap()
        params["class_id"] = class_id
        params["section"] = section
        params["teacher_id"] = teacher_id
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().assignTeacherAsync(params).await().let {
                    if (it.isSuccessful)
                        msg.value = JSONObject(it.body().toString()).optString("message")
                    else
                        msg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                msg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    fun createStudentAsync(class_id: String, section: String,teacher_id: String) {
        val params: HashMap<String, Any> = HashMap()
        params["class_id"] = class_id
        params["section"] = section
        params["teacher_id"] = teacher_id
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().assignTeacherAsync(params).await().let {
                    if (it.isSuccessful)
                        msg.value = JSONObject(it.body().toString()).optString("message")
                    else
                        msg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                msg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

}