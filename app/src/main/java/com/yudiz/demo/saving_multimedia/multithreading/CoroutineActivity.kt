package com.yudiz.demo.saving_multimedia.multithreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.math.BigInteger

class CoroutineActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCoroutineBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnExecuteCoroutine.setOnClickListener {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch{
                binding.tvCoroutine.text=
                    "${fact(BigInteger(binding.editTextCoroutine.text.toString()), BigInteger.ONE)}"
            }
        }
    }

     fun fact(number: BigInteger, result: BigInteger): BigInteger {
        return if (number == BigInteger.ZERO) result
        else fact(number - BigInteger.ONE, number * result)
    }
}