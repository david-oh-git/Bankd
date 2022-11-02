package io.osemwota.bankd.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.osemwota.bankd.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
     */
)

private val oxygen = FontFamily(
    Font(R.font.oxygen_regular),
    Font(R.font.oxygen_light, FontWeight.Light),
    Font(R.font.oxygen_bold, FontWeight.Bold)
)

val oxygenTypography = Typography(
//    defaultFontFamily = oxygen,
    bodyMedium = TextStyle(
        fontFamily = oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
//    button = TextStyle(
//        fontFamily = oxygen,
//        fontWeight = FontWeight.W500,
//        fontSize = 12.sp
//    )
)
