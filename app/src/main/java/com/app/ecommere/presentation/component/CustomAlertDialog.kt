package com.app.ecommere.presentation.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.app.ecommere.R
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun CustomAlertDialog(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    isShowNegativeButton: Boolean = false,
    onConfirmClicked: () -> Unit,
    onDismissClicked: () -> Unit
) {
    var isDismissDialog by remember {
        mutableStateOf(false)
    }

    if (!isDismissDialog) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { isDismissDialog = true },
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.blue),
                    fontSize = 16.sp
                )
            },
            text = {
                Text(text = subTitle, color = Color.Gray, fontSize = 14.sp)
            },
            dismissButton = {
                if (isShowNegativeButton) {
                    TextButton(onClick = {
                        onDismissClicked()
                        isDismissDialog = true
                    }) {
                        Text(text = stringResource(id = R.string.cancel), color = Color.Gray)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmClicked()
                    isDismissDialog = true
                }) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = colorResource(id = R.color.blue)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomAlertDialogPreview() {
    ECommerceTheme {
        CustomAlertDialog(title = "", subTitle = "", onConfirmClicked = {}, onDismissClicked = {})
    }
}