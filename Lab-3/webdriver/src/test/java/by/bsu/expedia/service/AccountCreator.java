package by.bsu.expedia.service;

import by.bsu.expedia.model.Account;

public class AccountCreator {
    private static final String TESTDATA_CASE_7_ACCOUNT_EMAIL = "testdata.case7.account.email";
    private static final String TESTDATA_CASE_7_ACCOUNT_PASSWORD = "testdata.case7.account.password";

    private static final String TESTDATA_CASE_10_ACCOUNT_EMAIL = "testdata.case10.account.email";

    public static Account withNotRegisteredEmailOrPassword() {
        return new Account(TestDataReader.getTestData(TESTDATA_CASE_7_ACCOUNT_EMAIL),
                TestDataReader.getTestData(TESTDATA_CASE_7_ACCOUNT_PASSWORD));
    }

}
