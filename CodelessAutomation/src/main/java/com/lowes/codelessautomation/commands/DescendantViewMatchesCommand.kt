/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.commands

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.lowes.codelessautomation.model.Instruction
import com.lowes.codelessautomation.utils.CommonUtils.getParentAndDescendantId
import com.lowes.codelessautomation.utils.RecyclerViewAction.Companion.hasItemAtPosition

/**
 * Created by Sandeep on 26/09/21.
 */
class DescendantViewMatchesCommand(private val instruction: Instruction) : Command {
    override fun execute() {
        val triple = getParentAndDescendantId(instruction.id)
        onView(withId(triple.first)).check(
            matches(
                hasItemAtPosition(
                    triple.third, triple.second
                    withText(instruction.text)
                )
            )
        )
    }
}