package com.vj.readerpro.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vj.readerpro.R
import com.vj.readerpro.base.activity.BaseActivity
import com.vj.readerpro.data.model.TimeFrameData
import com.vj.readerpro.databinding.ActivityHomeBinding
import com.vj.readerpro.ui.adapters.GroupSMSAdapter
import com.vj.readerpro.ui.viewmodels.HomeViewModel
import com.vj.readerpro.utils.EnumConstants
import com.vj.readerpro.utils.PermissionHandler
import kotlinx.android.synthetic.main.include_rv_group_sms.view.*
import java.util.*


class HomeActivity : BaseActivity() {

    private lateinit var mHomeActivityBinding: ActivityHomeBinding
    private lateinit var mHomeViewModel: HomeViewModel

    private lateinit var groupSMSAdapter: GroupSMSAdapter

    companion object {
        //lateinit var permissionHandler: PermissionHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        mHomeActivityBinding = DataBindingUtil.setContentView(
            this@HomeActivity,
            R.layout.activity_home
        )
        mHomeViewModel = ViewModelProvider(this, vmFactory)[HomeViewModel::class.java]
        //mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        mHomeActivityBinding.homeVM = mHomeViewModel
        mHomeActivityBinding.lifecycleOwner = this@HomeActivity
    }

    override fun onStart() {
        super.onStart()
        PermissionHandler.requestSMSPermission(this@HomeActivity)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == EnumConstants.SMS_PERMISSION.ordinal) {
            //var granted = false
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    PermissionHandler.permissionReadSMS
                )
            ) {
                //granted = false
                toastNeedPermission()
            } else {
                if (PermissionHandler.permissionGranted(
                        PermissionHandler.permissionReadSMS,
                        this@HomeActivity
                    )
                ) {
                    //granted = true
                    mHomeViewModel.fetchSMSData(contentResolver)
                } else {
                    //granted = false
                    toastNeedPermission()
                }
            }
        }
    }


    /*private fun fetchSMSData(){
        CoroutineScope(Dispatchers.Default).launch {
            // Create Inbox box URI
            val inboxURI: Uri = Uri.parse("content://sms/inbox")

            // List required columns
            val reqCols = arrayOf("_id", "address", "body")

            // Get Content Resolver object, which will deal with Content Provider
            val cr = contentResolver

            // Fetch Inbox SMS Message from Built-in Content Provider
            val cursor: Cursor? = cr.query(inboxURI, reqCols, null, null, null)

            val alSMSList : ArrayList<String> = arrayListOf()

            while(cursor?.moveToNext() == true) {
                alSMSList.add(cursor.getString(cursor.getColumnIndex("address")))
            }
        }
    }*/

    private fun getAllTimeFrame(){

    }

    private fun updateGroupSMSAdapter(){
        val alTimeFrameData = arrayListOf<TimeFrameData>()
        val alSupportedTimeFrame = arrayListOf<String>(*resources.getStringArray(R.array.supported_time_frames))
        //val alTimeFrameData = arrayListOf<TimeFrameData>(*resources.getStringArray(R.array.supported_time_frames))
        alSupportedTimeFrame.forEachIndexed { index, timeFrame ->
            alTimeFrameData[index].groupTimeFrame = timeFrame
            alTimeFrameData[index].index = index
        }


        groupSMSAdapter = GroupSMSAdapter(this::onTimeFrameSelected)

        mHomeActivityBinding.includeGroupSmsLayout.root.let { binding ->
            binding.rvTimeFrame.adapter = groupSMSAdapter
            groupSMSAdapter.submitList(alTimeFrameData)
            //groupSMSAdapter.submitList(timeFrameData.TimeFrame?.sortedBy { it.index?.toInt() })
        }
    }

    private fun onTimeFrameSelected(timeFrameData: TimeFrameData) {
        groupSMSAdapter.currentList.let { list ->
            list.forEach {
                it.isSelected = (it.index == timeFrameData.index)
            }
        }
        groupSMSAdapter?.notifyDataSetChanged()
        /*groupSMSAdapter?.let {
                save(it, optionData)
            }*/
    }

    private fun toastNeedPermission() {
        Toast.makeText(
            this,
            getString(R.string.provide_sms_permission),
            Toast.LENGTH_SHORT
        ).show()
        this.finish()
    }
}