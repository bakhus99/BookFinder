package com.exceptioncatchers.bookfinder.messages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentMessagesBinding
import com.exceptioncatchers.bookfinder.loginregister.adapter.UserItem
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val TAG = "MessagesFragment"

class MessagesFragment : Fragment(R.layout.fragment_messages) {


    private lateinit var binding: FragmentMessagesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessagesBinding.bind(view)
        fetchCurrentUser()
        fetchUsers()
    }

    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(User::class.java)
                Log.d("MainFragnent", "current User: ${currentUser?.username} ")
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                snapshot.children.forEach {
                    Log.d(TAG, "onDataChange: ${it.toString()} ")
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(UserItem(user))
                    }
                }
                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItem

                    val action =
                        MessagesFragmentDirections.actionMessagesFragmentToChatFragment(userItem.user)
                    findNavController().navigate(action)

                }

                binding.rvChatUsers.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    companion object {
        var currentUser: User? = null
    }

}