package com.moja.mojaku.favorite.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moja.mojaku.core.ui.MangaAdapter
import com.moja.mojaku.di.FavoriteModuleDependencies
import com.moja.mojaku.favorite.DaggerFavoriteComponent
import com.moja.mojaku.favorite.FavoriteViewModel
import com.moja.mojaku.favorite.ViewModelFactory
import com.moja.mojaku.favorite.databinding.FragmentFavoriteBinding
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val mangaAdapter = MangaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerFavoriteComponent.builder()
            .context(requireContext().applicationContext)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListManga()
        actionToDetail()
    }

    @SuppressLint("SetTextI18n")
    private fun getListManga() {
        favoriteViewModel.manga.observe(this, { manga ->
            binding?.pbFavManga?.visibility = View.GONE
            mangaAdapter.setData(manga)
            if (manga.isNullOrEmpty()) {
                binding?.tvFavEmpty?.visibility = View.VISIBLE
            } else {
                with(binding?.rvFavManga) {
                    binding?.tvFavEmpty?.visibility = View.GONE
                    this?.layoutManager = GridLayoutManager(this?.context, 2)
                    this?.setHasFixedSize(true)
                    this?.adapter = mangaAdapter
                }
            }
        })
    }

    private fun actionToDetail() {
        mangaAdapter.onItemClick = { data ->
            val intent = Intent(activity, DetailMangaActivity::class.java)
            intent.putExtra(DetailMangaActivity.DATA_MANGA, data)
            context?.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}