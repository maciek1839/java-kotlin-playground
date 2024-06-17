package com.showmeyourcode.playground.java;

import com.showmeyourcode.playground.java.overview.Classes;
import com.showmeyourcode.playground.java.overview.Equality;
import com.showmeyourcode.playground.java.overview.Functions;
import com.showmeyourcode.playground.java.overview.Jvm;
import com.showmeyourcode.playground.java.overview.LanguageFeatures;
import com.showmeyourcode.playground.java.overview.Precision;
import com.showmeyourcode.playground.java.overview.Time;
import com.showmeyourcode.playground.java.overview.async.Futures;
import com.showmeyourcode.playground.java.overview.collections.Arrays;
import com.showmeyourcode.playground.java.overview.collections.Lists;
import com.showmeyourcode.playground.java.overview.collections.Sets;
import com.showmeyourcode.playground.java.overview.concurrency.Concurrency;
import com.showmeyourcode.playground.java.overview.datatype.Datatypes;
import com.showmeyourcode.playground.java.overview.exception.Exceptions;
import com.showmeyourcode.playground.kotlin.common.Logging;
import org.slf4j.Logger;


public class LanguageOverview {

    // an example how to use a Kotlin class in Java
    private static final Logger LOGGER = Logging.INSTANCE.getLOGGER();

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        LOGGER.info("\nStarting Java Language Overview...\n");

        Arrays.main(new String[0]);
        Lists.main(new String[0]);
        Sets.main(new String[0]);

        Classes.main(new String[0]);
        Functions.main(new String[0]);
        Futures.main(new String[0]);
        Concurrency.main(new String[0]);
        Time.main(new String[0]);

        LanguageFeatures.main(new String[0]);

        Datatypes.main(new String[0]);
        Exceptions.main(new String[0]);
        Equality.main(new String[0]);
        Precision.main(new String[0]);

        Jvm.main(new String[0]);
    }
}
