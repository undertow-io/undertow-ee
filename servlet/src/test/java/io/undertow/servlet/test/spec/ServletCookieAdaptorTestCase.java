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

package io.undertow.servlet.test.spec;

import jakarta.servlet.http.Cookie;

import io.undertow.servlet.spec.ServletCookieAdaptor;

import org.junit.Assert;
import org.junit.Test;

public class ServletCookieAdaptorTestCase {

    @Test
    public void testSameSiteFromWrappedCookie() {
        Cookie cookie = new Cookie("test", "value");
        cookie.setAttribute("SameSite", "Lax");

        Assert.assertTrue(new ServletCookieAdaptor(cookie).isSameSite());
    }

    @Test
    public void testSetAttributeUpdatesSameSite() {
        ServletCookieAdaptor adaptor = new ServletCookieAdaptor(new Cookie("test", "value"));

        adaptor.setAttribute("SameSite", "Strict");
        Assert.assertTrue(adaptor.isSameSite());

        adaptor.setAttribute("SameSite", null);
        Assert.assertFalse(adaptor.isSameSite());
    }
}
