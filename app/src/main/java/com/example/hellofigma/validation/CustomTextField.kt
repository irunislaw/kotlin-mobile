package com.example.hellofigma.validation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier,
    inputWrapper: InputWrapper,
    readOnly:Boolean = remember {
        false
    },




    keyboardOptions: KeyboardOptions = remember {
        KeyboardOptions.Default
    },
    visualTransformation: VisualTransformation = remember {
        VisualTransformation.None
    },
    onValueChange: (x:String) -> Unit,
    fieldValuex: MutableState<TextFieldValue>? = null
){
    val fieldValue: MutableState<TextFieldValue>;
    if(fieldValuex==null){
         fieldValue = remember {
            mutableStateOf(TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length)))
        }
    }else{
        fieldValue = remember {
            fieldValuex
        }
    }

    Column {
        OutlinedTextField(
            value = fieldValue.value,

            onValueChange = {
                fieldValue.value = it
               onValueChange(it.text);
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = inputWrapper.errorId != null,
//            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            modifier = modifier,
            readOnly = readOnly
            )
        if (inputWrapper.errorId != null) {
            Text(
                text = stringResource(inputWrapper.errorId!!),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelLarge,
                modifier = androidx.compose.ui.Modifier.padding(start = 16.dp)
            )
        }
    }






}


