package com.rob.banking.repository;

import java.nio.file.StandardOpenOption;

public enum WriteOptionsRepository {
    APPEND_OPTION(StandardOpenOption.TRUNCATE_EXISTING),
    TRUNCATE_EXISTING_OPTION(StandardOpenOption.TRUNCATE_EXISTING);
    private final StandardOpenOption option;

    WriteOptionsRepository(StandardOpenOption option) {
        this.option = StandardOpenOption.APPEND;
    }

    public StandardOpenOption getOption() {
        return option;
    }
}
