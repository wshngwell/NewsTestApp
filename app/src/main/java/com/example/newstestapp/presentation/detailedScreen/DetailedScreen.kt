package com.example.newstestapp.presentation.detailedScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newstestapp.domain.entities.News


@Composable
fun DetailedScreen(
    news: News
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title ?: "[Название отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            news.imageUrl?.let {
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = news.fullText ?: "[Полный текст отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Text(
                    text = news.author ?: "[Автор отсутствует в api]",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp
                )

                Text(
                    text = news.dateOfPublication ?: "[Дата публикации отсутствует в api]",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    Color.Magenta.copy(alpha = 0.3f),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = Color.Unspecified
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Добавить в избранное",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp
                )

            }


        }
    }

}