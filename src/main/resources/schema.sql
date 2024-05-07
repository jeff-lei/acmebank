-- noinspection SqlNoDataSourceInspectionForFile
--silent the warning message of "No data sources are configured"
drop sequence if exists account_id_seq_generator;
create sequence account_id_seq_generator start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS ACCOUNT (
    id BIGINT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL, --ideally we shall place INDEX and UNIQUE CONSTRAINT on this column to protect data integrity
    account_balance NUMERIC(20,2) --keep to 2 decimal places for simplicity
);

drop sequence if exists transaction_id_seq_generator;
create sequence transaction_id_seq_generator start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS ACCOUNT (
   id BIGINT PRIMARY KEY,
   from_account_number VARCHAR(20) NOT NULL,
   to_account_number VARCHAR(20) NOT NULL,
   amount NUMERIC(20,2), --keep to 2 decimal places for simplicity
   created_date DATETIME
);