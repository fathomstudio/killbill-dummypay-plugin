/*
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014-2015 Groupon, Inc
 * Copyright 2014-2015 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
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

import org.killbill.billing.osgi.api.OSGIPluginProperties;
import org.killbill.billing.osgi.libs.killbill.KillbillActivatorBase;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillEventDispatcher;
import org.killbill.billing.payment.plugin.api.PaymentPluginApi;
import org.osgi.framework.BundleContext;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.util.Hashtable;

/**
 * This is the plugin entrance point.
 */
public class DummyPayActivator extends KillbillActivatorBase {
	//
	// Ideally that string should match the pluginName on the filesystem, but there is no enforcement
	//
	public static final String PLUGIN_NAME = "killbill-dummypay-plugin";
	
	private OSGIKillbillEventDispatcher.OSGIKillbillEventHandler killbillEventHandler;
	
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		
		// Register an event listener (optional)
		killbillEventHandler = new DummyPayListener(logService, killbillAPI, dataSource);
		dispatcher.registerEventHandlers(killbillEventHandler);
		
		// As an example, this plugin registers a PaymentPluginApi (this could be changed to any other plugin api)
		final PaymentPluginApi paymentPluginApi = new DummyPayPaymentPluginApi(configProperties.getProperties(), logService, killbillAPI, dataSource);
		registerPaymentPluginApi(context, paymentPluginApi);
		
		// Register a servlet (optional)
		final DummyPayServlet analyticsServlet = new DummyPayServlet(logService);
		registerServlet(context, analyticsServlet);
	}
	
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		// Do additional work on shutdown (optional)
	}
	
	private void registerServlet(final BundleContext context, final HttpServlet servlet) {
		final Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
		registrar.registerService(context, Servlet.class, servlet, props);
	}
	
	private void registerPaymentPluginApi(final BundleContext context, final PaymentPluginApi api) {
		final Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
		registrar.registerService(context, PaymentPluginApi.class, api, props);
	}
}
