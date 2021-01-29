package ui.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.R

class CustomLayout : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_layout)
    }
}