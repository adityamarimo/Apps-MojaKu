package com.moja.mojaku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moja.mojaku.core.data.source.Resource
import com.moja.mojaku.core.ui.MangaAdapter
import com.moja.mojaku.databinding.FragmentHomeBinding
import com.moja.mojaku.ui.base.BaseFragment
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val mangaAdapter = MangaAdapter()
            mangaAdapter.onItemClick = { data ->
                val intent = Intent(activity, DetailMangaActivity::class.java)
                intent.putExtra(DetailMangaActivity.DATA_MANGA, data)
                startActivity(intent)
            }

            homeViewModel.manga.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding!!.pbHomeManga.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding!!.pbHomeManga.visibility = View.GONE
                            mangaAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            binding!!.pbHomeManga.visibility = View.GONE
                            binding!!.layoutHomeError.root.visibility = View.VISIBLE
                        }
                    }
                }
            })

            with(binding!!.rvHomeManga) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = mangaAdapter
            }
        }
    }
}