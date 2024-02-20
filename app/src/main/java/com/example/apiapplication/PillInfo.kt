package com.example.apiapplication


import com.google.gson.annotations.SerializedName

data class PillInfo(
    @SerializedName("body")
    val body: Body?,
    @SerializedName("header")
    val header: Header?
){
    data class Header(
        @SerializedName("resultCode")
        val resultCode: String?,
        @SerializedName("resultMsg")
        val resultMsg: String?
    )
    data class Body(
        @SerializedName("items")
        val items: List<Item?>?,
        @SerializedName("numOfRows")
        val numOfRows: Int?,
        @SerializedName("pageNo")
        val pageNo: Int?,
        @SerializedName("totalCount")
        val totalCount: Int?
    ){
        data class Item(
            @SerializedName("CLASS_NAME") //병명?
            val cLASSNAME: String?,
            @SerializedName("COLOR_CLASS1")
            val cOLORCLASS1: String?,
            @SerializedName("COLOR_CLASS2")
            val cOLORCLASS2: Any?,
            @SerializedName("DRUG_SHAPE")
            val dRUGSHAPE: String?,
            @SerializedName("ENTP_NAME")
            val eNTPNAME: String?,
            @SerializedName("ETC_OTC_NAME") //전문 or 일반
            val eTCOTCNAME: String?,
            @SerializedName("FORM_CODE_NAME")
            val fORMCODENAME: String?,
            @SerializedName("ITEM_IMAGE")
            val iTEMIMAGE: String?,
            @SerializedName("ITEM_NAME")
            val iTEMNAME: String?,
            @SerializedName("ITEM_SEQ")  //품목 일련번호
            val iTEMSEQ: String?,
            @SerializedName("LINE_BACK")
            val lINEBACK: String?,
            @SerializedName("LINE_FRONT")
            val lINEFRONT: String?,
            @SerializedName("PRINT_BACK")
            val pRINTBACK: String?,
            @SerializedName("PRINT_FRONT")
            val pRINTFRONT: String?,
        )
    }
}