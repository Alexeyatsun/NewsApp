package com.example.gonews.presentation

import UnsplashNewsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gonews.R
import com.example.gonews.databinding.FragmentRootBinding
import com.example.gonews.model.news.Article
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RootFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRootBinding.inflate(layoutInflater, container, false)
        val bundle = Bundle()

        val listener = object : OnItemClickListener {
            override fun onItemClick(article: Article?) {
                if (article?.title != null) {
                    bundle.putParcelable("newsArgs", article)
                    findNavController().navigate(
                        R.id.action_rootFragment_to_fullNewsFragment,
                        bundle
                    )
                }
            }
        }

        val adapter = UnsplashNewsAdapter(listener)

        binding.apply {
            rv.setHasFixedSize(true)
            rv.adapter = adapter
        }

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        buttonPressInitialization(binding)

        return binding.root
    }

    private fun buttonPressInitialization(binding: FragmentRootBinding) {

        binding.apply {
            btnSearch.setOnClickListener {
                viewModel.searchNews(binding.editTextText.text.toString())
            }
            editTextText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == IME_ACTION_DONE || actionId == IME_ACTION_SEND) {
                    viewModel.searchNews(binding.editTextText.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }
}