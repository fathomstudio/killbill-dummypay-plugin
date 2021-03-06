/*
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

import org.joda.time.DateTime;
import org.killbill.billing.account.api.Account;
import org.killbill.billing.account.api.AccountApiException;
import org.killbill.billing.catalog.api.Currency;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillDataSource;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillLogService;
import org.killbill.billing.payment.api.PaymentMethodPlugin;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.payment.api.TransactionType;
import org.killbill.billing.payment.plugin.api.*;
import org.killbill.billing.util.callcontext.CallContext;
import org.killbill.billing.util.callcontext.TenantContext;
import org.killbill.billing.util.entity.Pagination;
import org.osgi.service.log.LogService;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DummyPay gateway interface.
 */
public class DummyPayPaymentPluginApi implements PaymentPluginApi {
	
	private final Properties             properties;
	private final OSGIKillbillLogService logService;
	private       OSGIKillbillAPI        killbillAPI;
	private       OSGIKillbillDataSource dataSource;
	
	public DummyPayPaymentPluginApi(final Properties properties, final OSGIKillbillLogService logService, final OSGIKillbillAPI killbillAPI, OSGIKillbillDataSource dataSource) {
		this.properties = properties;
		this.logService = logService;
		this.killbillAPI = killbillAPI;
		this.dataSource = dataSource;
	}
	
