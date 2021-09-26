/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Created by Sandeep on 26/09/21.
 */
class RecyclerViewAction(recyclerViewId: Int) {
    companion object {
        fun clickItemWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById(id) as View
                    v.performClick()
                }
            }
        }

        fun hasItemAtPosition(position: Int, matcher: Matcher<View>) : Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

                override fun describeTo(description: Description?) {
                    description?.appendText("has item at position $position : ")
                    matcher.describeTo(description)
                }

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    val viewHolder = item?.findViewHolderForAdapterPosition(position)
                    return matcher.matches(viewHolder?.itemView)
                }
            }
        }
    }
}