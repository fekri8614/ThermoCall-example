package info.fekri8614.thermocall.ui.feature.setup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import info.fekri8614.thermocall.ui.theme.Shapes

class SetupWidget(private val setupViewModel: SetupViewModel) {
    @Composable
    fun MainTextField(
        edtValue: String,
        hint: String,
        onValueChanges: (String) -> Unit
    ) {
        OutlinedTextField(
            label = { Text(hint) },
            value = edtValue,
            singleLine = true,
            onValueChange = onValueChanges,
            placeholder = { Text(hint) },
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 12.dp),
            shape = Shapes.medium,
        )
    }
}