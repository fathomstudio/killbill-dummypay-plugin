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

package com.fathomstudio.killbillbluepayplugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.killbill.billing.account.api.Account;
import org.killbill.billing.account.api.AccountApiException;
import org.killbill.billing.account.api.MutableAccountData;
import org.killbill.billing.catalog.api.Currency;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillLogService;
import org.killbill.billing.payment.api.PaymentMethodPlugin;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.payment.api.TransactionType;
import org.killbill.billing.payment.plugin.api.*;
import org.killbill.billing.util.callcontext.CallContext;
import org.killbill.billing.util.callcontext.TenantContext;
import org.killbill.billing.util.entity.Pagination;
import org.osgi.service.log.LogService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * The BluePay gateway interface.
 */
public class BluePayPaymentPluginApi implements PaymentPluginApi {
	
	private final Properties             properties;
	private final OSGIKillbillLogService logService;
	private       OSGIKillbillAPI        killbillAPI;
	
	public BluePayPaymentPluginApi(final Properties properties, final OSGIKillbillLogService logService, final OSGIKillbillAPI killbillAPI) {
		this.properties = properties;
		this.logService = logService;
		this.killbillAPI = killbillAPI;
	}
	
	@Override
	public PaymentTransactionInfoPlugin authorizePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	public PaymentTransactionInfoPlugin capturePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	public PaymentTransactionInfoPlugin purchasePayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	public PaymentTransactionInfoPlugin voidPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	public PaymentTransactionInfoPlugin creditPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	public PaymentTransactionInfoPlugin refundPayment(final UUID kbAccountId, final UUID kbPaymentId, final UUID kbTransactionId, final UUID kbPaymentMethodId, final BigDecimal amount, final Currency currency, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// not implemented
		return new PaymentTransactionInfoPlugin() {
			@Override
			public UUID getKbPaymentId() {
				return kbPaymentId;
			}
			@Override
			public UUID getKbTransactionPaymentId() {
				return kbTransactionId;
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
	
	@Override
	public void addPaymentMethod(final UUID kbAccountId, final UUID kbPaymentMethodId, final PaymentMethodPlugin paymentMethodProps, final boolean setDefault, final Iterable<PluginProperty> properties, final CallContext context) throws PaymentPluginApiException {
		// see: https://www.bluepay.com/developers/api-documentation/java/transactions/store-payment-information/
		String accountId = null;
		String secretKey = null;
		Boolean test = false;
		String type = null;
		
		String creditCardNumber = null;
		Number creditCardCVV2 = null;
		Number creditCardExpirationMonth = null;
		Number creditCardExpirationYear = null;
		
		for (PluginProperty property : properties) {
			String key = property.getKey();
			Object value = property.getValue();
			if (Objects.equals(key, "accountId")) {
				accountId = (String) value;
			} else if (Objects.equals(key, "secretKey")) {
				secretKey = (String) value;
			} else if (Objects.equals(key, "test")) {
				test = (Boolean) value;
			} else if (Objects.equals(key, "type")) {
				type = (String) value;
			} else if (Objects.equals(key, "creditCardNumber")) {
				creditCardNumber = (String) value;
			} else if (Objects.equals(key, "creditCardCVV2")) {
				creditCardCVV2 = (Number) value;
			} else if (Objects.equals(key, "creditCardExpirationMonth")) {
				creditCardExpirationMonth = (Number) value;
			} else if (Objects.equals(key, "creditCardExpirationYear")) {
				creditCardExpirationYear = (Number) value;
			} else {
				throw new PaymentPluginApiException("400", "unrecognized plugin property: " + key);
			}
		}
		
		BluePay payment = new BluePay(accountId, secretKey, test ? "TEST" : "LIVE");
		
		final Account account;
		try {
			account = killbillAPI.getAccountUserApi().getAccountById(kbAccountId, context);
		} catch (AccountApiException e) {
			throw new RuntimeException(e);
		}
		
		HashMap<String, String> customer = new HashMap<>();
		String firstName = account.getName().substring(0, account.getFirstNameLength());
		String lastName = account.getName().substring(account.getFirstNameLength());
		logService.log(LogService.LOG_INFO, "firstName: $firstName");
		logService.log(LogService.LOG_INFO, "lastName: $lastName");
		customer.put("firstName", firstName);
		customer.put("lastName", lastName);
		customer.put("address1", account.getAddress1());
		customer.put("address2", account.getAddress2());
		customer.put("city", account.getCity());
		customer.put("state", account.getStateOrProvince());
		customer.put("zip", account.getPostalCode());
		customer.put("country", account.getCountry());
		customer.put("phone", account.getPhone());
		customer.put("email", account.getEmail());
		payment.setCustomerInformation(customer);
		
		if (type == null) {
			throw new PaymentPluginApiException("400", "missing type");
		}
		if (Objects.equals(type, "card")) {
			HashMap<String, String> card = new HashMap<>();
			card.put("cardNumber", creditCardNumber);
			card.put("expirationDate", creditCardExpirationMonth.toString() + creditCardExpirationYear.toString());
			card.put("ccv2", creditCardCVV2.toString());
			payment.setCCInformation(card);
		} else if (Objects.equals(type, "ach")) {
		} else {
			throw new PaymentPluginApiException("400", "unknown type: " + type);
		}
		
		try {
			payment.process();
		} catch (Exception e) {
			throw new PaymentPluginApiException("500", e);
		}
		
		String transactionId = payment.getTransID();
		
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap map = null;
		try {
			map = objectMapper.readValue(account.getNotes(), HashMap.class);
		} catch (IOException e) {
			// no-op
		}
		if (map == null) {
			map = new HashMap();
		}
		
		map.put("credit", transactionId);
		
		final String updatedNotes;
		try {
			updatedNotes = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		
		try {
			killbillAPI.getAccountUserApi().updateAccount(new Account() {
				@Override
				public MutableAccountData toMutableAccountData() {
					return account.toMutableAccountData();
				}
				@Override
				public Account mergeWithDelegate(Account account) {
					return account.mergeWithDelegate(account);
				}
				@Override
				public String getExternalKey() {
					return account.getExternalKey();
				}
				@Override
				public String getName() {
					return account.getName();
				}
				@Override
				public Integer getFirstNameLength() {
					return account.getFirstNameLength();
				}
				@Override
				public String getEmail() {
					return account.getEmail();
				}
				@Override
				public Integer getBillCycleDayLocal() {
					return account.getBillCycleDayLocal();
				}
				@Override
				public Currency getCurrency() {
					return account.getCurrency();
				}
				@Override
				public UUID getPaymentMethodId() {
					return account.getPaymentMethodId();
				}
				@Override
				public DateTimeZone getTimeZone() {
					return account.getTimeZone();
				}
				@Override
				public String getLocale() {
					return account.getLocale();
				}
				@Override
				public String getAddress1() {
					return account.getAddress1();
				}
				@Override
				public String getAddress2() {
					return account.getAddress2();
				}
				@Override
				public String getCompanyName() {
					return account.getCompanyName();
				}
				@Override
				public String getCity() {
					return account.getCity();
				}
				@Override
				public String getStateOrProvince() {
					return account.getStateOrProvince();
				}
				@Override
				public String getPostalCode() {
					return account.getPostalCode();
				}
				@Override
				public String getCountry() {
					return account.getCountry();
				}
				@Override
				public String getPhone() {
					return account.getPhone();
				}
				@Override
				public Boolean isMigrated() {
					return account.isMigrated();
				}
				@Override
				public Boolean isNotifiedForInvoices() {
					return account.isNotifiedForInvoices();
				}
				@Override
				public UUID getParentAccountId() {
					return account.getParentAccountId();
				}
				@Override
				public Boolean isPaymentDelegatedToParent() {
					return account.isPaymentDelegatedToParent();
				}
				@Override
				public String getNotes() {
					return updatedNotes;
				}
				@Override
				public UUID getId() {
					return account.getId();
				}
				@Override
				public DateTimeZone getFixedOffsetTimeZone() {
					return account.getFixedOffsetTimeZone();
				}
				@Override
				public DateTime getReferenceTime() {
					return account.getReferenceTime();
				}
				@Override
				public DateTime getCreatedDate() {
					return account.getCreatedDate();
				}
				@Override
				public DateTime getUpdatedDate() {
					return account.getUpdatedDate();
				}
			}, context);
		} catch (Exception e) {
			throw new RuntimeException(e);
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
