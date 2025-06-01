package com.gametune.model.hardware;

public enum GpuVendor {
    NVIDIA,
    AMD,
    INTEL,
    OTHER;

    public static GpuVendor fromString(String vendor) {
        if (vendor == null) {
            return OTHER;
        }
        return switch (vendor.toUpperCase()) {
            case "NVIDIA" -> NVIDIA;
            case "AMD" -> AMD;
            case "INTEL" -> INTEL;
            default -> OTHER;
        };
    }
}
