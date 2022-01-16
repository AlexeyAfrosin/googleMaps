package com.afrosin.googlemaps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afrosin.googlemaps.R
import com.afrosin.googlemaps.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMapsBinding.inflate(layoutInflater).root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MapsFragment.newInstance())
                .commitNow()
        }
    }

}