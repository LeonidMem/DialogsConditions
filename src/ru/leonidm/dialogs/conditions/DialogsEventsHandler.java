package ru.leonidm.dialogs.conditions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.leonidm.dialogs.api.events.DialogLoadEvent;
import ru.leonidm.dialogs.api.events.DialogsPreReloadEvent;
import ru.leonidm.dialogs.conditions.entities.Condition;

import java.util.HashMap;
import java.util.Map;

import static ru.worldm.library.files.MapHandler.*;

public class DialogsEventsHandler implements Listener {

    @EventHandler
    public void onDialogsReload(DialogsPreReloadEvent e) {
        Utils.clearDialogs();
    }

    @EventHandler
    public void onDialogLoad(DialogLoadEvent e) {
        Map<String, Object> dialogInfo = e.getMap();

        Map<String, Object> conditions = getMap(dialogInfo, "conditions", Object.class, false);
        if(conditions == null) return;

        Map<Condition<?>, Object> outConditions = new HashMap<>();

        for(Condition<?> condition : Condition.CONDITIONS) {
            Object conditionValue = get(conditions, condition.toString(), condition.getGenericClass());
            if(conditionValue == null) continue;

            outConditions.put(condition, conditionValue);
        }

        if(outConditions.isEmpty()) return;

        Utils.putInDialogs(e.getDialogName(), outConditions);
    }
    
}
