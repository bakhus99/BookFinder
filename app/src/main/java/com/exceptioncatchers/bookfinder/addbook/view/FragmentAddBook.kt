package com.exceptioncatchers.bookfinder.addbook.view

import androidx.fragment.app.Fragment
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentAddBookBinding

class FragmentAddBook : Fragment(R.layout.fragment_add_book) {
    private val binding: FragmentAddBookBinding by lazy {
        FragmentAddBookBinding.inflate(layoutInflater)
    }
}