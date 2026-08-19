/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2026 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.undertow.websockets.jsr.annotated;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

@SuppressWarnings("removal")
class SecurityActions {

    private SecurityActions() {
        // no instances
    }

    static Method[] getDeclaredMethods(final Class<?> clazz) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredMethods();
        }
        return AccessController.doPrivileged((PrivilegedAction<Method[]>) clazz::getDeclaredMethods);
    }

    static void setAccessible(final Method method, final boolean flag) {
        if (System.getSecurityManager() == null) {
            method.setAccessible(flag);
            return;
        }

        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            method.setAccessible(flag);
            return null;
        });
    }
}
