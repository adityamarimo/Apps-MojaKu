package com.moja.mojaku.utils

import com.moja.mojaku.core.domain.model.Manga

object DummyData {
    fun listManga() = listOf(
        Manga(
            malId = 2,
            title = "Berserk",
            title_japanese = "ベルセルク",
            title_english = "Berserk",
            title_synonyms = "Berserk: The Prototype",
            url = "https://myanimelist.net/manga/2/Berserk",
            images = "https://cdn.myanimelist.net/images/manga/1/157897l.jpg",
            type = "Manga",
            chapters = 380,
            volumes = 41,
            status = "On Hiatus",
            published = "Aug 25, 1989 to Sep 10, 2021",
            scored = 9.44,
            scored_by = 249487,
            rank = 1,
            popularity = 2,
            members = 516356,
            synopsis = "Guts, a former mercenary now known as the \"Black Swordsman,\" is out for revenge. After a tumultuous childhood, he finally finds someone he respects and believes he can trust, only to have everything fall apart when this person takes away everything important to Guts for the purpose of fulfilling his own desires. Now marked for death, Guts becomes condemned to a fate in which he is relentlessly pursued by demonic beings. Setting out on a dreadful quest riddled with misfortune, Guts, armed with a massive sword and monstrous strength, will let nothing stop him, not even death itself, until he is finally able to take the head of the one who stripped him—and his loved one—of their humanity. [Written by MAL Rewrite] Included one-shot: Volume 14: Berserk: The Prototype",
            authors = "Miura, Kentarou",
            serializations = "Young Animal",
            genres = "Action, Adventure, Award Winning, Drama, Fantasy, Horror, Supernatural",
            themes = "Gore, Military, Mythology, Psychological",
            demographics = "Seinen",
            isFavorite = false
        )
    )

}