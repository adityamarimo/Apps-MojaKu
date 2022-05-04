package com.moja.mojaku.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding2.view.RxView
import com.moja.mojaku.R
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.databinding.ActivityDetailMangaBinding
import com.moja.mojaku.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMangaActivity : BaseActivity<ActivityDetailMangaBinding>() {
    private val detailMangaViewModel: DetailMangaViewModel by viewModels()

    companion object {
        const val DATA_MANGA = "data_manga"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_synopsis,
            R.string.tab_information
        )
    }

    override fun getViewBinding(): ActivityDetailMangaBinding =
        ActivityDetailMangaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailManga = intent.getParcelableExtra<Manga>(DATA_MANGA)

        setTabDetail()
        showDetailManga(detailManga)
    }


    private fun setTabDetail() {
        val sectionsPagerAdapter = TabDetailAdapter(this)

        with(binding) {
            val viewPager: ViewPager2 = vpDetailMain
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = tabDetailMain
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    @SuppressLint("CheckResult")
    private fun showDetailManga(detailManga: Manga?) {
        detailManga?.let {
            with(binding) {
                tvDetailTitle.text = it.title
                tvDetailAuthor.text = it.authors
                tvDetailStatus.text = it.status

                imgDetailPoster.load(it.images) {
                    crossfade(true)
                    placeholder(R.drawable.img_placeholder)
                    error(R.drawable.ic_broken)
                }

                imgDetailBanner.load(it.images) {
                    crossfade(true)
                    placeholder(R.drawable.img_placeholder)
                    error(R.drawable.ic_broken)
                }

                var statusFavorite = it.isFavorite
                setStatusFavorite(statusFavorite)

                RxView.clicks(fabDetailFav).subscribe {
                    statusFavorite = !statusFavorite
                    detailMangaViewModel.setFavManga(detailManga, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabDetailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabDetailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_non
                )
            )
        }
    }
}