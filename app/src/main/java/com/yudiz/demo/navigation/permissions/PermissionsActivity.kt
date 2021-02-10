package com.yudiz.demo.navigation.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.os.CancellationSignal
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityPermissionsBinding
import java.util.*
import java.util.function.Consumer


class PermissionsActivity : AppCompatActivity(), View.OnClickListener, LocationListener {
    lateinit var binding: ActivityPermissionsBinding
    private val locationCode = 10
    private val cameraCode = 9
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPermissionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAddImage.setOnClickListener(this)
        binding.btnAddAddress.setOnClickListener(this)
    }

    @SuppressLint("MissingPermission")
    override fun onClick(v: View?) {
        when (v) {
            binding.btnAddAddress -> {
                if (checkSelf(Manifest.permission.ACCESS_FINE_LOCATION) && checkSelf(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(this, "Accessing Location", Toast.LENGTH_SHORT).show()
                    val manager = (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
                    if (manager.isLocationEnabled) {
                        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000.toLong(),
                            5.toFloat(),this)
                    } else {
                        Toast.makeText(this, getString(R.string.enable_location), Toast.LENGTH_LONG)
                            .show()
                        val viewIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(viewIntent)
                    }

                } else {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ), locationCode
                    )
                }
            }
            binding.btnAddImage -> {
                if (checkSelf(Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show()
                } else {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.CAMERA
                        ), cameraCode
                    )
                }
            }
        }
    }

    private fun checkSelf(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            locationCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    if (manager.isLocationEnabled) {
                        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000.toLong(),
                            5.toFloat(),this)
                    } else {
                        Toast.makeText(this, getString(R.string.enable_location), Toast.LENGTH_LONG)
                            .show()
                        val viewIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(viewIntent)
                    }
                } else {
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Attention!")
                        setIcon(R.drawable.ic_warning)
                        setMessage(getString(R.string.location_message_settings))
                        setNegativeButton(getString(R.string.ok), null)
                        show()
                    }
                }
            }
            cameraCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Attention!")
                        setIcon(R.drawable.ic_warning)
                        setMessage(getString(R.string.camera_message_settings))
                        setNegativeButton(getString(R.string.ok), null)
                        show()
                    }
                }
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        /*Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        val address = Geocoder(this, Locale.getDefault()).getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        binding.addressTv.text = address[0].apply {
            countryName
            adminArea
            subAdminArea
            subLocality
            postalCode

        }.toString()*/
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        binding.addressTv.text=location.apply {
            latitude
            longitude
        }.toString()
    }

}