package com.example.hellofigma.pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellofigma.R
import jp.wasabeef.gap.Gap



val tab = arrayOf(0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5);







@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPage(
    onNavigateToDevicePage: () -> Unit,
    onNavigateToAddDevicePage: () -> Unit,
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.height(100.dp),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                        )
                    },
                modifier = Modifier.height(80.dp)
                )
        },
        floatingActionButton = {
            FloatingActionButton(
                        onClick = onNavigateToAddDevicePage,
                        modifier = Modifier.onGloballyPositioned {
                            coordinates->
                            coordinates.positionInWindow()
                        }
                        ) {
                        Icon(
                            Icons.Filled.Add,
                            "Dodaj element"
                        )
                    }
        }

    ) {
        Column(
            modifier = Modifier
                .padding(26.dp, 80.dp,26.dp,0.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {

            for (i in tab) {
                Card(
                    onClick = onNavigateToDevicePage,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(16.dp)
                        )
                        .size(width = 800.dp, height = 56.dp)


                ) {
                    Row(

                        modifier = Modifier.padding(16.dp, 8.dp, 24.dp, 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.router),
                            contentDescription = "ikonka",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                        Gap(width = 16.dp)
                        Column() {
                            Text(
                                text = "Name",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "+48 123 456 789",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            Modifier.fillMaxHeight()
                        ) {
                            var expanded by remember { mutableStateOf(false)}

                            IconButton(
                                onClick = { expanded = true },
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(alignment = Alignment.CenterVertically)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.more),
                                    contentDescription = "more icon"

                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text(text = "Edit") },
                                    onClick = { /*TODO*/ },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Edit,
                                            contentDescription = null
                                        )
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "Delete") },
                                    onClick = { /*TODO*/ },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Delete,
                                            contentDescription = null
                                        )
                                    }
                                )


                            }
                        }


                    }

                }
                Gap(20.dp)
            }
        }


    }
}











@Preview
@Composable
fun PreviewListpage() {
    ListPage(onNavigateToAddDevicePage = {},
    onNavigateToDevicePage = {},
    )
}


