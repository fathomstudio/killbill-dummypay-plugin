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

import org.killbill.billing.account.api.Account;
import org.killbill.billing.account.api.AccountApiException;
import org.killbill.billing.notification.plugin.api.ExtBusEvent;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillDataSource;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillEventDispatcher;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillLogService;
import org.killbill.billing.plugin.api.PluginTenantContext;
import org.killbill.billing.plugin.api.notification.PluginConfigurationEventHandler;
import org.killbill.billing.plugin.api.notification.PluginConfigurationHandler;
import org.osgi.service.log.LogService;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Kill Bill events are listened to and handled here.
 */
public class DummyPayListener extends PluginConfigurationEventHandler implements OSGIKillbillEventDispatcher.OSGIKillbillEventHandler {
	
	private final LogService      logService;
	private final OSGIKillbillAPI osgiKillbillAPI;
	
	public DummyPayListener(final OSGIKillbillLogService logService, final OSGIKillbillAPI killbillAPI, final OSGIKillbillDataSource dataSource) {
		super(new DummyPayPluginConfigurationHandler(DummyPayActivator.PLUGIN_NAME, killbillAPI, logService, dataSource));
		this.logService = logService;
		this.osgiKillbillAPI = killbillAPI;
	}
	
	@Override
	public void handleKillbillEvent(final ExtBusEvent killbillEvent) {
		switch (killbillEvent.getEventType()) {
			//
			// Calls java plugin framework PluginConfigurationEventHandler to handle TENANT_CONFIG_CHANGE/TENANT_CONFIG_DELETION events
			//
			case TENANT_CONFIG_CHANGE:
			case TENANT_CONFIG_DELETION:
				super.handleKillbillEvent(killbillEvent);
				break;
			
			//
			// Handle ACCOUNT_CREATION and ACCOUNT_CHANGE (only) for demo purpose and just print the account
			//
			case ACCOUNT_CREATION:
			case ACCOUNT_CHANGE:
				try {
					final Account account = osgiKillbillAPI.getAccountUserApi().getAccountById(killbillEvent.getAccountId(), new PluginTenantContext(killbillEvent.getTenantId()));
					logService.log(LogService.LOG_INFO, "Account information: " + account);
				} catch (final AccountApiException e) {
					logService.log(LogService.LOG_WARNING, "Unable to find account", e);
				}
				break;
			
			// Nothing
			default:
				break;
		}
	}
	
	//
	// The goal is just to show-case that when per-tenant config changes are made, the plugin automatically gets notified (and prints a log trace)
	//
	// curl \
	// -X POST \
	// -u admin:password \
	// -H "Accept: application/json" \
	// -H "Content-Type: text/plain" \
	// -H "X-Killbill-ApiKey: bob" \
	// -H "X-Killbill-ApiSecret: lazar" \
	// -H "Cache-Control: no-cache"  \
	// -d 'key1=foo1 key2=foo2'
	// "http://127.0.0.1:8080/1.0/kb/tenants/uploadPluginConfig/hello-world-plugin"
	//
	private static class DummyPayPluginConfigurationHandler extends PluginConfigurationHandler {
		
		private final LogService             logService;
		private final OSGIKillbillDataSource dataSource;
		
		public DummyPayPluginConfigurationHandler(String pluginName, OSGIKillbillAPI osgiKillbillAPI, OSGIKillbillLogService osgiKillbillLogService, OSGIKillbillDataSource dataSource) {
			super(pluginName, osgiKillbillAPI, osgiKillbillLogService);
			this.logService = osgiKillbillLogService;
			this.dataSource = dataSource;
		}
		
		@Override
		protected void configure(@Nullable UUID kbTenantId) {
			if (kbTenantId == null) {
				return;
			}
			final String properties = getTenantConfigurationAsString(kbTenantId);
			if (properties == null) {
				// invalid configuration or tenant not configured, we will default to the global configurable (or previous configuration)
				return;
			}
		}
	}
}
