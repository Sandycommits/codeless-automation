/*
 * Lowe's Companies Inc., Android Application
 * Copyright (C) 2021 Lowe's Companies Inc.
 *
 * The Lowe's Application is the private property of
 * Lowe's Companies Inc. Any distribution of this software
 * is unlawful and prohibited.
 */
package com.lowes.codelessautomation.model

// Created by Sandeep on 11/07/21.
enum class Action(val action: String) {
    LAUNCH("launch"),
    IS_VISIBLE("isVisible"),
    TAP_ON_IT("tapOnIt"),
    REPLACE_TEXT("replaceText"),
    BACK_CLICK("backButtonClick"),
    MATCHES_WITH("matchesWith"),
    CONTAINS_TEXT("containsText"),
    PERFORM_CLICK("performClick"),
    WAIT_MORE("waitMore"),
    WAIT_LESS("waitLess"),
}