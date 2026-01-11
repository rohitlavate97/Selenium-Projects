package com.alchemist.tests.base;

import com.alchemist.framework.specs.SpecBuilder;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        SpecBuilder.init();
    }
}
