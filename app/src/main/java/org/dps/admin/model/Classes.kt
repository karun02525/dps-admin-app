package org.dps.admin.model

import com.google.gson.annotations.SerializedName


data class Classes(
    @SerializedName("data")
    var `data`: List<DataClasses>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DataClasses(
    @SerializedName("classname")
    var classname: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("section")
    var section: List<String>
) {
    override fun toString(): String {
        return classname.toString()
    }
}

