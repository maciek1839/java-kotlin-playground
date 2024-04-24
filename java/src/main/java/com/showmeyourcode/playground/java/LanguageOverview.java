package com.showmeyourcode.playground.java;

import com.showmeyourcode.playground.java.code.Samples;
import com.showmeyourcode.playground.java.overview.Equality;
import com.showmeyourcode.playground.java.overview.Jvm;
import com.showmeyourcode.playground.java.overview.LanguageFeatures;
import com.showmeyourcode.playground.java.overview.datatype.Datatypes;
import com.showmeyourcode.playground.java.overview.exception.Exceptions;
import com.showmeyourcode.playground.kotlin.common.Logging;
import org.slf4j.Logger;

public class LanguageOverview {

    public static final Logger LOGGER = Logging.INSTANCE.getLOGGER();

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        LOGGER.info("\nStarting Java Language Overview...\n");

        LanguageFeatures.main();

        Datatypes.main();
        Exceptions.main();
        Equality.main();

        Jvm.main();

        // code samples
        Samples.main();
    }
}
