package com.crystalpixel.neogfutils.system;

import com.crystalpixel.neogfutils.annotation.NotNull;

public interface Serializable {

    int getAllocation();

    @NotNull
    byte[] getAsBytes();
}
