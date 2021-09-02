/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.commands

import com.lowes.codelessautomation.model.Action
import com.lowes.codelessautomation.model.Instruction

// Created by Sandeep on 02/08/21.
class WaitCommand(private val instruction: Instruction) : Command {

    override fun execute() {
        when (instruction.action) {
            Action.WAIT_LESS.action -> Thread.sleep(500)
            Action.WAIT_MORE.action -> Thread.sleep(2000)
        }
    }
}