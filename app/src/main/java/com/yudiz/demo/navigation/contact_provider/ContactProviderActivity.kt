package com.yudiz.demo.navigation.contact_provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.database.Cursor
import android.provider.ContactsContract
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityContactProviderBinding
import com.yudiz.demo.navigation.contact_provider.models.ContactModel

class ContactProviderActivity : AppCompatActivity() {
    private val contactCode = 1
    lateinit var nameCursor: Cursor
    lateinit var binding: ActivityContactProviderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactProviderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (checkSelf(Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(this, "reading contacts", Toast.LENGTH_SHORT).show()
            contentResolver.query(
                ContactsContract.CommonDataKinds.Contactables.CONTENT_URI,
                null,
                null,
                null,
                null,
                null
            )?.also { nameCursor = it }
            val contactList = ArrayList<ContactModel>()
            nameCursor.moveToFirst()
            var id: String
            lateinit var emailCursor: Cursor
            var email = ""
            nameCursor.moveToFirst()
            while (nameCursor.moveToNext()) {
                id =
                    nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.CONTACT_ID))
                contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = $id ", null, null
                )?.also { emailCursor = it }
                if (emailCursor.moveToFirst()) {
                    email =
                        emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                    emailCursor.close()
                } else {
                    email = "No Email Id is Assigned to this Contact"
                }
                contactList.add(
                    ContactModel(
                        nameCursor.getString(
                            nameCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Contactables.DISPLAY_NAME
                            )
                        ), email
                    )
                )
            }
            nameCursor.close()
            binding.contactsRv.adapter = RecyclerContactsAdapter(contactList)
            binding.contactsRv.layoutManager = LinearLayoutManager(this)
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_CONTACTS
                ), contactCode
            )
        }
    }

    private fun checkSelf(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            contactCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "read contacts permission granted", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Attention!")
                        setIcon(R.drawable.ic_warning)
                        setMessage(getString(R.string.contacts_message_settings))
                        setNegativeButton(getString(R.string.ok), null)
                        show()
                    }
                }
            }
        }
    }
}