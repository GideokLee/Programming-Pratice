package com.ssafy.control

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class LeDeviceListAdapter(context: Context?) : BaseAdapter() {

    private val mLeDevices: ArrayList<BluetoothDevice>

    private val mInflator: LayoutInflater

    fun addDevice(device: BluetoothDevice) {
        if (!mLeDevices.contains(device)) {
            mLeDevices.add(device)
        }
    }

    fun getDevice(position: Int): BluetoothDevice {
        return mLeDevices[position]
    }

    fun clear() {
        mLeDevices.clear()
    }

    override fun getCount(): Int {
        return mLeDevices.size
    }

    override fun getItem(i: Int): Any {
        return mLeDevices[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        //Log.e("BLECONTROL", "getVeiw called....1")
        var view = view
        val viewHolder: ViewHolder
        //Log.e("BLECONTROL", "getVeiw called....2")
        // General ListView optimization code.
        if (view == null) {
            view = mInflator.inflate(R.layout.devicelistitem, null)
            viewHolder = ViewHolder()
            viewHolder.deviceAddress = view.findViewById<View>(R.id.device_address) as TextView
            viewHolder.deviceName = view.findViewById<View>(R.id.device_name) as TextView
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        val device = mLeDevices[i]
        val deviceName = device.name
        if (deviceName != null && deviceName.length > 0) {
            viewHolder.deviceName!!.text = deviceName
            viewHolder.deviceAddress!!.text = device.address
        } else viewHolder.deviceName!!.text = "unknown_device"
        //Log.e("BLECONTROL", "view : ${view}")
        return view
    }

    internal class ViewHolder {
        var deviceName: TextView? = null
        var deviceAddress: TextView? = null
    }

    init {
        mLeDevices = ArrayList()
        mInflator = LayoutInflater.from(context)
    }
}