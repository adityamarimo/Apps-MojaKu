package com.moja.mojaku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moja.mojaku.core.data.source.Resource
import com.moja.mojaku.core.ui.MangaAdapter
import com.moja.mojaku.databinding.FragmentHomeBinding
import com.moja.mojaku.ui.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val mangaAdapter = MangaAdapter()
            mangaAdapter.onItemClick = { data ->
                val intent = Intent(activity, DetailMangaActivity::class.java)
                intent.putExtra(DetailMangaActivity.DATA_MANGA, data)
                startActivity(intent)
            }

            homeViewModel.manga.observe(viewLifecycleOwner, { manga ->
                if (manga != null) {
                    when (manga) {
                        is Resource.Loading -> binding.pbHomeManga.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbHomeManga.visibility = View.GONE
                            mangaAdapter.setData(manga.data)
                        }
                        is Resource.Error -> {
                            binding.pbHomeManga.visibility = View.GONE
                            binding.layoutHomeError.root.visibility = View.VISIBLE
                        }
                    }
                }
            })

            with(binding.rvHomeManga) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = mangaAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}