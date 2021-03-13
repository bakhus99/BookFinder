package com.exceptioncatchers.bookfinder.messages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentChatBinding
import com.exceptioncatchers.bookfinder.loginregister.models.ChatMessage
import com.exceptioncatchers.bookfinder.messages.adapter.ChatFromItem
import com.exceptioncatchers.bookfinder.messages.adapter.ChatToItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val TAG = "ChatFragment"

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding
    private val args: ChatFragmentArgs by navArgs()
    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        binding.rvChatMsg.adapter = adapter
        // Не работет TODO fix it
        //activity?.actionBar?.title = args.user.username

        Log.d(TAG, "onViewCreated: ${args.user.username}")

        listenForMessages()

        binding.btnSendMsg.setOnClickListener {
            Log.d(TAG, "Sending msg ")
            performSendMsg()
        }

    }

    private fun listenForMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val toId = args.user.uid

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    Log.d(TAG, "onChildAdded: ${chatMessage.text}")

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser = MessagesFragment.currentUser ?: return
                        adapter.add(ChatToItem(chatMessage.text, currentUser))
                    } else {
                        val toUser = args.user
                        adapter.add(ChatFromItem(chatMessage.text, toUser))
                    }

                }
                binding.rvChatMsg.scrollToPosition(adapter.itemCount - 1)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }


        })
    }

    private fun performSendMsg() {
        val message = binding.etUserMsg.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val toId = args.user.uid
        if (fromId == null) return

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRef =
            FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val chatMessage =
            ChatMessage(ref.key!!, message, fromId, toId, System.currentTimeMillis() / 1000)

        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "performSendMsg: ${ref.key}")
                binding.rvChatMsg.scrollToPosition(adapter.itemCount - 1)
                binding.etUserMsg.text?.clear()
            }

        toRef.setValue(chatMessage).addOnSuccessListener {
            Log.d(TAG, "performSendMsg: ${toRef.key}")
        }

    }


}