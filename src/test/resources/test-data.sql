-- noinspection SqlNoDataSourceInspectionForFile
--silent the warning message of "No data sources are configured"

TRUNCATE TABLE ACCOUNT;
INSERT INTO ACCOUNT (id, account_number, account_balance)
VALUES (100, '12345678', 1000000.00);

INSERT INTO ACCOUNT (id, account_number, account_balance)
VALUES (101, '88888888', 1000000.00);

-- for transaction table test data
TRUNCATE TABLE TRANSACTION;

INSERT INTO TRANSACTION (id, from_account_number, to_account_number, amount, created_date)
VALUES (1000, '12345678', '88888888', 100.00, current_timestamp());


INSERT INTO TRANSACTION (id, from_account_number, to_account_number, amount, created_date)
VALUES (1001, '12345678', '88888888', 200.00, current_timestamp());