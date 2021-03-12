package com.exceptioncatchers.bookfinder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.exceptioncatchers.bookfinder.databinding.FragmentStartBinding


class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)

        binding.test.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToBooksListFragment()
            findNavController().navigate(action)
        }
    }


}