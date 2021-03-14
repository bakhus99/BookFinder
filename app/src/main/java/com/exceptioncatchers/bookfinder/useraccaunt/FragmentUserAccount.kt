package com.exceptioncatchers.bookfinder.useraccaunt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.FragmentUserAccountBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.useraccaunt.adapter.UserSharingListAdapter
import com.exceptioncatchers.bookfinder.useraccaunt.viewmodel.AccountViewModel
import com.exceptioncatchers.bookfinder.useraccaunt.viewmodel.AccountViewModelFactory
import com.exceptioncatchers.bookfinder.userlibrary.adapter.ItemClickListener

class FragmentUserAccount : Fragment(R.layout.fragment_user_account){
    private val viewModel: AccountViewModel by viewModels { AccountViewModelFactory() }
    private lateinit var binding: FragmentUserAccountBinding
    private var userId: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserAccountBinding.bind(view)
        //реализовать передачу юзер айди через аргументы
        subscribeResponseUserList()
    }

    private fun subscribeResponseUserList() {
        viewModel.requestUserInfo(userId)
        viewModel.responseUserInfo().observe(this.viewLifecycleOwner, {setupUserInfo(it)})
        viewModel.requestUserSharingList(userId)
        viewModel.responseUserList().observe(this.viewLifecycleOwner, {initRecycler(it)})
    }

    private fun initRecycler(bookList: List<BookDetails>) {
        val adapter = UserSharingListAdapter(bookList, clickListener)
        binding.recyclerNowReading.adapter = adapter
        binding.recyclerNowReading.layoutManager = LinearLayoutManager(context)
    }

    private fun setupUserInfo(user: User) {
        context?.let {
            Glide.with(it)
                .asBitmap()
                .load(user.profileImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.userPhoto)
        }
        binding.accUserName.text = user.username
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(book: BookDetails) {
//            showBookDetailsFragment(book)
        }
    }
}