package com.bofu.a20210421_fu_project.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.bofu.a20210421_fu_project.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    val context: Context = InstrumentationRegistry.getInstrumentation().context
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true


    @Test
    fun test_isActivityInView(){
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_elements(){
        onView(withId(R.id.main_header_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.main_recycler_view)).check(matches(isDisplayed()))

        if(isConnected){
            onView(withId(R.id.main_no_connection_center_view)).check(
                matches(
                    withEffectiveVisibility(
                        Visibility.GONE
                    )
                )
            )
        } else {
            onView(withId(R.id.main_no_connection_center_view)).check(
                matches(
                    withEffectiveVisibility(
                        Visibility.VISIBLE
                    )
                )
            )
            onView(withId(R.id.noconnection_center_btn)).check(
                matches(
                    withEffectiveVisibility(
                        Visibility.VISIBLE
                    )
                )
            ).perform(click())
        }
    }

    @Test
    fun test_isBrand_btn_checked(){
        onView(withId(R.id.filter_toggle_brand_btn)).check(matches(isChecked()))
    }

    @Test
    fun test_isText_displyed(){
        onView(withId(R.id.recycler_header_tv)).check(matches(withText(R.string.filter_by)))
    }

}