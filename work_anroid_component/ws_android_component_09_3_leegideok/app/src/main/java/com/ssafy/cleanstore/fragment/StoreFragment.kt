package com.ssafy.cleanstore.fragment

import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.cleanstore.databinding.FragmentStoreBinding
import com.ssafy.cleanstore.stuff.StuffActivity

private const val TAG = "StoreFragment_싸피"

class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        binding.tvStoreName.text = "싸피벅스"
        binding.tvStoreTel.text = "010-1234-5678"
        binding.tvStoreLat.text = "36.10830144233874"
        binding.tvStoreLng.text = "128.41827450414362"


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding.tvStoreName.parent as View).setOnClickListener {
            Intent(context, StuffActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.btnRegisterContact.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission(){
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                val storeName = binding.tvStoreName.text.toString()
                val storeTel = binding.tvStoreTel.text.toString()
                addContacts(storeName, storeTel)
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(requireContext(),
                    "연락처 접근 권한을 허가해주세요",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(android.Manifest.permission.WRITE_CONTACTS)
            .check()
    }

    /**
     * 아래 사이트 내용을 읽고, addContacts 코드 내용을 분석해보세요.
     * https://developer.android.com/guide/topics/providers/contacts-provider
     */
    // Contact(사람 한명) - RawContact(계정 정보) - Data
    private fun addContacts(storeName: String, storeTel: String) {

        // 계정을 하나 만듦
        val p = ContentValues()
        p.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.google")
        p.put(ContactsContract.RawContacts.ACCOUNT_NAME, "ssafy")
        // id 값 포함된 uri 리턴 받음음
       val rowContact = requireContext().contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, p)
        val rawContactId = ContentUris.parseId(rowContact!!)

        // 연락처의 이름 지정
        val value = ContentValues()
        value.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        value.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        value.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, storeName)
        requireContext().contentResolver.insert(ContactsContract.Data.CONTENT_URI, value)

        // 연락처의 전화번호 지정
        val ppv = ContentValues()
        ppv.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        ppv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        ppv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, storeTel)
        ppv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
        requireContext().contentResolver.insert(ContactsContract.Data.CONTENT_URI, ppv)

        Toast.makeText(requireContext(),"$storeName 연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }

}