package com.crystalpixel.neogfutils.system;

import com.crystalpixel.neogfutils.annotation.NotNull;

public interface Serializable {

    @NotNull
    byte[] getAsBytes();
}
