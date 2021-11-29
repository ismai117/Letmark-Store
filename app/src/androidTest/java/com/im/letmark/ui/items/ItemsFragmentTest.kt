package com.im.letmark.ui.items

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.im.letmark.R
import com.im.letmark.ui.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemsFragmentTest {


    @get: Rule
    val activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_if_item_coordinatorLayout_Exists() {
        onView(withId(R.id.item_coordinatorLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_if_item_constraintLayout_Exists() {
        onView(withId(R.id.item_constraintLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_if_item_recyclerView_Exists() {
        onView(withId(R.id.items_recycler_view)).check(matches(isDisplayed()))
    }

//
//    @Test
//    fun test_detailFragment() {
//
//
//        onView(withId(R.id.detail_constraintLayout)).check(matches(isDisplayed()))
//        onView(withId(R.id.detail_itemName)).check(matches(isDisplayed()))
//        onView(withId(R.id.detail_itemPrice)).check(matches(isDisplayed()))
//        onView(withId(R.id.detail_itemDescription)).check(matches(isDisplayed()))
//        onView(withId(R.id.add_to_cartBtN)).check(matches(isDisplayed()))
//
//
//    }

}