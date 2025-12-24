package com.malomnogo.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.rememberAsyncImagePainter

@Composable
fun WebImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    previewPlaceholder: Painter? = null,
    loadingPlaceholder: Painter? = null,
    errorPlaceholder: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val painter =
        if (LocalInspectionMode.current && previewPlaceholder != null) {
            previewPlaceholder
        } else {
            rememberAsyncImagePainter(
                model = url,
                placeholder = loadingPlaceholder,
                error = errorPlaceholder,
                contentScale = contentScale,
            )
        }

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}
