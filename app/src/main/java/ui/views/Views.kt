package ui.views

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class Views : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)

        // Enables Always-on
        setAmbientEnabled()
    }
}