package ru.leonidm.dialogs.conditions;

import ru.leonidm.dialogs.conditions.entities.Condition;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final HashMap<String, Map<Condition<?>, Object>> dialogs = new HashMap<>();

    public static void clearDialogs() {
        dialogs.clear();
    }

    public static void putInDialogs(String dialogName, Map<Condition<?>, Object> conditions) {
        dialogs.put(dialogName, conditions);
    }

    public static Map<Condition<?>, Object> getConditions(String dialogName) {
        return dialogs.get(dialogName);
    }
}
