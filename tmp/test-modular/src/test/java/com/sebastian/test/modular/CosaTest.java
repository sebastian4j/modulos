package com.sebastian.test.modular;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Sebastián Ávila A.
 */
class CosaTest {

    @Test
    void cosaTest() {
        Cosa cosa = new Cosa();
        Assertions.assertThat(cosa).isNotNull();
    }
}
