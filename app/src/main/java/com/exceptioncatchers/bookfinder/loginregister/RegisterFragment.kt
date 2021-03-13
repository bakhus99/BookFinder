package com.exceptioncatchers.bookfinder.loginregister

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentRegisterBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

private const val TAG = "RegisterFragment"

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private var selectedPhotoUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.ivUserImage.setOnClickListener {
            selectPhoto()
        }
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }


    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Check mail or password", Toast.LENGTH_SHORT).show()
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(context, "User registered", Toast.LENGTH_SHORT).show()
                uploadImagetoFirebase()
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImagetoFirebase() {
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$filename")
        reference.putFile(selectedPhotoUri!!)
            .addOnSuccessListener { path ->
                Log.d(TAG, "uploadImageToFirebase: Sucsses photo added")
                reference.downloadUrl.addOnSuccessListener { 
                    saveUserToFirebase(it.toString())
                }.addOnFailureListener{
                    
                }
            }

    }

    private fun saveUserToFirebase(profileImgUrl: String) {
        val name = binding.etUserName.text.toString()
        val city  = binding.etUserCity.text.toString()
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid,name,city,profileImgUrl)
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, "saveUserToFirebase: saved user to firebase")
            }

    }


    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedPhotoUri)
            binding.userCircleImage.setImageBitmap(bitmap)
            binding.ivUserImage.alpha = 0f
        }
    }


}