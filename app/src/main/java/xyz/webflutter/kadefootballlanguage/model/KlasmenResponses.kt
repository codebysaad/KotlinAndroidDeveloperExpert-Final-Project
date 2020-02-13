package xyz.webflutter.kadefootballlanguage.model

import com.google.gson.annotations.SerializedName

data class KlasmenResponses(
    @SerializedName("table")
    var getKlasmen: List<ModelKlasmen>,
    @SerializedName("seasons")
    var getSeason: List<String>
)