package com.moja.mojaku.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moja.mojaku.core.data.source.Resource
import com.moja.mojaku.core.ui.MangaAdapter
import com.moja.mojaku.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mangaAdapter = MangaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearch()
    }

    @SuppressLint("CheckResult")
    private fun initSearch() {
        binding.svSearchMain.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        searchViewModel.search(query).observe(viewLifecycleOwner, { manga ->
                            if (manga != null) {
                                when (manga) {
                                    is Resource.Loading -> binding.pbSearchManga.visibility =
                                        View.VISIBLE
                                    is Resource.Success -> {
                                        with(binding) {
                                            layoutSearchError.root.visibility = View.GONE
                                            pbSearchManga.visibility = View.GONE
                                            mangaAdapter.setData(manga.data)
                                        }

                                    }
                                    is Resource.Error -> {
                                        with(binding) {
                                            val empty =
                                                "the manga you are looking for doesn't exist"
                                            rvSearchManga.adapter = MangaAdapter()
                                            pbSearchManga.visibility = View.GONE
                                            layoutSearchError.tvErrorCaption.text = empty
                                            layoutSearchError.root.visibility = View.VISIBLE
                                        }

                                    }
                                }
                            }
                        })
                        with(binding.rvSearchManga) {
                            layoutManager = GridLayoutManager(context, 2)
                            setHasFixedSize(true)
                            adapter = mangaAdapter
                        }
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun onDestroyView() {
        binding.rvSearchManga.adapter = null
        _binding = null
        super.onDestroyView()
    }

}