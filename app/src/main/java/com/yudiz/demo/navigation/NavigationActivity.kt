package com.yudiz.demo.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.databinding.ActivityNavigationBinding
import com.yudiz.demo.navigation.broadcast_receiver.BroadCastReceiverActivity
import com.yudiz.demo.navigation.contact_provider.ContactProviderActivity
import com.yudiz.demo.navigation.content_provider.ContentProviderActivity
import com.yudiz.demo.navigation.dialogs_demo.DialogsActivity
import com.yudiz.demo.navigation.intent.IntentOneActivity
import com.yudiz.demo.navigation.intent_filter.FilterMainActivity
import com.yudiz.demo.navigation.fragments_demo.FragmentsMainActivity
import com.yudiz.demo.navigation.fragments_demo.ViewPagerNavigationActivity
import com.yudiz.demo.navigation.notifications.NotificationsActivity
import com.yudiz.demo.navigation.permissions.PermissionsActivity
import com.yudiz.demo.services.ServicesActivity

class NavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navTopicsIntent.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    IntentOneActivity::class.java
                )
            )
        }
        binding.navTopicsIntentFilters.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FilterMainActivity::class.java
                )
            )
        }
        binding.navTopicsFragments.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FragmentsMainActivity::class.java
                )
            )
        }
        binding.navTopicsDialogs.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DialogsActivity::class.java
                )
            )
        }
        binding.navTopicsPageNavigation.setOnClickListener {
            startActivity(Intent(this, ViewPagerNavigationActivity::class.java))
        }
        binding.navTopicsPermissions.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PermissionsActivity::class.java
                )
            )
        }
        binding.navTopicsNotifications.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    NotificationsActivity::class.java
                )
            )
        }
        binding.navTopicsContentProvider.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ContentProviderActivity::class.java
                )
            )
        }
        binding.navTopicsContactProvider.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ContactProviderActivity::class.java
                )
            )
        }
        binding.navTopicsBroadcastReceiver.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BroadCastReceiverActivity::class.java
                )
            )
        }
    }
}