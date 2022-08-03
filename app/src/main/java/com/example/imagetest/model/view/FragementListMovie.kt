package com.example.imagetest.model.view

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagetest.MovieApplication
import com.example.imagetest.R
import com.example.imagetest.databinding.MovieListFragmentLayoutBinding
import com.example.imagetest.model.MovieResponse
import com.example.imagetest.model.Repository
import com.example.imagetest.model.view.adapter.MovieAdapter

private const val TAG = "FragementListMovie"

class FragementListMovie : Fragment() {
    private lateinit var binding:
            MovieListFragmentLayoutBinding

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MovieListFragmentLayoutBinding
            .inflate(
                inflater,
                container,
                false
            )
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //list<string> some = array;ist()
        val response = Repository().deSerialize(
            MovieApplication.movieApplication
        )
        updateAdapter(response)
    }

    private fun updateAdapter(response: List<MovieResponse>) {
        Log.d(TAG, "updateAdapter: $response")
        adapter.updateList(response)
    }

    private fun initViews (){
        adapter = MovieAdapter(emptyList()) {
        openDetails(it)
        }

        binding.movieList.adapter = adapter
        binding.movieList.layoutManager = LinearLayoutManager(context)
    }
    private fun openDetails(dataItem: MovieResponse){
        activity?.openDetails(dataItem)
    }

    private fun FragmentActivity.openDetails(dataItem: MovieResponse){
        val fragmentDetails: FragementDetails =
            supportFragmentManager.findFragmentById(R.id.fragment_details) as FragementDetails

        if (fragmentDetails == null )

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FragementDetails.newInstance(dataItem))
            .addToBackStack(null)
            .commit()
        else
            fragmentDetails.initViews(dataItem)
    }
}