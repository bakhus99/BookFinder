package com.exceptioncatchers.bookfinder.userLocation

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.databinding.FragmentLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

class LocationFragment : Fragment(R.layout.fragment_location) {

    private lateinit var binding: FragmentLocationBinding
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var PERMISSION_ID = 1000

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)
        //mb nado activty
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.btnGetUserLocation.setOnClickListener {
//            RequestPermission()
//            getLastLocation()
            fetchLocations()
        }
    }


    private fun fetchLocations() {

        val task = fusedLocationProviderClient.lastLocation


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 111)
            return
        }
        task.addOnSuccessListener {
            if (it!= null){
                binding.tvUserLocation.text = "Latitude: ${it.latitude}, Longitude: ${it.longitude}"
            }
        }

    }


//    @RequiresApi(Build.VERSION_CODES.M)
//    fun getLastLocation() {
//        if (CheckPermission()) {
//            if (isLocationEnabled()) {
//                if (ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return
//                }
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//                    var location: Location? = task.result
//                    if (location == null) {
//                        NewLocationData()
//                    } else {
//                        Log.d("Debug:", "Your Location:" + location.longitude)
//                        binding.tvUserLocation.text =
//                            "You Current Location is : Long: " + location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(
//                                location.latitude,
//                                location.longitude
//                            )
//                    }
//                }
//            } else {
//                Toast.makeText(context, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        } else {
//            RequestPermission()
//        }
//    }
//
//    fun NewLocationData() {
//        var locationRequest = LocationRequest()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 0
//        locationRequest.fastestInterval = 0
//        locationRequest.numUpdates = 1
//        //mb activty
//        fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(requireActivity())
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest, locationCallback, Looper.myLooper()
//        )
//    }
//
//
//    private val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            var lastLocation: Location = locationResult.lastLocation
//            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
//            binding.tvUserLocation.text =
//                "You Last Location is : Long: " + lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(
//                    lastLocation.latitude,
//                    lastLocation.longitude
//                )
//        }
//    }
//
//    private fun getCityName(lat: Double, long: Double): String {
//        var cityName: String = ""
//        var countryName = ""
//        var geoCoder = Geocoder(requireContext(), Locale.getDefault())
//        var Adress = geoCoder.getFromLocation(lat, long, 3)
//
//        cityName = Adress.get(0).locality
//        countryName = Adress.get(0).countryName
//        Log.d("Debug:", "Your City: " + cityName + " ; your Country " + countryName)
//        return cityName
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun CheckPermission(): Boolean {
//        //this function will return a boolean
//        //true: if we have permission
//        //false if not
//        if (
//            ActivityCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED ||
//            ActivityCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            return true
//        }
//
//        return false
//    }
//
//    fun RequestPermission() {
//        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
//        ActivityCompat.requestPermissions(
//            (activity as MainActivity),
//            arrayOf(
//                android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ),
//            PERMISSION_ID
//        )
//    }
//
//    fun isLocationEnabled(): Boolean {
//        //this function will return to us the state of the location service
//        //if the gps or the network provider is enabled then it will return true otherwise it will return false
//        var locationManager =
//            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == PERMISSION_ID) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.d("TAG", "onRequestPermissionsResult: Yiu have permissions  ")
//            }
//        }
//    }


}