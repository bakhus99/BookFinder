package com.exceptioncatchers.bookfinder.bookdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModel
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModelFactory
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.databinding.FragmentBookDetailsBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.userlibrary.FragmentUserLibrary

class FragmentBookDetails : Fragment(R.layout.fragment_book_details) {
    private val binding: FragmentBookDetailsBinding by lazy {
        FragmentBookDetailsBinding.inflate(layoutInflater)
    }
    private val bookDetailsViewModel: BookDetailsViewModel by viewModels {
        BookDetailsViewModelFactory()
    }
    private lateinit var bookId: String
    private var user = User()
    private val args: FragmentBookDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bookId = requireNotNull(requireArguments().getString(BOOK_KEY))
        bookId = args.bookId
        subscribeBookDetailsResponse(bookId)
    }

    private fun subscribeBookDetailsResponse(bookId: String) {
        bookDetailsViewModel.getBook(bookId)
        bookDetailsViewModel.getBookDetails()
            .observe(this.viewLifecycleOwner, {
                subscribeUserInfo(it.userUid)
                setupBookDetails(it)
            })
    }

    private fun subscribeUserInfo(userId: String) {
        bookDetailsViewModel.getUser(userId)
        bookDetailsViewModel.getUserInfo().observe(this.viewLifecycleOwner, { userResponse ->
            userResponse?.let {
                user = it
            }
        })
    }

    private fun setupBookDetails(book: BookDetails) {
        binding.bookTitle.append(book.bookTitle)
        binding.bookAutor.append(book.bookAuthor)
        this.context?.let {
            Glide.with(it)
                .asBitmap()
                .load(book.bookPoster)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.bookPoster)
        }
        binding.ratingBar.rating = book.bookRating
        binding.cardViewButton.setOnClickListener { TODO() }
        binding.bookDescription.append(book.bookDescription)
        //реализовать диалог с полным описанием по клику
        binding.bookDescription.setOnClickListener { TODO() }
        //реализовать подгрузку юзернейма и листенер с переходом в профиль
        binding.bookOwnerUsername.append(book.userUid)
        binding.bookOwnerUsername.setOnClickListener { goUserLibrary(user.uid) }
        binding.quantityCount.append(book.sharingCount.toString())
    }

    private fun goUserLibrary(userId: String) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.nav_host_fragment, FragmentUserLibrary.newInstance(userId))
            .addToBackStack("FragmentBookDetails")
            .commit()
    }

//    companion object {
//        private const val BOOK_KEY = "book"
//        fun newInstance(bookId: String): Fragment = FragmentBookDetails().apply {
//            arguments = bundleOf(BOOK_KEY to bookId)
//        }
//    }
}