package com.example.hellofigma.classes

import java.sql.RowId

data class Input(
    val id: RowId,
    var name: String,
    var SmsText:String,
    var isArmed: Boolean=true,
    var NormalyClosed:Boolean=false,
    var Type:InputTypes=InputTypes.NORMAL,
)
