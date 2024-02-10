package com.crystalpixel.neogfutils.game.Graphics;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.vulkan.*;

import static org.lwjgl.system.MemoryUtil.*;

public class VulkanShader {

    private final long shaderModule;
    private final VkDevice device;

    public VulkanShader(VkDevice device, String filePath) throws IOException {
        this.device = device;

        byte[] code = readFile(filePath);
        ByteBuffer shaderCode = ByteBuffer.allocateDirect(code.length);
        shaderCode.put(code).flip();
        VkShaderModuleCreateInfo createInfo = VkShaderModuleCreateInfo.calloc()
                .sType(VK10.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO)
                .pCode(shaderCode);

        LongBuffer pShaderModule = memAllocLong(1);
        if (VK10.vkCreateShaderModule(device, createInfo, null, pShaderModule) != VK10.VK_SUCCESS) {
            throw new RuntimeException("Failed to create shader module");
        }

        shaderModule = pShaderModule.get(0);
    }

    public long getShaderModule() {
        return shaderModule;
    }

    public VkDevice getDevice() {
        return this.device;
    }

    public void cleanup(VkDevice device) {
        VK10.vkDestroyShaderModule(device, shaderModule, null);
    }

    private byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}


