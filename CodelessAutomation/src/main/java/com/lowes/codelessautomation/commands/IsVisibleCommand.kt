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
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.lowes.codelessautomation.model.Instruction
import com.lowes.codelessautomation.utils.CommonUtils.getId
import java.lang.Thread.sleep

// Created by Sandeep on 14/07/21.
class IsVisibleCommand(private val instruction: Instruction) : Command {

    override fun execute() {
        sleep(1500L)
        onView(ViewMatchers.withId(getId(instruction.id)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}