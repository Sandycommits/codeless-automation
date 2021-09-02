/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.main

import com.lowes.codelessautomation.commands.Command

/**
 * Created by Sandeep on 31/08/21.
 */
class InstructionExecutor {

    val queue = ArrayList<Command>()

    fun addToQueue(command: Command): InstructionExecutor = apply {
        queue.add(command)
    }

    fun executeInstructions(): InstructionExecutor = apply {
        queue.forEach { it.execute() }
    }
}