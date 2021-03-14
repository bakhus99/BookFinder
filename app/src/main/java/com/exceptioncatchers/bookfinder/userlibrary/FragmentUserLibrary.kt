package com.exceptioncatchers.bookfinder.userlibrary

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.FragmentBookDetails
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.FragmentUserLibraryBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.userlibrary.adapter.ItemClickListener
import com.exceptioncatchers.bookfinder.userlibrary.adapter.UserLibraryAdapter
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModel
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModelFactory

class FragmentUserLibrary : Fragment(R.layout.fragment_user_library) {
    private val userLibraryViewModel: UserLibraryViewModel by viewModels {
        UserLibraryViewModelFactory()
    }
    private lateinit var userId: String
    private lateinit var binding: FragmentUserLibraryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserLibraryBinding.bind(view)
        userId = requireNotNull(requireArguments().getString(USER_KEY))
        subscribeBookLibrary(userId)
    }

    private fun subscribeBookLibrary(userId: String) {
        userLibraryViewModel.getUser(userId)
        userLibraryViewModel.getBookListFromUser(userId)
        userLibraryViewModel.getBookList().observe(this.viewLifecycleOwner, { initRecycler(it) })
        userLibraryViewModel.getUserInfoInDatabase().observe(this.viewLifecycleOwner, { initUserinfo(it) })
    }

    private fun initUserinfo(user: User) {
        context?.let {
            Glide.with(it)
                .asBitmap()
                .load(user.profileImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.userImage)
        }
        binding.userName.text = user.username
    }

    private fun initRecycler(bookList: List<BookDetails>) {
        val adapter = UserLibraryAdapter(bookList, clickListener)
        binding.recyclerBookLibrary.adapter = adapter
        binding.recyclerBookLibrary.layoutManager = GridLayoutManager(context, COLUMN)
        binding.userLibrary.text = bookList.size.toString()
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(book: BookDetails) {
//            showBookDetailsFragment(book)
        }
    }

//    private fun showBookDetailsFragment(book: BookDetails) {
//        requireFragmentManager().beginTransaction()
//            .replace(R.id.nav_host_fragment, FragmentBookDetails())
//            .addToBackStack("FragmentBookDetails")
//            .commit()
//    }

    companion object {
        private const val COLUMN = 2
        private const val USER_KEY = "user"
        fun newInstance(userId: String): Fragment = FragmentUserLibrary().apply {
            arguments = bundleOf(USER_KEY to userId)
        }
    }
}