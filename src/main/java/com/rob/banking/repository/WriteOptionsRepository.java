package com.rob.banking.repository;

import java.nio.file.StandardOpenOption;

public enum WriteOptionsRepository {
    APPEND_OPTION(StandardOpenOption.APPEND),
    TRUNCATE_EXISTING_OPTION(StandardOpenOption.TRUNCATE_EXISTING);
    private final StandardOpenOption option;

    WriteOptionsRepository(StandardOpenOption option) {
        this.option = option;
    }

    public StandardOpenOption getOption() {
        return option;
    }
}
