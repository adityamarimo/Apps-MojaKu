package com.moja.mojaku.ui.detail.information

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.databinding.FragmentInformationBinding
import com.moja.mojaku.ui.base.BaseFragment
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : BaseFragment<FragmentInformationBinding>() {
    override fun getViewBinding(): FragmentInformationBinding =
        FragmentInformationBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailManga =
            activity?.intent?.getParcelableExtra<Manga>(DetailMangaActivity.DATA_MANGA)
        showInformation(detailManga)
    }

    @SuppressLint("SetTextI18n")
    private fun showInformation(detailManga: Manga?) {
        detailManga?.let {
            with(binding) {
                with(this!!.layoutHeadline) {
                    tvHeadlineScore.text = it.scored.toString()
                    tvHeadlineUsers.text = it.scored_by.toString() + " users"
                    tvHeadlineRankedCount.text = "#" + it.rank.toString()
                    tvHeadlinePopularityCount.text = "#" + it.popularity.toString()
                    tvHeadlineMemberCount.text = it.members.toString()
                    tvHeadlineType.text = it.type
                    tvHeadlineSerialization.text = it.serializations
                    tvHeadlineAuthor.text = it.authors
                }

                with(layoutAlternative) {
                    tvAlternativeSynonymsValue.text = it.title_synonyms
                    tvAlternativeJapaneseValue.text = it.title_japanese
                    tvAlternativeEnglishValue.text = it.title_english
                }

                with(layoutInformation) {
                    tvInformationTypeValue.text = it.type
                    tvInformationVolumesValue.text = it.volumes.toString()
                    tvInformationChaptersValue.text = it.chapters.toString()
                    tvInformationStatusValue.text = it.status
                    tvInformationPublishedValue.text = it.published
                    tvInformationGenresValue.text = it.genres
                    tvInformationThemesValue.text = it.themes
                    tvInformationDemographicValue.text = it.demographics
                    tvInformationSerializationValue.text = it.serializations
                    tvInformationAuthorsValue.text = it.authors
                }
            }
        }
    }
}