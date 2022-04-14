package com.ssafy.comp_05

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.comp_05.databinding.ActivityContactsBinding
import java.io.InputStream

data class ContactsDto (val id: Long, val name: String, val number: String, val photo: Bitmap?)

private const val TAG = "ContactsActivity_싸피"
class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private val contactsList = mutableListOf<ContactsDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                initData()
                initView()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(this@ContactsActivity,
                    "연락처 접근 권한을 허가해주세요",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(Manifest.permission.READ_CONTACTS)
            .check()
    }

    private fun initData() {
        getContracts().use {
            if (it.moveToFirst()) {
                Log.d(TAG, "initData: getContracts...")
                do {
                    val id = it.getLong(it.getColumnIndex(ContactsContract.Contacts._ID))
                    val photoId = it.getLong(it.getColumnIndex(ContactsContract.Contacts.PHOTO_ID))
                    val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number = getPhoneNumber(id.toString())

                    var photo: Bitmap? = null
                    Log.d(TAG, "getPhoto: $photoId")

                    if(photoId != 0L) {
                        photo = getPhoto(id)
                    }

                    Log.d(TAG, "id:$id, name:$name, number:$number, photo:$photo \n")

                    contactsList.add(ContactsDto(id, name, number, photo))

                } while(it.moveToNext())
            }
        }
    }

    private fun getContracts(): Cursor {
        var queryUri = ContactsContract.Contacts.CONTENT_URI

        // 정렬
        var sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        // 가져올 컬럼명
        val what = arrayOf(
            ContactsContract.Contacts._ID ,
            ContactsContract.Contacts.PHOTO_ID ,
            ContactsContract.Contacts.DISPLAY_NAME
        )
        return contentResolver.query(queryUri, what, null, null, sortOrder)!!
    }

    private fun getPhoneNumber(id: String): String {
        var result = ""

        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ",
            arrayOf(id),
            null
        )?.use {
            if (it.moveToFirst()) {
                result = it.getString(0)
            }
        }
        return result
    }

    fun getPhoto(contactId: Long?): Bitmap? {
        val contactPhotoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId!!)
        val photoDataStream: InputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, contactPhotoUri)
        return BitmapFactory.decodeStream(photoDataStream)
    }

    private fun initView() {
        var contactsAdapter = ContactsAdapter(this, contactsList)

        binding.recyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@ContactsActivity)
        }
    }
}

class ContactsAdapter(private val context: Context, private val contactsList: MutableList<ContactsDto>)
    : RecyclerView.Adapter<ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.contact_item, parent, false)

        return ContactsViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val number = contactsList[layoutPosition].number
                if (number != "") {
                    val intent = Intent(ACTION_DIAL, Uri.parse("tel:$number"))
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount(): Int = contactsList.size
}

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById(R.id.name) as TextView
    private val number = itemView.findViewById(R.id.number) as TextView
    private val imageView = itemView.findViewById(R.id.imageView) as ImageView

    fun bind(data: ContactsDto) {
        name.text = data.name
        number.text = data.number

        if (data.photo != null) {
            imageView.setImageBitmap(data.photo)
        }
    }
}