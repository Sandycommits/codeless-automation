/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.commands

import androidx.test.core.app.ActivityScenario
import com.lowes.codelessautomation.model.Instruction
import com.lowes.codelessautomation.utils.CommonUtils.getClass

// Created by Sandeep on 11/07/21.
class LaunchCommand(private val instruction: Instruction) : Command {

    override fun execute() {
        ActivityScenario.launch(getClass(instruction.id))
    }
}