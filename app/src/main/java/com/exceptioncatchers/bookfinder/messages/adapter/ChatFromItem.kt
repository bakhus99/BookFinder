package com.exceptioncatchers.bookfinder.messages.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.ChatFromRowBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.xwray.groupie.viewbinding.BindableItem

class ChatFromItem(val text: String,val user: User) : BindableItem<ChatFromRowBinding>() {
    override fun bind(viewBinding: ChatFromRowBinding, position: Int) {
        viewBinding.tvUserName.text = text
        Glide.with(viewBinding.root.context).load(user.profileImage).into(viewBinding.userImageMsg)
    }

    override fun getLayout() = R.layout.chat_from_row

    override fun initializeViewBinding(view: View): ChatFromRowBinding =
        ChatFromRowBinding.bind(view)
}