package com.example.practicalfive;  // 改成你的包名

public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();

        if (trimmed.isEmpty()) {
            return null;
        }

        String lowerCase = trimmed.toLowerCase();

        switch (lowerCase) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
