# Basic Banking System

A console application in Java that manages bank accounts and transactions, using a CSVFile as storage. 

## Features
- Account creation.
- Deposit or withdraw money (with amount validation).
- View account details.
- Delete accounts.

## Design decisions

- File input/output to simulate a repository.
  - CSV storage uses Apache Commons CSV.
  - Values containing commas or line breaks are quoted correctly.
- Unit tests (JUnit) were used to verify the code. 
  - Temporary directories are created with `@TempDir`, so no real files are touched.
- `BigDecimal` is used to avoid rounding errors in money calculations.
  - Double datatype cannot represent decimal values exactly, so balances use `BigDecimal`.

## Requirements
- Maven

## Limitations
- Concurrent access is not supported.
- Every update rewrites the entire repository.
- Data is stored in plain text.

## Possible next steps
- Replace the CSV file with a database.
- Add a transaction history.

