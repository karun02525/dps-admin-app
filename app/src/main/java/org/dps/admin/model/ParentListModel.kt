package org.dps.admin.model

import com.google.gson.annotations.SerializedName


data class ParentListModel(
        @SerializedName("data")
        var `data`: List<ParentData>?,
        @SerializedName("message")
        var message: String?,
        @SerializedName("status")
        var status: String?
)

data class ParentData(
        @SerializedName("date")
        var date: String?,
        @SerializedName("dob")
        var dob: String?,
        @SerializedName("fname")
        var fname: String?,
        @SerializedName("gender")
        var gender: String?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("lname")
        var lname: String?,
        @SerializedName("parent_id")
        var parentId: String?,
        @SerializedName("surname")
        var surname: String?
) {
    override fun toString(): String {
        return fname.toString() + " " + lname.toString() + "      Parent Id: " + parentId.toString()
    }
}
