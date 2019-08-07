package com.cyosp.homebank.server.model;

import com.cyosp.homebank.server.repository.HomebankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountIT {

    @Autowired
    private HomebankRepository homebankRepository;

    @Test
    void balance() {
        Account account = homebankRepository.account(1);

        assertEquals(new BigDecimal("1038.45"), account.getBalance());
    }
}
