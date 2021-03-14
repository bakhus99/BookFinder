package com.exceptioncatchers.bookfinder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.exceptioncatchers.bookfinder.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth


class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)

//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser != null){
//            val action = StartFragmentDirections.actionStartFragmentToBooksListFragment()
//            findNavController().navigate(action)
//        }
        binding.btnLogin.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.btnRegister.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
        binding.btnTest.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToBooksListFragment()
            findNavController().navigate(action)
        }

        binding.btnLocation.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToMapsFragment()
            findNavController().navigate(action)
        }

    }
}