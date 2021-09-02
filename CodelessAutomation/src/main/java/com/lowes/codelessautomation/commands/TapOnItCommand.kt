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
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.lowes.codelessautomation.model.Instruction
import com.lowes.codelessautomation.utils.CommonUtils.getId

// Created by Sandeep on 14/07/21.
class TapOnItCommand(private val instruction: Instruction) : Command {

    override fun execute() {
        onView(withId(getId(instruction.id)))
            .perform(click())
    }
}