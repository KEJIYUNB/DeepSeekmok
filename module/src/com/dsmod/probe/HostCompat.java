package com.dsmod.probe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Runtime symbols for the supported DeepSeek host generations.
 *
 * <p>DeepSeek is minified with R8, so an application update can rename every host class while
 * keeping the actual contract unchanged.  Callers always use the 2.2.x symbol as the stable
 * logical key; this class translates it when the installed host is 2.3.0 (237) or 2.3.4 (246).</p>
 */
final class HostCompat {
    private static volatile boolean initialized;
    private static volatile boolean v230;
    private static volatile boolean v246;

    private HostCompat() {}

    static synchronized void initialize(ClassLoader loader) {
        if (initialized) return;
        v230 = hasV230CompletionRequest(loader);
        v246 = hasV246CompletionRequest(loader);
        initialized = true;
    }

    static boolean isV230() {
        return v230;
    }

    static boolean isV246() {
        return v246;
    }

    static String generationName() {
        if (v246) return "2.3.4/code246";
        return v230 ? "2.3.0/code237" : "2.2.x";
    }

    /**
     * 2.3.0 (237) detection: qw0 has the 11-arg completion request constructor.
     */
    private static boolean hasV230CompletionRequest(ClassLoader loader) {
        try {
            Class<?> candidate = Class.forName("qw0", false, loader);
            for (Constructor<?> constructor : candidate.getDeclaredConstructors()) {
                Class<?>[] p = constructor.getParameterTypes();
                if (p.length == 11
                        && p[0] == String.class
                        && p[2] == String.class
                        && p[4] == boolean.class
                        && p[5] == boolean.class
                        && p[7] == boolean.class
                        && p[8] == String.class
                        && p[9] == String.class
                        && p[10] == int.class) {
                    return true;
                }
            }
        } catch (Throwable ignored) {}
        return false;
    }

    /**
     * 2.3.4 (246) detection: gz0 is ChatFullCompletionRequest in this generation.
     */
    private static boolean hasV246CompletionRequest(ClassLoader loader) {
        try {
            Class<?> candidate = Class.forName("gz0", false, loader);
            for (Constructor<?> constructor : candidate.getDeclaredConstructors()) {
                Class<?>[] p = constructor.getParameterTypes();
                if (p.length == 11
                        && p[0] == String.class
                        && p[1] == Integer.class
                        && p[2] == String.class
                        && p[3] == java.util.List.class) {
                    return true;
                }
            }
        } catch (Throwable ignored) {}
        return false;
    }

    static Class<?> load(ClassLoader loader, String legacyName)
            throws ClassNotFoundException {
        return loader.loadClass(name(legacyName));
    }

    static String name(String legacyName) {
        if (legacyName == null) return legacyName;
        if (v246) return name246(legacyName);
        if (v230) return name230(legacyName);
        return legacyName;
    }

    /** 2.3.0 (237) mapping table. */
    static String name230(String legacyName) {
        switch (legacyName) {
            case "u25": return "t55";
            case "fo2": return "lq2";
            case "ho2": return "nq2";
            case "at7": return "aw7";
            case "ht7": return "hw7";
            case "h78": return "ja8";
            case "i68": return "j98";
            case "yg8": return "ji8";
            case "ew0": return "qw0";
            case "rs0": return "ct0";
            case "s92": return "ac2";
            case "r92": return "zb2";
            case "b41": return "y41";
            case "q03": return "x23";
            case "q71": return "n81";
            case "w02": return "e32";
            case "ui8": return "vl8";
            case "n02": return "v22";
            case "uz1": return "c22";
            case "vz1": return "d22";
            case "mb3": return "xd3";
            case "xa3": return "id3";
            case "ib3": return "td3";
            case "u82": return "dq1";
            case "c74": return "r94";
            case "fx6": return "zz6";
            case "tp": return "aq";
            case "uo": return "cp";
            case "uo7": return "sr7";
            case "mv": return "vv";
            case "kv": return "tv";
            case "vv7": return "uy7";
            case "lv7": return "ky7";
            case "h83": return "ta3";
            case "za1": return "cc1";
            case "na1": return "nb1";
            case "bu0": return "mu0";
            case "zs0": return "kt0";
            case "au0": return "lu0";
            case "at0": return "lt0";
            case "op5": return "hs5";
            case "kp5": return "ds5";
            case "vx2": return "b03";
            case "yg3": return "v99";
            case "h61": return "g71";
            case "fu0": return "ru0";
            case "uu0": return "hv0";
            case "h1": return "n1";
            case "jm7": return "gp7";
            case "rs7": return "rv7";
            case "xs7": return "xv7";
            case "kb7": return "he7";
            case "sf5": return "ni5";
            case "gf5": return "bi5";
            case "y91": return "xa1";
            case "cy4": return "t05";
            case "px4": return "h05";
            case "x94": return "mc4";
            case "hv": return "qv";
            case "ch4": return "rj4";
            case "m84": return "bb4";
            case "gm8": return "hp8";
            case "pw0": return "bx0";
            case "am8": return "bp8";
            case "sl8": return "so8";
            case "ed0": return "fh";
            case "p68": return "q98";
            case "aw": return "m17";
            case "ve1": return "wg1";
            case "ie": return "pe";
            case "sb1": return "vc1";
            case "rm5": return "mp5";
            case "gf8": return "ii8";
            case "mc": return "tc";
            case "mq5": return "gt5";
            case "bn2": return "hp2";
            case "n51": return "k61";
            case "cn2": return "ip2";
            case "zm2": return "fp2";
            case "y31": return "v41";
            case "qg5": return "lj5";
            case "lw5": return "fz5";
            case "bm4": return "qo4";
            case "fe7": return "ch7";
            case "sm4": return "hp4";
            case "ce": return "je";
            case "c46": return "w66";
            case "gn9": return "yt9";
            case "us": return "dt";
            case "pv0": return "cw0";
            case "k31": return "h41";
            case "fp": return "mp";
            case "ky2": return "r03";
            case "yu0": return "lv0";
            case "ty0": return "mz0";
            case "su3": return "cx3";
            case "a60": return "m60";
            case "u40": return "e50";
            case "xv0": return "kw0";
            case "jv0": return "xv0";
            case "zv0": return "mw0";
            case "lj9": return "vm9";
            case "pl9": return "ml9";
            case "i91": return "fa1";
            case "jb1": return "mc1";
            default: return legacyName;
        }
    }

