package com.showmeyourcode.playground.java;

import com.showmeyourcode.playground.java.release.JavaReleases;
import com.showmeyourcode.playground.kotlin.common.Logging;
import org.slf4j.Logger;

public class JavaRelease {

    // an example how to use a Kotlin class in Java
    public static final Logger LOGGER = Logging.INSTANCE.getLOGGER();

    public static void main(String[] args) {
        LOGGER.info("\nStarting Java Release Application...\n");

        JavaReleases.main();
    }
}
