package com.gametune.model.hardware;

public enum CpuVendor {
    INTEL,
    AMD,
    ARM,
    OTHER;

    public static CpuVendor fromString(String vendor) {
        if (vendor == null) {
            return OTHER;
        }
        return switch (vendor.toUpperCase()) {
            case "INTEL" -> INTEL;
            case "AMD" -> AMD;
            case "ARM" -> ARM;
            default -> OTHER;
        };
    }
}
