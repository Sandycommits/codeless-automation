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
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.lowes.codelessautomation.model.Instruction
import com.lowes.codelessautomation.utils.CommonUtils
import com.lowes.codelessautomation.utils.RecyclerViewAction.Companion.clickItemWithId

/**
 * Created by Sandeep on 27/09/21.
 */
class DescendantViewClickCommand(private val instruction: Instruction) : Command {

    override fun execute() {
        val triple = CommonUtils.getParentAndDescendantId(instruction.id)
        onView(withId(triple.first)).perform(clickItemWithId(triple.second))
    }
}