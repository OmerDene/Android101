package com.example.android101

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.android101.databinding.ActivityKotlinMapsBinding
import com.google.android.material.snackbar.Snackbar

class KotlinMaps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityKotlinMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private lateinit var sharedPreferences: SharedPreferences
    private var trackBoolean : Boolean? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKotlinMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        registerLauncher()
        sharedPreferences =this.getSharedPreferences("com.example.android101", MODE_PRIVATE)
        trackBoolean =false
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager =this.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object  :LocationListener{
            override fun onLocationChanged(p0: Location) {
                trackBoolean =sharedPreferences.getBoolean("trackBoolean",false)
                if(trackBoolean==false){
                    val userLocation =LatLng(p0.latitude,p0.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))
                    sharedPreferences.edit().putBoolean("trackBoolean",true).apply()

                }



            }

        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.root,"Permission needed for location",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

                }.show()

            }else{
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)


            }

        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            val lastlocation =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(lastlocation!=null){
                val userLocation =LatLng(lastlocation.latitude,lastlocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))
            }
            mMap.isMyLocationEnabled =true

        }

    }
    private fun registerLauncher(){
        permissionLauncher =registerForActivityResult(ActivityResultContracts.RequestPermission()){ result ->
            if(result){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
                    val lastlocation =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if(lastlocation!=null){
                        val userLocation =LatLng(lastlocation.latitude,lastlocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))
                    }
                    mMap.isMyLocationEnabled =true

            }else{
                Toast.makeText(this@KotlinMaps,"Needed permission",Toast.LENGTH_LONG).show()
            }

        }

    }
}
}