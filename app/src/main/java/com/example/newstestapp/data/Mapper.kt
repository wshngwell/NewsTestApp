package com.example.newstestapp.data

import com.example.newstestapp.data.localStorage.dbModels.NewsDbModel
import com.example.newstestapp.data.remoteDataSource.dto.NewsResponseDto
import com.example.newstestapp.domain.entities.News

fun mapNewsResponseDtoToNews(newsResponseDto: NewsResponseDto) =
    newsResponseDto.newsList.map {
        News(
            title = it.title,
            shortDescr = it.description,
            source = it.sourceDto.name,
            fullText = it.content,
            author = it.author,
            dateOfPublication = it.publishedAt,
            imageUrl = it.imageUrl,
            url = it.url,
        )
    }

fun mapNewsListFromDbToNews(newsListFromDb: List<NewsDbModel>) =
    newsListFromDb.map {
        News(
            title = it.title,
            shortDescr = it.shortDescr,
            source = it.source,
            fullText = it.fullText,
            author = it.author,
            dateOfPublication = it.dateOfPublication,
            imageUrl = it.imageUrl,
            url = it.url,
        )
    }

fun mapToNewsToNewsListFromDb(news: News) = NewsDbModel(
    title = news.title ?: "",
    shortDescr = news.shortDescr ?: "",
    source = news.source ?: "",
    fullText = news.fullText ?: "",
    author = news.author ?: "",
    dateOfPublication = news.dateOfPublication ?: "",
    imageUrl = news.imageUrl ?: "",
    url = news.url ?: ""
)