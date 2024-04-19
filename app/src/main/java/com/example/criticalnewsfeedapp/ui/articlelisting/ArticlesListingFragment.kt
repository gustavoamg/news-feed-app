package com.example.criticalnewsfeedapp.ui.articlelisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.criticalnewsfeedapp.MainActivity
import com.example.criticalnewsfeedapp.R
import com.example.criticalnewsfeedapp.databinding.FragmentArticleBinding
import com.example.criticalnewsfeedapp.databinding.FragmentArticlesListingBinding
import com.example.criticalnewsfeedapp.utils.WindowUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesListingFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesListingFragment()
    }

    private lateinit var binding: FragmentArticlesListingBinding
    private val viewModel: ArticlesListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListingBinding.inflate(layoutInflater)

        if (activity is MainActivity) {
            (activity as MainActivity).setActionBarTitle(getString(R.string.newsSourceName))
        }

        val recyclerView: RecyclerView = binding.articlesListingRv
        recyclerView.layoutManager = createLayoutManager()
        recyclerView.adapter = viewModel.articleListAdapter

        viewModel.newsLiveData.observe(viewLifecycleOwner, viewModel.newsLiveDataObserver)

        return binding.root
    }

    private fun createLayoutManager(): RecyclerView.LayoutManager {
        val windowSizeClasses = WindowUtils.computeWindowSizeClasses(requireContext())
        return if (windowSizeClasses.first == WindowWidthSizeClass.EXPANDED) {
            GridLayoutManager(context, 2)
        } else {
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}