    /** 2.3.4 (246) mapping table. */
    static String name246(String legacyName) {
        switch (legacyName) {
            // Chat completion request / transport
            case "ew0": return "gz0";
            case "rm5": return "fx5";
            case "uo": return "iw";
            case "tp": return "bp7";
            // Placeholder: other 246 symbols to be confirmed.
            default: return legacyName;
        }
    }

    static String method(String legacyOwner, String legacyMethod) {
        if (v246) return method246(legacyOwner, legacyMethod);
        if (!v230 || legacyOwner == null || legacyMethod == null) return legacyMethod;
        if ("u25".equals(legacyOwner) && "i".equals(legacyMethod)) return "l";
        if (("fo2".equals(legacyOwner) || "ho2".equals(legacyOwner))
                && "g".equals(legacyMethod)) return "e";
        if ("yg8".equals(legacyOwner) && "b".equals(legacyMethod)) return "e";
        if ("mc".equals(legacyOwner)) {
            if ("e".equals(legacyMethod)) return "b";
            if ("f".equals(legacyMethod)) return "c";
        }
        if ("mq5".equals(legacyOwner) && "i".equals(legacyMethod)) return "l";
        if ("qg5".equals(legacyOwner) && "w".equals(legacyMethod)) return "s";
        if ("bm4".equals(legacyOwner)) {
            if ("i".equals(legacyMethod)) return "h";
            if ("k".equals(legacyMethod)) return "j";
            if ("t".equals(legacyMethod)) return "q";
            if ("w".equals(legacyMethod)) return "t";
        }
        if ("p68".equals(legacyOwner) && "a".equals(legacyMethod)) return "d";
        if ("aw".equals(legacyOwner) && "a".equals(legacyMethod)) return "g";
        if ("ed0".equals(legacyOwner) && "h".equals(legacyMethod)) return "n";
        if ("i91".equals(legacyOwner)) {
            if ("a".equals(legacyMethod)) return "c";
            if ("b".equals(legacyMethod)) return "e";
            if ("c".equals(legacyMethod)) return "f";
        }
        if ("u82".equals(legacyOwner)) {
            if ("K".equals(legacyMethod)) return "f0";
            if ("P".equals(legacyMethod)) return "p0";
        }
        if ("mv".equals(legacyOwner) || "uo".equals(legacyOwner)) {
            return messageMethod(legacyMethod);
        }
        return legacyMethod;
    }

    static String method246(String legacyOwner, String legacyMethod) {
        // TODO: 246 method mapping, same as legacy for now.
        return legacyMethod;
    }

    static String messageMethod(String legacyMethod) {
        if (!v230 || legacyMethod == null) return legacyMethod;
        switch (legacyMethod) {
            case "A": return "C";
            case "B": return "D";
            case "C": return "E";
            case "D": return "F";
            case "E": return "G";
            case "F": return "H";
            case "G": return "I";
            case "H": return "J";
            case "I": return "K";
            case "J": return "L";
            case "K": return "M";
            case "L": return "N";
            case "N": return "O";
            case "O": return "P";
            case "P": return "Q";
            case "Q": return "T";
            case "R": return "U";
            case "S": return "V";
            case "g": return "e";
            case "e": return "f";
            case "f": return "g";
            case "l": return "n";
            case "m": return "q";
            case "n": return "r";
            case "q": return "s";
            case "r": return "t";
            case "s": return "u";
            case "t": return "v";
            case "u": return "w";
            case "v": return "x";
            case "w": return "y";
            case "x": return "z";
            case "y": return "A";
            case "z": return "B";
            default: return legacyMethod;
        }
    }

    static Method publicMessageMethod(Object message, String legacyName,
                                      Class<?>... parameterTypes)
            throws NoSuchMethodException {
        return message.getClass().getMethod(messageMethod(legacyName), parameterTypes);
    }

    static String instanceMethod(Object value, String legacyName) {
        if (value == null) return legacyName;
        String simple = value.getClass().getSimpleName();
        if (v246) return legacyName;
        if (!v230) return legacyName;
        return ("vv".equals(simple) || "tv".equals(simple))
                ? messageMethod(legacyName) : legacyName;
    }

    static boolean simpleNameIs(Object value, String legacyName) {
        return value != null && name(legacyName).equals(value.getClass().getSimpleName());
    }

    static String staticMessageField(Object value, String legacyField) {
        if (value == null || legacyField == null) return legacyField;
        if (v246) return legacyField;
        if (!v230) return legacyField;
        if (!"tv".equals(value.getClass().getSimpleName())
                && !"vv".equals(value.getClass().getSimpleName())) {
            return legacyField;
        }
        if (legacyField.length() != 1) return legacyField;
        char field = legacyField.charAt(0);
        if (field >= 'f' && field < 'z') return String.valueOf((char) (field + 1));
        if (field == 'z') return "A";
        if (field == 'A') return "B";
        return legacyField;
    }
}
