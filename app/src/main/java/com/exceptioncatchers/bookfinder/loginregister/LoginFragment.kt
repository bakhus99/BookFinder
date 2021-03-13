package com.exceptioncatchers.bookfinder.loginregister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null){
            val action = LoginFragmentDirections.actionLoginFragmentToMessagesFragment()
            findNavController().navigate(action)
        }

        binding.btnLogin.setOnClickListener {
            userSingIn()
        }

    }

    private fun userSingIn() {
        val email =  binding.etLogin.text.toString()
        val password =  binding.etPassword.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Toast.makeText(context,"Loged in",Toast.LENGTH_SHORT).show()
            }
    }


}