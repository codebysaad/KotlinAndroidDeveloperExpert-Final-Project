package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelKlasmen(
    @SerializedName("name")
    var name: String,
    @SerializedName("teamId")
    var teamid: String,
    @SerializedName("played")
    var played: String,
    @SerializedName("goalsfor")
    var goalsfor: String,
    @SerializedName("goalsagainst")
    var goalsagainst: String,
    @SerializedName("goalsdifference")
    var goalsdifference: String,
    @SerializedName("win")
    var win: String,
    @SerializedName("draw")
    var draw: String,
    @SerializedName("loss")
    var loss: String,
    @SerializedName("total")
    var total: String
) : Parcelable