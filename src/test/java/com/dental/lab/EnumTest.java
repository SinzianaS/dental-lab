package com.dental.lab;

import com.dental.lab.data.domain.enums.Status;
import com.dental.lab.data.domain.enums.StatusUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTest {

    @Test
    void givenCustomName_whenUsingGetByUpperCaseName_thenReturnEnumConstant() {
        assertEquals(Status.COMPLETED, StatusUtils.getByUpperCaseName("completed"));

        assertEquals(Status.IN_PROGRESS, StatusUtils.getByUpperCaseName("in_progress"));
    }
}
