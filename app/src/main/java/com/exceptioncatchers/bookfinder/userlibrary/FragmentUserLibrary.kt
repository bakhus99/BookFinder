package com.exceptioncatchers.bookfinder.userlibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.FragmentBookDetails
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.FragmentUserLibraryBinding
import com.exceptioncatchers.bookfinder.userlibrary.adapter.ItemClickListener
import com.exceptioncatchers.bookfinder.userlibrary.adapter.UserLibraryAdapter
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModel
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModelFactory

class FragmentUserLibrary : Fragment(R.layout.fragment_user_library) {
    private val binding: FragmentUserLibraryBinding by lazy {
        FragmentUserLibraryBinding.inflate(layoutInflater)
    }
    private val userLibraryViewModel: UserLibraryViewModel by viewModels {
        UserLibraryViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeBookLibrary()
    }

    private fun subscribeBookLibrary() {
        userLibraryViewModel.getBookList().observe(this.viewLifecycleOwner, { initRecycler(it) })
    }

    private fun initRecycler(bookList: List<BookDetails>) {
        val adapter = UserLibraryAdapter(bookList, clickListener)
        binding.recyclerBookLibrary.adapter = adapter
        binding.recyclerBookLibrary.layoutManager = GridLayoutManager(context, COLUMN)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(book: BookDetails) {
            showBookDetailsFragment(book)
        }
    }

    private fun showBookDetailsFragment(book: BookDetails) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.nav_host_fragment, FragmentBookDetails.newInstance(book))
            .addToBackStack("FragmentBookDetails")
            .commit()
    }

    companion object {
        private const val COLUMN = 3
    }
}