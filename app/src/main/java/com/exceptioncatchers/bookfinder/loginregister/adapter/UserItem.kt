package com.exceptioncatchers.bookfinder.loginregister.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.UserRowMessagesBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.xwray.groupie.viewbinding.BindableItem


class UserItem( val user: User) : BindableItem<UserRowMessagesBinding>() {

    override fun getLayout() = R.layout.user_row_messages

    override fun initializeViewBinding(view: View): UserRowMessagesBinding =
        UserRowMessagesBinding.bind(view)

    override fun bind(viewBinding: UserRowMessagesBinding, position: Int) {
        viewBinding.userMsgName.text = user.username
        val userImage = viewBinding.userMsgImage
        Glide.with(viewBinding.root.context).load(user.profileImage).into(userImage)
    }
}