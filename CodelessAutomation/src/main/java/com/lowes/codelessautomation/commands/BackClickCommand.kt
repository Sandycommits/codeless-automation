/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.commands

import androidx.test.espresso.Espresso.pressBack

// Created by Sandeep on 14/07/21.
class BackClickCommand() : Command {

    override fun execute() {
        pressBack()
    }
}