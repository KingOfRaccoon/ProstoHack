package elements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CustomAlertDialog(error: String) {
    val shouldDismiss = remember {
        mutableStateOf(false)
    }

    if (shouldDismiss.value) return

    AlertDialog(
        { shouldDismiss.value = true },
        {},
        dismissButton = { Button({ shouldDismiss.value = true }, Modifier) { Text("Закрыть") } },
        title = { Text("ОшИбКа!") },
        text = { Text(error) }
    )
}