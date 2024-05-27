package info.fekri8614.thermocall.ui.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.ui.theme.BlueLightBack
import info.fekri8614.thermocall.ui.theme.GrayLightColor

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Surface(
                shape = CircleShape,
                elevation = 0.dp,
                modifier = Modifier.size(110.dp),
                color = Color.Transparent,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Alireza Hatami",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        ),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Owner".uppercase(),
                        modifier = Modifier
                            .background(BlueLightBack)
                            .padding(2.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 14.sp, fontStyle = FontStyle.Italic
                        ),
                    )
                }
                Text(
                    "Lab Technician",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Black,
                    ),
                )
                Text(
                    "alirez.a.hatami87@gmail.com",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Black,
                    ),
                )
            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(GrayLightColor)
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Column {
            Text("Shared with", fontSize = 20.sp, fontWeight = FontWeight.Black)
            Text("Shared with no one, yet.", style = TextStyle(color = GrayLightColor))
        }
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = { /*TODO*/ }, modifier=Modifier.fillMaxWidth(0.7f)) {
                Text("Start sharing".uppercase(), style=  TextStyle(), modifier = Modifier.padding(8.dp))
            }
        }
    }
}
