package com.acmebank.accountManager.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validator {
    public boolean isValidAccountFormat(String accountNumber) {
        if (accountNumber.length()!=8) return false;

        try {
            Long num = Long.parseLong(accountNumber);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }
}
