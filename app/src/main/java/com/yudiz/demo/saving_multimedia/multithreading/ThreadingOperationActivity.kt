package com.yudiz.demo.saving_multimedia.multithreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.yudiz.demo.databinding.ActivityThreadingOperationBinding
import java.math.BigInteger

class ThreadingOperationActivity : AppCompatActivity(), Handler.Callback {
    lateinit var binding: ActivityThreadingOperationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityThreadingOperationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var resultOne: BigInteger = BigInteger.ZERO
        var resultTwo: BigInteger
        val callback = Handler(this)
        binding.btnThreadingFact.setOnClickListener {
            var threadTwo = Thread {
                resultTwo = fact(BigInteger(binding.editTextFactTwo.text.toString()), BigInteger.ONE)
                callback.sendMessage(Message().apply {
                    data =
                        Bundle().apply {
                            putString("result", (resultOne + resultTwo).toString())
                            putString("result_one",resultOne.toString())
                            putString("result_two",resultTwo.toString())
                        }
                })
            }
            val threadOne = Thread {
                resultOne = fact(BigInteger(binding.editTextFactOne.text.toString()), BigInteger.ONE)
                threadTwo.start()
            }
            threadOne.start()
        }

    }

    private tailrec fun fact(number: BigInteger, result: BigInteger): BigInteger {
        return if (number == BigInteger.ZERO) result
        else fact(number - BigInteger.ONE, number * result)
    }

    override fun handleMessage(msg: Message): Boolean {
        binding.factThreadingTv.text = msg.data.getString("result")
        binding.factThreadingTvOne.text= msg.data.getString("result_one")
        binding.factThreadingTvTwo.text=msg.data.getString("result_two")
        return true
    }
}