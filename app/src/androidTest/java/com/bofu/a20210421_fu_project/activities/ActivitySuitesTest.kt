package com.bofu.a20210421_fu_project.activities

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    DetailActivityTest::class,
    DetailNestedScrollViewTest::class
)
class ActivitySuitesTest