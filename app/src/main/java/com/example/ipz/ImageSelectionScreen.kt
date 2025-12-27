package com.example.ipz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ImageItem(
    val id: String,
    val icon: ImageVector,
    val nameResId: Int
)

@Composable
fun getAvailableImages() = listOf(
    ImageItem("cake", Icons.Default.Cake, R.string.image_cake),
    ImageItem("favorite", Icons.Default.Favorite, R.string.image_favorite),
    ImageItem("star", Icons.Default.Star, R.string.image_star),
    ImageItem("emoji", Icons.Default.EmojiEmotions, R.string.image_emoji),
    ImageItem("gift", Icons.Default.CardGiftcard, R.string.image_gift),
    ImageItem("celebration", Icons.Default.Celebration, R.string.image_celebration),
    ImageItem("flower", Icons.Default.LocalFlorist, R.string.image_flower),
    ImageItem("sun", Icons.Default.WbSunny, R.string.image_sun),
    ImageItem("music", Icons.Default.MusicNote, R.string.image_music),
    ImageItem("diamond", Icons.Default.Diamond, R.string.image_diamond)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSelectionScreen(
    initialImage: String,
    onNextClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val availableImages = getAvailableImages()
    var selectedImage by remember {
        mutableStateOf(
            if (initialImage.isEmpty()) "" else initialImage
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.image_selection_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.image_selection_header),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(availableImages) { image ->
                    ImageItemView(
                        image = image,
                        isSelected = image.id == selectedImage,
                        onClick = { selectedImage = image.id }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onNextClick(selectedImage) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = selectedImage.isNotEmpty()
            ) {
                Text(
                    text = stringResource(R.string.button_next),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ImageItemView(
    image: ImageItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface
            )
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = image.icon,
            contentDescription = stringResource(image.nameResId),
            modifier = Modifier.size(48.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(image.nameResId),
            fontSize = 12.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )
    }
}