package com.exceptioncatchers.bookfinder.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentBookDetailsBinding

class FragmentBookDetails : Fragment(R.layout.fragment_book_details) {
    private val binding: FragmentBookDetailsBinding by lazy {
        FragmentBookDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}