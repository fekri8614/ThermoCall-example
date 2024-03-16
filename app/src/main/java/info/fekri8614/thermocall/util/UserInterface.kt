package info.fekri8614.thermocall.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.ui.theme.Shapes

@Composable
fun MyEditText(
    edtValue: String,
    icon: ImageVector,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onValueChanges: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = Shapes.medium,
        leadingIcon = { Icon(icon, null) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}

// -----------------------------------------------------------

@Composable
fun ShowAlertDialog(
    title: String,
    msg: String,
    btnMsg: String,
    onConfirmClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest,
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
        text = { Text(text = msg, fontWeight = FontWeight.Medium, fontSize = 15.sp) },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(text = btnMsg, modifier = Modifier.padding(all=8.dp), fontSize = 16.sp)
            }
        }
    )
}

// ---------------------------------------------------------

@Composable
fun MyAnimShower(name: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(name))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(240.dp)
    )
}

// --------------------------------------------------------------
