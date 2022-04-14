package com.ssafy.comp_06

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.comp_06.databinding.ActivityGpsNetworkBinding

private const val TAG = "GpsNetworkActivity_싸피"
class GpsNetworkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGpsNetworkBinding

    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGpsNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                getLastLocation()
                getProviders()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    this@GpsNetworkActivity,
                    "위치 권한이 거부되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("[설정] 에서 위치 접근 권한을 부여해야만 사용이 가능합니다.")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        locationManager
            .getLastKnownLocation(LocationManager.GPS_PROVIDER)
            .apply {
                if (this != null) {
                    binding.tvGpsLatitude.text = ":: $latitude"
                    binding.tvGpsLongitude.text = ":: $longitude"
                    Log.d(TAG, "GPS: 위도=$latitude, 경도=$longitude")
                }
            }

        locationManager
            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            .apply {
                if (this != null) {
                    binding.tvNetworkLatitude.text = ":: $latitude"
                    binding.tvNetworkLongitude.text = ":: $longitude"
                    Log.d(TAG, "Network: 위도=$latitude, 경도=$longitude")
                }
            }

        locationManager
            .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            .apply {
                if (this != null) {
                    binding.tvPassiveLatitude.text = ":: $latitude"
                    binding.tvPassivekLongitude.text = ":: $longitude"
                    Log.d(TAG, "Passive: 위도=$latitude, 경도=$longitude")
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun getProviders() {
        val providerList = locationManager.allProviders as MutableList<String>
        val isEnable = BooleanArray(3)
        for (provider in providerList) {
            when (provider) {
                LocationManager.GPS_PROVIDER -> {
                    isEnable[0] = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    binding.tvGpsEnable.text = ":: " + isEnable[0].toString()
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0f,
                        listener
                    )
                    Log.d(TAG, provider + '/' + isEnable[0].toString())
                }
                LocationManager.NETWORK_PROVIDER -> {
                    isEnable[1] = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                    binding.tvNetworkEnable.text = ":: " + isEnable[1].toString()
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0,
                        0f,
                        listener
                    )
                    Log.d(TAG, provider + '/' + isEnable[1].toString())
                }
                LocationManager.PASSIVE_PROVIDER -> {
                    isEnable[2] = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
                    binding.tvPassiveEnable.text = ":: " + isEnable[2].toString()
                    locationManager.requestLocationUpdates(
                        LocationManager.PASSIVE_PROVIDER,
                        0,
                        0f,
                        listener
                    )
                    Log.d(TAG, provider + '/' + isEnable[2].toString())
                }
            }
        }
    }

    private val listener = object : LocationListener {

        // 위치가 변경될때 호출될 method
        override fun onLocationChanged(location: Location) {
            when (location.provider) {
                LocationManager.GPS_PROVIDER -> {
                    binding.tvGpsLatitude.text = ": ${location.latitude}"
                    binding.tvGpsLongitude.text = ": ${location.longitude}"
                    Log.d(TAG, "onLocationChanged: GPS: ${location.latitude} / ${location.longitude}")
                }
                LocationManager.NETWORK_PROVIDER -> {
                    binding.tvNetworkLatitude.text = ": ${location.latitude}"
                    binding.tvNetworkLongitude.text = ": ${location.longitude}"
                    Log.d(TAG, "onLocationChanged: Network: ${location.latitude} / ${location.longitude}")
                }
                LocationManager.PASSIVE_PROVIDER -> {
                    binding.tvPassiveLatitude.text = ": ${location.latitude}"
                    binding.tvPassivekLongitude.text = ": ${location.longitude}"
                    Log.d(TAG, "onLocationChanged: Passive: ${location.latitude} / ${location.longitude}")
                }
            }
        }

        override fun onProviderDisabled(provider: String) {
            //super.onProviderDisabled(provider)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("MissingPermission")
        override fun onProviderEnabled(provider: String) {
            if (isPermitted()) {
                locationManager.also {
                    it.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 0, 0f, this)
                    it.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 0, 0f, this)
                    it.requestLocationUpdates(
                        LocationManager.PASSIVE_PROVIDER, 0, 0f, this)
                }
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if (isPermitted()) {
            locationManager.also {
                it.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0f, listener)
                it.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0f, listener)
                it.requestLocationUpdates(
                    LocationManager.PASSIVE_PROVIDER, 0, 0f, listener)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        super.onPause()
        if (isPermitted()) {
            locationManager.removeUpdates(listener)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isPermitted(): Boolean {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}