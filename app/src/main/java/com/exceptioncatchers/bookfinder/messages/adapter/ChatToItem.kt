package com.exceptioncatchers.bookfinder.messages.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.ChatToRowBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.xwray.groupie.viewbinding.BindableItem

class ChatToItem(val text: String, val user: User) : BindableItem<ChatToRowBinding>() {

    override fun getLayout() = R.layout.chat_to_row

    override fun bind(viewBinding: ChatToRowBinding, position: Int) {
        viewBinding.tvUserName.text = text
        Glide.with(viewBinding.root.context).load(user.profileImage).into(viewBinding.userImageMsg)
        //  viewBinding.userImageMsg.(R.drawable.empty_avatar)
    }

    override fun initializeViewBinding(view: View): ChatToRowBinding =
        ChatToRowBinding.bind(view)


}