package com.vj.readerpro.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vj.readerpro.R
import com.vj.readerpro.databinding.ActivityHomeBinding
import com.vj.readerpro.ui.viewmodels.HomeViewModel
import com.vj.readerpro.utils.PermissionHandler

class HomeActivity : AppCompatActivity() {

    private lateinit var mHomeActivityBinding: ActivityHomeBinding
    private lateinit var mHomeViewModel: HomeViewModel

    companion object {
        lateinit var permissionHandler: PermissionHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}