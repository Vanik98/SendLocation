package com.vanik.sendlocation.ui.home.fragments.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vanik.sendlocation.R


@Suppress("UNREACHABLE_CODE", "DEPRECATED_IDENTITY_EQUALS")
class MapFragment : Fragment(), OnMapReadyCallback{

    private lateinit var map: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var myGelolocation: MarkerOptions



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    private fun showGeolocation() {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latiude = location.latitude
                val longitude = location.longitude
                val icon = BitmapDescriptorFactory.fromResource(R.drawable.mylocation)
                myGelolocation = MarkerOptions().position(LatLng(latiude, longitude)).title("G").icon(icon)
                map.addMarker(myGelolocation)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latiude, longitude), 15.0f))
            }

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            100000,
            20f,
            locationListener
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            100000,
            20f,
            locationListener
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        showGeolocation()
    }

    override fun onMapReady(gMap: GoogleMap) {
        map = gMap
        if (ContextCompat.checkSelfPermission(
               requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) === PackageManager.PERMISSION_GRANTED
                ) {

                }
            }
        }

    }

}