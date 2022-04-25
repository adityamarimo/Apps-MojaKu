package com.moja.mojaku.core.utils

import com.moja.mojaku.core.data.source.local.entity.MangaEntity
import com.moja.mojaku.core.data.source.remote.response.MangaResponsesData
import com.moja.mojaku.core.domain.model.Manga

object DataMapper {
    fun mapResponsesToEntities(input: List<MangaResponsesData>): List<MangaEntity> {
        val mangaList = ArrayList<MangaEntity>()
        input.map { it ->
            val manga = MangaEntity(
                malId = it.mal_id,
                title = it.title,
                title_english = checkNullOrNot(it.titleEnglish),
                title_japanese = checkNullOrNot(it.titleJapanese),
                title_synonyms = checkNullOrNot(it.titleSynonyms.joinToString()),
                url = it.url,
                images = it.images.jpg.largeImageUrl,
                type = it.type,
                chapters = it.chapters,
                volumes = it.volumes,
                status = it.status,
                published = it.published.string,
                scored = it.scored,
                scored_by = it.scoredBy,
                rank = it.rank,
                popularity = it.popularity,
                members = it.members,
                synopsis = it.synopsis,
                authors = checkNullOrNot(it.authors.joinToString { it.name }),
                serializations = checkNullOrNot(it.serializations.joinToString { it.name }),
                genres = checkNullOrNot(it.genres.joinToString { it.name }),
                themes = checkNullOrNot(it.themes.joinToString { it.name }),
                demographics = checkNullOrNot(it.demographics.joinToString { it.name }),
                isFavorite = false
            )
            mangaList.add(manga)
        }
        return mangaList
    }

    fun mapEntitiesToDomain(input: List<MangaEntity>): List<Manga> =
        input.map {
            Manga(
                malId = it.malId,
                title = it.title,
                title_english = checkNullOrNot(it.title_english),
                title_japanese = checkNullOrNot(it.title_japanese),
                title_synonyms = checkNullOrNot(it.title_synonyms),
                url = it.url,
                images = it.images,
                type = it.type,
                chapters = it.chapters,
                volumes = it.volumes,
                status = it.status,
                published = it.published,
                scored = it.scored,
                scored_by = it.scored_by,
                rank = it.rank,
                popularity = it.popularity,
                members = it.members,
                synopsis = it.synopsis,
                authors = it.authors,
                serializations = it.serializations,
                genres = it.genres,
                themes = it.themes,
                demographics = it.demographics,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Manga) = MangaEntity(
        malId = input.malId,
        title = input.title,
        title_english = checkNullOrNot(input.title_english),
        title_japanese = checkNullOrNot(input.title_japanese),
        title_synonyms = checkNullOrNot(input.title_synonyms),
        url = input.url,
        images = input.images,
        type = input.type,
        chapters = input.chapters,
        volumes = input.volumes,
        status = input.status,
        published = input.published,
        scored = input.scored,
        scored_by = input.scored_by,
        rank = input.rank,
        popularity = input.popularity,
        members = input.members,
        synopsis = input.synopsis,
        authors = input.authors,
        serializations = input.serializations,
        genres = input.genres,
        themes = input.themes,
        demographics = input.demographics,
        isFavorite = input.isFavorite
    )

    private fun checkNullOrNot(value: String?): String {
        return if (value.isNullOrBlank()) "-" else value
    }

    fun mapSearchToDomain(input: List<MangaResponsesData>): List<Manga> {
        val mangaList = ArrayList<Manga>()
        input.map { it ->
            val manga = Manga(
                malId = it.mal_id,
                title = it.title,
                title_english = checkNullOrNot(it.titleEnglish),
                title_japanese = checkNullOrNot(it.titleJapanese),
                title_synonyms = checkNullOrNot(it.titleSynonyms.joinToString()),
                url = it.url,
                images = it.images.jpg.largeImageUrl,
                type = it.type,
                chapters = it.chapters,
                volumes = it.volumes,
                status = it.status,
                published = it.published.string,
                scored = it.scored,
                scored_by = it.scoredBy,
                rank = it.rank,
                popularity = it.popularity,
                members = it.members,
                synopsis = checkNullOrNot(it.synopsis),
                authors = checkNullOrNot(it.authors.joinToString { it.name }),
                serializations = checkNullOrNot(it.serializations.joinToString { it.name }),
                genres = checkNullOrNot(it.genres.joinToString { it.name }),
                themes = checkNullOrNot(it.themes.joinToString { it.name }),
                demographics = checkNullOrNot(it.demographics.joinToString { it.name }),
                isFavorite = false
            )
            mangaList.add(manga)
        }
        return mangaList
    }

}