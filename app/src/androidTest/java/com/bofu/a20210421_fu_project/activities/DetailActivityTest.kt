package com.bofu.a20210421_fu_project.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bofu.a20210421_fu_project.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(DetailActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.activity_detail)).check(matches(isDisplayed()))
    }


}