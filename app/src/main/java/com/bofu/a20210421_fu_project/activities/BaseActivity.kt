package com.bofu.a20210421_fu_project.activities

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bofu.a20210421_fu_project.extensions.isConnectedToNetwork

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val FIRST_LOADING = 1
        const val SCROLL_LOADING = 2
    }

    private lateinit var baseNoConnectionView: View
    private lateinit var baseBtn: Button
    private lateinit var baseAction: (Int) -> Unit
    private var baseLoadingType: Int = 0

    private fun isConnected() = this.isConnectedToNetwork()

    fun checkConnection(view:View, btn:Button, action:((Int)-> Unit), loadingType:Int){

        baseNoConnectionView = view
        baseBtn = btn
        baseAction = action
        baseLoadingType = loadingType

        baseBtn.setOnClickListener {
            checkConnection(baseNoConnectionView, baseBtn, baseAction, baseLoadingType)
        }

        when(isConnected()){
            true -> {
                noConnectionView(false)
                baseAction(loadingType)
            }
            false -> noConnectionView(true)
        }
    }

    fun noConnectionView(show: Boolean){
        when(show){
            true -> baseNoConnectionView.visibility = View.VISIBLE
            false -> baseNoConnectionView.visibility = View.GONE
        }
    }

    fun progressBar(progressBar: ProgressBar, show: Boolean){
        when(show){
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }


}