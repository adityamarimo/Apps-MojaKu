package com.moja.mojaku.ui.detail.synopsis

import android.os.Bundle
import android.view.View
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.databinding.FragmentSynopsisBinding
import com.moja.mojaku.ui.base.BaseFragment
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SynopsisFragment : BaseFragment<FragmentSynopsisBinding>() {
    override fun getViewBinding(): FragmentSynopsisBinding =
        FragmentSynopsisBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailManga =
            activity?.intent?.getParcelableExtra<Manga>(DetailMangaActivity.DATA_MANGA)
        showSynopsis(detailManga)
    }

    private fun showSynopsis(detailManga: Manga?) {
        detailManga?.let {
            with(binding) {
                this!!.layoutSynopsis.tvSynopsisValue.text = it.synopsis
            }
        }
    }
}