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

private val encodeSans = FontFamily(
    Font(R.font.encodesans_regular),
    Font(R.font.encodesans_light),
    Font(R.font.encodesans_bold),
    Font(R.font.encodesans_medium)
)

val encodeSansTypography = Typography(
    displayLarge = Typography.displayLarge.copy(fontFamily = encodeSans),
    displayMedium = Typography.displayMedium.copy(fontFamily = encodeSans),
    displaySmall = Typography.displaySmall.copy(fontFamily = encodeSans),
    headlineLarge = Typography.headlineLarge.copy(fontFamily = encodeSans),
    bodyLarge = Typography.bodyLarge.copy(fontFamily = encodeSans),
    bodyMedium = Typography.bodyMedium.copy(fontFamily = encodeSans),
    bodySmall = Typography.bodySmall.copy(fontFamily = encodeSans),
    headlineMedium = Typography.headlineMedium.copy(fontFamily = encodeSans),
    headlineSmall = Typography.headlineSmall.copy(fontFamily = encodeSans),
    labelLarge = Typography.labelLarge.copy(fontFamily = encodeSans),
    labelMedium = Typography.labelMedium.copy(fontFamily = encodeSans),
    labelSmall = Typography.labelSmall.copy(fontFamily = encodeSans),
    titleLarge = Typography.titleLarge.copy(fontFamily = encodeSans),
    titleMedium = Typography.titleMedium.copy(fontFamily = encodeSans),
    titleSmall = Typography.titleSmall.copy(fontFamily = encodeSans),
)

val oxygenTypography = Typography(
    displayLarge = Typography.displayLarge.copy(fontFamily = oxygen),
    displayMedium = Typography.displayMedium.copy(fontFamily = oxygen),
    displaySmall = Typography.displaySmall.copy(fontFamily = oxygen),
    headlineLarge = Typography.headlineLarge.copy(fontFamily = oxygen),
    bodyLarge = Typography.bodyLarge.copy(fontFamily = oxygen),
    bodyMedium = Typography.bodyMedium.copy(fontFamily = oxygen),
    bodySmall = Typography.bodySmall.copy(fontFamily = oxygen),
    headlineMedium = Typography.headlineMedium.copy(fontFamily = oxygen),
    headlineSmall = Typography.headlineSmall.copy(fontFamily = oxygen),
    labelLarge = Typography.labelLarge.copy(fontFamily = oxygen),
    labelMedium = Typography.labelMedium.copy(fontFamily = oxygen),
    labelSmall = Typography.labelSmall.copy(fontFamily = oxygen),
    titleLarge = Typography.titleLarge.copy(fontFamily = oxygen),
    titleMedium = Typography.titleMedium.copy(fontFamily = oxygen),
    titleSmall = Typography.titleSmall.copy(fontFamily = oxygen),
)