	@Override
	public PaymentTransactionInfoPlugin authorizePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return null;
			}
			
			@Override
			public BigDecimal getAmount() {
				return null;
			}
			
			@Override
			public Currency getCurrency() {
				return null;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return null;
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return null;
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return PaymentPluginStatus.CANCELED;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return null;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public PaymentTransactionInfoPlugin capturePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return null;
			}
			
			@Override
			public BigDecimal getAmount() {
				return null;
			}
			
			@Override
			public Currency getCurrency() {
				return null;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return null;
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return null;
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return PaymentPluginStatus.CANCELED;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return null;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	/**
	 * Called to actually make the payment.
	 *
	 * @param kbAccountId       - the account
	 * @param kbPaymentId       - the paymentID
	 * @param kbgatewayToken    - the gatewayToken
	 * @param kbPaymentMethodId - the paymentMethodId to make the payment with
	 * @param amount            - the amount
	 * @param currency          - the currency
	 * @param properties        - properties specified by the client
	 * @param context           - the context
	 * @return
	 * @throws PaymentPluginApiException
	 */
	@Override
	public PaymentTransactionInfoPlugin purchasePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// get the account associated with the ID
		final Account account;
		try {
			account = killbillAPI.getAccountUserApi().getAccountById(kbAccountId, context);
		} catch (AccountApiException e) {
			throw new RuntimeException(e);
		}
		
		String gatewayToken;
		
		String gatewayTokenQuery = "SELECT `gatewayToken` FROM `dummyPay_paymentMethods` WHERE `paymentMethodId` = ?";
		try (Connection connection = dataSource.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(gatewayTokenQuery)) {
				statement.setString(1, kbPaymentMethodId.toString());
				ResultSet resultSet = statement.executeQuery();
				if (!resultSet.next()) {
					throw new SQLException("no results");
				}
				gatewayToken = resultSet.getString("gatewayToken");
			} catch (SQLException e) {
				logService.log(LogService.LOG_ERROR, "could not retrieve gateway token: ", e);
				throw new PaymentPluginApiException("could not retrieve gateway token", e);
			}
		} catch (SQLException e) {
			logService.log(LogService.LOG_ERROR, "could not retrieve gateway token: ", e);
			throw new PaymentPluginApiException("could not retrieve gateway token", e);
		}
		
		String message = "{\"type\":\"saved\",\"savedId\":\"" + gatewayToken + "\",\"amount\":" + amount + "}";
		logService.log(LogService.LOG_INFO, "Constructed message: " + message);
		
		int status;
		String error = null;
		
		HttpsURLConnection connection = null;
		String out = "";
		try {
			connection = (HttpsURLConnection) new URL("https://dummypay.io/pay").openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.connect();
			
			// write
			connection.getOutputStream().write(message.getBytes());
			
			// read
			try (InputStream inputStream = connection.getInputStream()) {
				Reader input = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				for (int c = input.read(); c != -1; c = input.read()) {
					out += (char) c;
				}
			}
			status = connection.getResponseCode();
		} catch (IOException e) {
			logService.log(LogService.LOG_ERROR, "Could not complete payment", e);
			throw new PaymentPluginApiException("Could not complete payment", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		if (status == 204) {
			logService.log(LogService.LOG_INFO, "DummyPay payment successful with gateway token: " + gatewayToken);
		} else if (status == 402) {
			Matcher m = Pattern.compile("\"message\":\"(.+)\"").matcher(out);
			m.find();
			error = m.group(1);
			logService.log(LogService.LOG_INFO, "DummyPay payument unsuccessful with error: " + error);
		} else {
			throw new PaymentPluginApiException("Didn't expect a " + status + " response code: " + out, new Exception());
		}
		
		// send response
		final int finalStatus = status;
		final String finalError = error;
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return TransactionType.PURCHASE;
			}
			
			@Override
			public BigDecimal getAmount() {
				return amount;
			}
			
			@Override
			public Currency getCurrency() {
				return currency;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return DateTime.now();
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return DateTime.now();
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return finalStatus == 204 ? PaymentPluginStatus.PROCESSED : PaymentPluginStatus.ERROR;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return finalError;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public PaymentTransactionInfoPlugin voidPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return null;
			}
			
			@Override
			public BigDecimal getAmount() {
				return null;
			}
			
			@Override
			public Currency getCurrency() {
				return null;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return null;
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return null;
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return PaymentPluginStatus.CANCELED;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return null;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public PaymentTransactionInfoPlugin creditPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return null;
			}
			
			@Override
			public BigDecimal getAmount() {
				return null;
			}
			
			@Override
			public Currency getCurrency() {
				return null;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return null;
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return null;
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return PaymentPluginStatus.CANCELED;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return null;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public PaymentTransactionInfoPlugin refundPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbgatewayToken, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbgatewayToken;
			}
			
			@Override
			public TransactionType getTransactionType() {
				return null;
			}
			
			@Override
			public BigDecimal getAmount() {
				return null;
			}
			
			@Override
			public Currency getCurrency() {
				return null;
			}
			
			@Override
			public DateTime getCreatedDate() {
				return null;
			}
			
			@Override
			public DateTime getEffectiveDate() {
				return null;
			}
			
			@Override
			public PaymentPluginStatus getStatus() {
				return PaymentPluginStatus.CANCELED;
			}
			
			@Override
			public String getGatewayError() {
				return null;
			}
			
			@Override
			public String getGatewayErrorCode() {
				return null;
			}
			
			@Override
			public String getFirstPaymentReferenceId() {
				return null;
			}
			
			@Override
			public String getSecondPaymentReferenceId() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public List<PaymentTransactionInfoPlugin> getPaymentInfo(final UUID kbAccountId, final UUID kbPaymentId, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentPluginApiException {
		// not implemented
		return Collections.emptyList();
	}
	
	@Override
	public Pagination<PaymentTransactionInfoPlugin> searchPayments(final String searchKey, final Long offset, final Long limit, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentPluginApiException {
		// not implemented
		return new Pagination<PaymentTransactionInfoPlugin>() {
			@Override
			public Long getCurrentOffset() {
				return null;
			}
			
			@Override
			public Long getNextOffset() {
				return null;
			}
			
			@Override
			public Long getMaxNbRecords() {
				return null;
			}
			
			@Override
			public Long getTotalNbRecords() {
				return null;
			}
			
			@Override
			public Iterator<PaymentTransactionInfoPlugin> iterator() {
				return null;
			}
		};
	}
	
	/**
	 * Create a payment method with the given details.
	 *
	 * @param kbAccountId        - the account
	 * @param kbPaymentMethodId  - the paymentMethodId
	 * @param paymentMethodProps - the properties
	 * @param setDefault         - if this should be the default
	 * @param properties         - client-specified properties
	 * @param context            - the context
	 * @throws PaymentPluginApiException
	 */
	@Override
	public void addPaymentMethod(final UUID kbAccountId, final UUID kbPaymentMethodId, final PaymentMethodPlugin paymentMethodProps, final boolean setDefault, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		String paymentType = null;
		
		String creditCardNumber = null;
		String creditCardCVV2 = null;
		String creditCardExpirationMonth = null;
		String creditCardExpirationYear = null;
		
		String routingNumber = null;
		String accountNumber = null;
		
		// get the client-passed properties including BluePay auth details and appropriate credit card or ACH details
		for (PluginProperty property : paymentMethodProps.getProperties()) {
			String key = property.getKey();
			Object value = property.getValue();
			if (Objects.equals(key, "paymentType")) {
				paymentType = value.toString();
			} else if (Objects.equals(key, "creditCardNumber")) {
				creditCardNumber = value.toString();
			} else if (Objects.equals(key, "creditCardCVV2")) {
				creditCardCVV2 = value.toString();
			} else if (Objects.equals(key, "creditCardExpirationMonth")) {
				creditCardExpirationMonth = value.toString();
			} else if (Objects.equals(key, "creditCardExpirationYear")) {
				creditCardExpirationYear = value.toString();
			} else if (Objects.equals(key, "routingNumber")) {
				routingNumber = value.toString();
			} else if (Objects.equals(key, "accountNumber")) {
				accountNumber = value.toString();
			} else {
				throw new PaymentPluginApiException("unrecognized plugin property: " + key, new IllegalArgumentException());
			}
		}
		
		String message;
		if (Objects.equals(paymentType, "card")) {
			message = "{\"type\":\"card\",\"cardNumber\":" + creditCardNumber + ",\"cardCvv\":" + creditCardCVV2 + ",\"cardExpMonth\":" + creditCardExpirationMonth + ",\"cardExpYear\":" + creditCardExpirationYear + "}";
		} else if (Objects.equals(paymentType, "ach")) {
			message = "{\"type\":\"bank\",\"bankRouting\":" + routingNumber + ",\"bankAccount\":" + accountNumber + "}";
		} else {
			throw new PaymentPluginApiException("unknown paymentType: " + paymentType, new Exception());
		}
		logService.log(LogService.LOG_INFO, "Constructed message: " + message);
		
		int status;
		String error = null;
		String gatewayToken;
		
		HttpsURLConnection connection = null;
		String out = "";
		try {
			connection = (HttpsURLConnection) new URL("https://dummypay.io/save").openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.connect();
			
			// write
			connection.getOutputStream().write(message.getBytes());
			
			// read
			try (InputStream inputStream = connection.getInputStream()) {
				Reader input = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				for (int c = input.read(); c != -1; c = input.read()) {
					out += (char) c;
				}
			}
			status = connection.getResponseCode();
		} catch (IOException e) {
			logService.log(LogService.LOG_ERROR, "Could not complete save", e);
			throw new PaymentPluginApiException("Could not complete save", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		logService.log(LogService.LOG_INFO, "status: " + status);
		logService.log(LogService.LOG_INFO, "out: " + out);
		
		if (status == 200) {
			Matcher m = Pattern.compile("\"savedId\":\"(.+)\"").matcher(out);
			m.find();
			gatewayToken = m.group(1);
			logService.log(LogService.LOG_INFO, "DummyPay save successful with gateway token: " + gatewayToken);
		} else if (status == 402) {
			Matcher m = Pattern.compile("\"message\":\"(.+)\"").matcher(out);
			error = m.group(1);
			logService.log(LogService.LOG_INFO, "DummyPay save unsuccessful with error: " + error);
			throw new PaymentPluginApiException("DummyPay save unsuccessful with error: " + error, new Exception());
		} else {
			throw new PaymentPluginApiException("Didn't expect a " + status + " response code: " + out, new Exception());
		}
		
		String gatewayTokenQuery = "INSERT INTO `dummyPay_paymentMethods` (`paymentMethodId`, `gatewayToken`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `paymentMethodId` = ?, `gatewayToken` = ?";
		try (Connection dataConnection = dataSource.getDataSource().getConnection()) {
			try (PreparedStatement statement = dataConnection.prepareStatement(gatewayTokenQuery)) {
				statement.setString(1, kbPaymentMethodId.toString());
				statement.setString(2, gatewayToken);
				statement.setString(3, kbPaymentMethodId.toString());
				statement.setString(4, gatewayToken);
				statement.executeUpdate();
			} catch (SQLException e) {
				logService.log(LogService.LOG_ERROR, "could not save gateway token: ", e);
				throw new PaymentPluginApiException("could not save gateway token", e);
			}
		} catch (SQLException e) {
			logService.log(LogService.LOG_ERROR, "could not save gateway token: ", e);
			throw new PaymentPluginApiException("could not save gateway token", e);
		}
	}
	
	@Override
	public void deletePaymentMethod(final UUID kbAccountId, final UUID kbPaymentMethodId, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
	}
	
	@Override
	public PaymentMethodPlugin getPaymentMethodDetail(final UUID kbAccountId, final UUID kbPaymentMethodId, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentMethodPlugin() {
			@Override
			public UUID getKbPaymentMethodId() {
				return kbPaymentMethodId;
			}
			
			@Override
			public String getExternalPaymentMethodId() {
				return null;
			}
			
			@Override
			public boolean isDefaultPaymentMethod() {
				return false;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public void setDefaultPaymentMethod(final UUID kbAccountId, final UUID kbPaymentMethodId, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
	}
	
	@Override
	public List<PaymentMethodInfoPlugin> getPaymentMethods(final UUID kbAccountId, final boolean refreshFromGateway, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return Collections.emptyList();
	}
	
	@Override
	public Pagination<PaymentMethodPlugin> searchPaymentMethods(final String searchKey, final Long offset, final Long limit, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentPluginApiException {
		// not implemented
		return new Pagination<PaymentMethodPlugin>() {
			@Override
			public Long getCurrentOffset() {
				return null;
			}
			
			@Override
			public Long getNextOffset() {
				return null;
			}
			
			@Override
			public Long getMaxNbRecords() {
				return null;
			}
			
			@Override
			public Long getTotalNbRecords() {
				return null;
			}
			
			@Override
			public Iterator<PaymentMethodPlugin> iterator() {
				return null;
			}
		};
	}
	
	@Override
	public void resetPaymentMethods(final UUID kbAccountId, final List<PaymentMethodInfoPlugin> paymentMethods, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
	}
	
	@Override
	public HostedPaymentPageFormDescriptor buildFormDescriptor(final UUID kbAccountId, final Iterable<PluginProperty> customFields, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new HostedPaymentPageFormDescriptor() {
			@Override
			public UUID getKbAccountId() {
				return kbAccountId;
			}
			
			@Override
			public String getFormMethod() {
				return null;
			}
			
			@Override
			public String getFormUrl() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getFormFields() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
	
	@Override
	public GatewayNotification processNotification(final String notification, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new GatewayNotification() {
			@Override
			public UUID getKbPaymentId() {
				return null;
			}
			
			@Override
			public int getStatus() {
				return 0;
			}
			
			@Override
			public String getEntity() {
				return null;
			}
			
			@Override
			public Map<String, List<String>> getHeaders() {
				return null;
			}
			
			@Override
			public List<PluginProperty> getProperties() {
				return null;
			}
		};
	}
}
