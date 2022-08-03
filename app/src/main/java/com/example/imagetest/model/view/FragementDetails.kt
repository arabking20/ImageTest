package com.example.imagetest.model.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imagetest.R
import com.example.imagetest.databinding.MovieDetailsFragmentLayoutBinding
import com.example.imagetest.databinding.MovieItemLayoutBinding
import com.example.imagetest.databinding.MovieListFragmentLayoutBinding
import com.example.imagetest.model.MovieResponse
import com.squareup.picasso.Picasso

class FragementDetails : Fragment() {
    companion object {
        const val MOVIERESPONSE_EXTRA = ""

        // Factory fragment function
        fun newInstance(dataItem: MovieResponse)
                : FragementDetails {
            val fragment = FragementDetails()
            val bundle = Bundle()
            bundle.putParcelable(MOVIERESPONSE_EXTRA, dataItem)
            fragment.arguments = bundle
            return fragment

        }
    }

    private lateinit var binding: MovieDetailsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MovieDetailsFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.getParcelable<MovieResponse>(MOVIERESPONSE_EXTRA)?.let{
            initViews(it)
        }

        return binding.root
    }

    fun initViews(it:MovieResponse) {
        binding.tvMovieGenreDetails.text = getString(R.string.movie_detail_genre,
        it.genre.toString())
        binding.tvMovieRatingDetails.text=
            getString(R.string.movie_detail_rating,
            it.rating)
        binding.tvMovieReleaseDetails.text=
            getString(R.string.movie_detail_release,
            it.releaseYear)
        binding.tvMovieTitleDetails.text=it.title
        Picasso.get().load(it.image).into(binding.ivMoviePosterDetails)
    }
}






