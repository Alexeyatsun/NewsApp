package com.example.gonews.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.gonews.R
import com.example.gonews.databinding.FragmentFullNewsBinding
import com.example.gonews.model.news.Article


class FullNewsFragment : Fragment() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFullNewsBinding.inflate(layoutInflater, container, false)
        val article = arguments?.getParcelable<Article>("newsArgs")
        try {
            binding.apply {
                tvDescription.text = article?.description
                tvTitle.text = article?.title
                Glide.with(requireContext())
                    .load(article?.urlToImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.image_not_found)
                    .into(ivImage)
            }
        } catch (e: Exception) {
            println("Exception")
        }

        return binding.root
    }

}