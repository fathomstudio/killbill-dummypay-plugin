/*
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014 The Billing Project, LLC
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.fathomstudio.killbilldummypayplugin;

import org.osgi.service.log.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This allows direct communication to the plugin from a caller.
 */
public class DummyPayServlet extends HttpServlet {
	
	private final LogService logService;
	
	public DummyPayServlet(final LogService logService) {
		this.logService = logService;
	}
	
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		// find me on http://killbill:8080/plugins/killbill-dummypay-plugin
		logService.log(LogService.LOG_INFO, "Hello, world!");
		resp.getOutputStream().print("Hello, world!");
	}
}
