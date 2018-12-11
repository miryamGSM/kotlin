/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.container

import java.lang.reflect.InvocationTargetException

inline fun <T> runWithUnwrappingInvocationException(block: () -> T) =
    try {
        block()
    } catch (e: InvocationTargetException) {
        throw e.targetException ?: e
    }
