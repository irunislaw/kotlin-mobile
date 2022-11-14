package com.example.hellofigma.classes

import java.sql.RowId

data class Input(
    val id: RowId,
    var name: String,
    var isArmed: Boolean=true,
    var Type:InputTypes=InputTypes.NORMAL,
    var SmsText:String,
    var NormalyClosed:Boolean=false
)
