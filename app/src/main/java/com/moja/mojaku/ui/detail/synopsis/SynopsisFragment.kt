package com.moja.mojaku.ui.detail.synopsis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.databinding.FragmentSynopsisBinding
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SynopsisFragment : Fragment() {

    private var _binding: FragmentSynopsisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSynopsisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailManga =
            activity?.intent?.getParcelableExtra<Manga>(DetailMangaActivity.DATA_MANGA)
        showSynopsis(detailManga)
    }

    private fun showSynopsis(detailManga: Manga?) {
        detailManga?.let {
            with(binding) {
                layoutSynopsis.tvSynopsisValue.text = it.synopsis
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}