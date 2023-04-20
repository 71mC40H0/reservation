package com.zerobase.reservation.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Plain Text");
        assertNotNull(encrypt);
        assertEquals(Aes256Util.decrypt(encrypt), "Plain Text");
    }

}