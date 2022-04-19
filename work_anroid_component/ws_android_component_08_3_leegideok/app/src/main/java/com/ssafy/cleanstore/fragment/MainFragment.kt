package com.ssafy.cleanstore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.databinding.FragmentMainBinding

class MainFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this@MainFragment)
        }.root
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val store = LatLng(36.10830144233874, 128.41827450414362)
        mMap!!.addMarker(MarkerOptions().position(store).title("싸피벅스"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(store))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(16f))
    }
}