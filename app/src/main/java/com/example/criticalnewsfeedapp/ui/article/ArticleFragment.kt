package com.example.criticalnewsfeedapp.ui.article

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.criticalnewsfeedapp.MainActivity
import com.example.criticalnewsfeedapp.R
import com.example.criticalnewsfeedapp.databinding.FragmentArticleBinding
import com.example.criticalnewsfeedapp.domain.Article
import com.squareup.picasso.Picasso

class ArticleFragment : Fragment() {

    companion object {
        const val ARGUMENT_ARTICLE = "com.example.criticalnewsfeedapp.ui.article.ARGUMENT_ARTICLE"
    }

    private lateinit var binding: FragmentArticleBinding
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!requireArguments().isEmpty) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                viewModel.article = requireArguments().getSerializable(ARGUMENT_ARTICLE, Article::class.java)
            }
            else {
                viewModel.article = requireArguments().getSerializable(ARGUMENT_ARTICLE) as Article
            }
        }

        if (activity is MainActivity) {
            (activity as MainActivity).setActionBarTitle(getString(R.string.newsSourceName))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater)

        binding.articleTitleTv.text = viewModel.article?.title
        binding.imageCaptionTv.text = viewModel.article?.description
        binding.articlePublishDateTv.text = viewModel.getFormattedPublishDate()
        binding.articleAuthorTv.text = viewModel.article?.author
        binding.articleTextTv.text = if (viewModel.article != null) viewModel.article?.content else "No content available."

        Picasso.get().load(viewModel.article?.urlToImage)
            .into(binding.articleImageIv)

        return binding.root
    }
}