package com.yudiz.demo.navigation.DialogsDemo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityDialogsBinding
import com.yudiz.demo.databinding.BottomSheetLayoutBinding
import com.yudiz.demo.databinding.CustomLayoutBinding

class DialogsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityDialogsBinding
    lateinit var bottomsheet: BottomSheetDialog
    lateinit var sheetBinding: BottomSheetLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDialogsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setTitle("You want to exit?")
                setIcon(R.drawable.ic_clear_black_48)
                setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> finish() })
                setNegativeButton("No", null)
                show()
            }
        }
        binding.btnBottomSheet.setOnClickListener(this)
        createBottomSheet()
    }

    private fun createBottomSheet() {
        sheetBinding = BottomSheetLayoutBinding.inflate(layoutInflater, null, false)
        bottomsheet = BottomSheetDialog(this)
        bottomsheet.setContentView(sheetBinding.root)
        sheetBinding.sheetItemOne.setOnClickListener(this)
        sheetBinding.sheetItemTwo.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            sheetBinding.sheetItemOne -> {
                Toast.makeText(this, "Clicked on cancel", Toast.LENGTH_SHORT).show()
                bottomsheet.dismiss()
            }
            sheetBinding.sheetItemTwo -> {
                Toast.makeText(this, "Clicked on Done", Toast.LENGTH_SHORT).show()
                bottomsheet.dismiss()
            }
            binding.btnBottomSheet -> bottomsheet.show()
        }
    }
}