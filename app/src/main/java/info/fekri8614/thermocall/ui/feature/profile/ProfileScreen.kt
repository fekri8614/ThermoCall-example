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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.fekri8614.thermocall.R

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
                    painter = painterResource(id = R.drawable.astro_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Alireza Hatami",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF283593),
                    ),
                )
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
            modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Gray)
        )
    }
}
