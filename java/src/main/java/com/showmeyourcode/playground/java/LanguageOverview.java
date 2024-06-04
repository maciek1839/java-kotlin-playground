package com.showmeyourcode.playground.java;

import com.showmeyourcode.playground.java.overview.Arrays;
import com.showmeyourcode.playground.java.overview.Classes;
import com.showmeyourcode.playground.java.overview.Functions;
import com.showmeyourcode.playground.java.overview.Lists;
import com.showmeyourcode.playground.java.overview.Precision;
import com.showmeyourcode.playground.java.overview.async.Futures;
import com.showmeyourcode.playground.java.overview.concurrency.Concurrency;
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

        Arrays.main();
        Lists.main();
        Classes.main();
        Functions.main();
        Futures.main();
        Concurrency.main();

        LanguageFeatures.main();

        Datatypes.main();
        Exceptions.main();
        Equality.main();
        Precision.main(new String[]{});

        Jvm.main();
    }
}
