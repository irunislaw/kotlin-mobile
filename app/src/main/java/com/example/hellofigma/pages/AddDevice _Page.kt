package com.example.hellofigma.pages

import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellofigma.R
import jp.wasabeef.gap.Gap








@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDevicePage(onNavigateToListPage: () -> Unit) {
    val openDialog = remember { mutableStateOf(false) }
    var isAutoReport by remember { mutableStateOf(false) }
    var Isadvanced by remember { mutableStateOf(false) }
    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var phonenumber by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var sliderPosition by remember { mutableStateOf(1f) }
    var isFirstChipClicked by remember { mutableStateOf(true) }
    var isSecondChipClicked by remember { mutableStateOf(false) }

    var allIOk by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("All inputs OK"))
    }
    var period by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = "0"))
    }
    var inputNames = arrayOfNulls<String>(8)
    var inputsOkText = arrayOfNulls<String>(8)
    var areInputsArmed = arrayOfNulls<Boolean>(8)
    var areNormalyClosed = arrayOfNulls<Boolean>(8)
    var inputTypes = arrayOfNulls<String>(8)
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            title = {
                Text(text = "Discard changes ?")
            },
            confirmButton = {
                TextButton(
                    onClick = onNavigateToListPage

                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(text = "Add new device")
                },
                navigationIcon = {
                    IconButton(onClick = { openDialog.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            validateData(
                                name.text

                            )
                        }
                    ) {
                        Text(
                            text = "save",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                    }
                }
            )
        },

        content = {


            Column(
                Modifier
                    .padding(0.dp, 60.dp, 0.dp, 0.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = name,
                    label = { Text("name") },
                    onValueChange = {
                        if (it.text.length <= 16) name = it
                    },
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                    isError = false,
                )
                Gap(16.dp)

                OutlinedTextField(
                    value = phonenumber,
                    label = { Text("Phone number") },
                    onValueChange = {
                        if (it.text.length <= 16) phonenumber = it
                    },
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                    isError = false,
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp, 16.dp)
                        .fillMaxHeight()
                ) {
                    Row {
                        Text(text = "Input's number")
                        Spacer(Modifier.weight(1f))
                        Text(text = Math.round(sliderPosition).toString())
                    }
                    Slider(
                        modifier = Modifier.semantics {
                            contentDescription = "Localized Description"
                        },
                        value = sliderPosition,
                        onValueChange = {
                            sliderPosition = it
                            Log.i("slider", Math.round(sliderPosition).toString())
                        },
                        valueRange = 1f..8f,
                        onValueChangeFinished = {
                            // launch some business logic update with the state you hold
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 6
                    )
                    Row(

                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = {
                                Isadvanced = !Isadvanced
                            },
                        ) {
                            Text("advanced", style = MaterialTheme.typography.labelLarge)
                            if (!Isadvanced) {
                                Icon(imageVector = Icons.Filled.ArrowDropDown, "null")
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.drowup),
                                    contentDescription = "",
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                                )
                            }
                        }
                    }
                    if (Isadvanced) {


                        Text(
                            "Text when all inputs are OK",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        OutlinedTextField(
                            value = allIOk,
                            onValueChange = {
                                if (it.text.length <= 40) allIOk = it
                            },
                            modifier = Modifier
                                .padding(0.dp, 0.dp)
                                .fillMaxWidth(),
                            textStyle = MaterialTheme.typography.bodyLarge,
                            singleLine = true,
                            isError = false,
                            supportingText = { }
                        )

                        Gap(height = 15.dp)

                        Text(text = "Report Type", style = MaterialTheme.typography.bodyLarge)
                        Row() {
                            InputChip(
                                selected = isFirstChipClicked,
                                onClick = { isFirstChipClicked = !isFirstChipClicked },
                                label = { Text(text = "sms") },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(R.drawable.sms2),
                                        contentDescription = "null "
                                    )
                                }
                            )
                            Gap(width = 5.dp)
                            InputChip(
                                selected = isSecondChipClicked,
                                onClick = { isSecondChipClicked = !isSecondChipClicked },
                                label = { Text(text = "calling") },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.call),
                                        contentDescription = "null"
                                    )
                                }

                            )

                        }


                        Gap(height = 15.dp)
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(text = "Auto report", style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.weight(1f))
                            Switch(
                                checked = isAutoReport,
                                onCheckedChange = { isAutoReport = !isAutoReport },
                                modifier = Modifier.scale(0.9f)
                            )

                        }
                        if (isAutoReport) {

                            OutlinedTextField(
                                value = period,
                                label = { Text("Period in minutes") },
                                onValueChange = {
                                    Log.i("halo", it.text.toString())
                                    if (it.text.toString() == "") {
                                        period = it

                                    } else if (it.text.toInt() <= 9999) {
                                        period = it
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = MaterialTheme.typography.bodyLarge,
                                singleLine = true,
                                isError = false,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                            )
                        }
                        Text(
                            text = "Inputs Calibration",
                            style = MaterialTheme.typography.titleLarge
                        )
                        for (i in 0 until Math.round(sliderPosition)) {
                            val focusRequester = remember { FocusRequester() }
                            val focusManager = LocalFocusManager.current

                            var inputName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var okTextInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                                mutableStateOf(TextFieldValue("Input OK"))
                            }
                            var isArmed by remember { mutableStateOf(true) }
                            var isNormallyClosed by remember { mutableStateOf(false) }
                            var inputType by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                                mutableStateOf(TextFieldValue("normal"))
                            }
                            var isFocused by remember { mutableStateOf(false) }
                            Gap(height = 20.dp)
                            Text(
                                text = "Input " + (i + 1),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            OutlinedTextField(
                                value = inputName,
                                label = { Text("Input name") },
                                onValueChange = {
                                    inputName = it
                                    inputNames[i] = inputName.text;
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = MaterialTheme.typography.bodyLarge,
                                singleLine = true,
                                isError = false,
                            )

                            OutlinedTextField(
                                label = { Text(text = "Text when input is ok") },
                                value = okTextInput,
                                onValueChange = {
                                    okTextInput = it
                                    inputsOkText[i] = okTextInput.text;
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = MaterialTheme.typography.bodyLarge,
                                singleLine = true,
                                isError = false,
                            )
                            Gap(height = 7.dp)
                            Row() {
                                OutlinedTextField(
                                    label = { Text(text = "Input type") },
                                    value = inputType,
                                    onValueChange = {
                                        inputType = it
                                        inputTypes[i] = inputType.text;
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged { focusState ->
                                            isFocused = focusState.isFocused
                                        }
                                        .focusRequester(focusRequester),
                                    textStyle = MaterialTheme.typography.bodyLarge,
                                    singleLine = true,
                                    isError = false,
                                    readOnly = true,
                                )
                                DropdownMenu(
                                    expanded = isFocused,
                                    onDismissRequest = {
                                        isFocused = false;focusManager.clearFocus();
                                    },
                                    modifier = Modifier.fillMaxWidth(0.92f)
                                ) {
                                    DropdownMenuItem(
                                        text = { Text(text = "Normal") },
                                        onClick = {
                                            focusManager.clearFocus();
                                            isFocused = false;
                                            inputType = TextFieldValue("Normal")
                                        },
                                    )
                                    DropdownMenuItem(
                                        text = { Text(text = "Oiler") },
                                        onClick = {
                                            focusManager.clearFocus();
                                            isFocused = false;
                                            inputType = TextFieldValue("Oiler")
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text(text = "Sludo") },
                                        onClick = {
                                            focusManager.clearFocus();
                                            isFocused = false;
                                            inputType = TextFieldValue("Sludo")
                                        }
                                    )
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Text(
                                    text = "Input armed",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Switch(
                                    checked = isArmed,
                                    onCheckedChange = {
                                        isArmed = !isArmed; areInputsArmed[i] = isArmed
                                    },
                                    modifier = Modifier.scale(0.9f)
                                )

                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Normally closed",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Switch(
                                    checked = isNormallyClosed,
                                    onCheckedChange = {
                                        isNormallyClosed = !isNormallyClosed; areNormalyClosed[i] =
                                        isNormallyClosed
                                    },
                                    modifier = Modifier.scale(0.9f)
                                )
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun AddDevicePreview() {
    AddDevicePage({})
}


fun validateData(
    deviceName: String,


    ) {

}
