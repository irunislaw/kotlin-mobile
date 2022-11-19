package com.example.hellofigma.classes

import java.sql.RowId

data class Device(
    val id: RowId,
    var name: String,
    var phoneNumber: String,
    var Inputs: Array<Input>,
    var AllOkText: String,
    var ReportType: Number,
    var AutoReport:Boolean,
    var ReportTime: Number

)
