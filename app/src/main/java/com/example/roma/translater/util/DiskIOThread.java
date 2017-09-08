package com.example.roma.translater.util;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Roma on 08.09.2017.
 */

public class DiskIOThread implements Executor {

    private final Executor mDiskIO;

    public DiskIOThread() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
