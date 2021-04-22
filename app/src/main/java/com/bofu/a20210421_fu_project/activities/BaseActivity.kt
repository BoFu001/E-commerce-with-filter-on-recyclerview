package com.bofu.a20210421_fu_project.activities

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bofu.a20210421_fu_project.extensions.isConnectedToNetwork
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var baseAction: () -> Unit
    private lateinit var baseBtn: Button
    private fun isConnected() = this.isConnectedToNetwork()

    override fun onStart() {
        super.onStart()
        retryBtnSetup()
    }

    private fun retryBtnSetup(){
        baseBtn.setOnClickListener {
            checkConnection(baseBtn, baseAction)
        }
    }

    fun checkConnection(btn:Button, action: (()-> Unit)){

        baseBtn = btn
        baseAction = action

        if(isConnected()){
            no_connection_view.visibility = View.GONE
            baseAction()
        } else {
            no_connection_view.visibility = View.VISIBLE
        }
    }
}