package com.ssafy.cleanstore.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.databinding.FragmentMainBinding


private const val TAG = "MainFragment_싸피"
class MainFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMainBinding

    private val UPDATE_INTERVAL = 1000 // 1초
    private val FASTEST_UPDATE_INTERVAL = 500 // 0.5초

    private var mMap: GoogleMap? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var mCurrentLocation: Location
    private lateinit var currentPosition: LatLng
    private var storeMarker: Marker? = null

    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL.toLong()
            smallestDisplacement = 10.0f
            fastestInterval = FASTEST_UPDATE_INTERVAL.toLong()
        }

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.container_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        if (checkPermission()) {
            startLocationUpdates()
        }
    }

    override fun onStop() {
        super.onStop()
        // 위치 찾는 것을 멈춤
        mFusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        if (checkPermission()) { // 1. 위치 퍼미션을 가지고 있는지 확인
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식)
            startLocationUpdates() // 3. 위치 업데이트 시작

        } else {  //2. 권한이 없다면
            // 3-1. 사용자가 권한이 없는 경우에는
            val permissionListener = object : PermissionListener {
                // 권한 얻기에 성공했을 때 동작 처리
                override fun onPermissionGranted() {
                    startLocationUpdates()
                }

                // 권한 얻기에 실패했을 때 동작 처리
                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(
                        ctx,
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

        // 상점 위치 마커 표시 (메가박스 구미 근처)
        val storeLatLng = LatLng(36.10830144233874, 128.41827450414362)

        val bitmapdraw = ResourcesCompat.getDrawable(resources, R.drawable.location_icon, ctx.theme) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val marker = Bitmap.createScaledBitmap(b, 100, 100, false)

        val markerOptions = MarkerOptions()
        markerOptions.position(storeLatLng)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(marker))
        markerOptions.draggable(true)

        storeMarker = mMap!!.addMarker(markerOptions)
    }

    fun setCurrentLocation(location: Location) {
        val currentLatLng = LatLng(location.latitude, location.longitude)

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f)
        mMap!!.animateCamera(cameraUpdate)
    }

    // 현재위치 찾아서 마커찍는 함수
    private fun startLocationUpdates() {
        if (checkPermission()) {
            mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()!!
            )
            // 현재 위치 찾는 버튼 활성화
            if (mMap != null) mMap!!.isMyLocationEnabled = true
            // +.- 줌 버튼 활성화
            if (mMap != null) mMap!!.uiSettings.isZoomControlsEnabled = true
        }
    }

    // 콜백 함수
    var locationCallback: LocationCallback = object : LocationCallback() {
        // 위치가 바뀔때마다, 일정 시간마다 호출됨
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            // 위치 정보들이 리스트에 담김
            val locationList = locationResult.locations
            if (locationList.size > 0) {
                // 리스트에서 가장 최근 위치 가져옴
                val location = locationList[locationList.size - 1]
                currentPosition = LatLng(location.latitude, location.longitude)

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location)
                mCurrentLocation = location

            }
        }
    }

    private fun checkPermission(): Boolean {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
    }
}