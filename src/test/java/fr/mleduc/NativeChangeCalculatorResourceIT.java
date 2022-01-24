package fr.mleduc;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeChangeCalculatorResourceIT extends ChangeCalculatorResourceTest {
    // Execute the same tests but in native mode.
}
