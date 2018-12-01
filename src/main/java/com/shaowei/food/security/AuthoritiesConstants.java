package com.shaowei.food.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    
    public static final String PROVIDER_ADMINISTRATOR = "PROVIDER_ADMINISTRATOR";
    public static final String PROVIDER_SALES = "PROVIDER_SALES";
    public static final String PROVIDER_SALES_ADMINISTRATOR = "PROVIDER_SALES_ADMINISTRATOR";
    public static final String PROVIDER_FORWARDER = "PROVIDER_FORWARDER";
    
    public static final String TRADER_ADMINISTRATOR = "TRADER_ADMINISTRATOR";
    public static final String TRADER_SALES = "TRADER_SALES";
    public static final String TRADER_SALES_ADMINISTRATOR = "TRADER_SALES_ADMINISTRATOR";
    public static final String TRADER_ACCOUNTANTTRADER_FORWARDER = "TRADER_ACCOUNTANTTRADER_FORWARDER";
    
    public static final String BUYER_ADMINISTRATOR = "BUYER_ADMINISTRATOR";
    public static final String BUYER_SALES = "BUYER_SALES";
    public static final String BUYER_SALES_ADMINISTRATOR = "BUYER_SALES_ADMINISTRATOR";
    public static final String BUYER_FORWARDER = "BUYER_FORWARDER";
    
    public static final String ROOT = "ROOT";
    public static final String ADMINISTRATOR = "ADMINISTRATOR";

    private AuthoritiesConstants() {
    }
}
