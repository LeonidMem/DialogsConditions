package ru.leonidm.dialogs.conditions;

import org.bukkit.entity.Player;
import ru.leonidm.dialogs.api.decorators.DialogDecorator;
import ru.leonidm.dialogs.conditions.entities.Condition;

import java.util.Map;

public class DialogsConditionsChecker implements DialogDecorator {

    @Override
    public boolean checkDialog(Player player, String dialogName) {
        Map<Condition<?>, Object> conditions = Utils.getConditions(dialogName);
        if(conditions == null) return true;

        for(Map.Entry<Condition<?>, Object> entry : conditions.entrySet()) {
            Condition<?> condition = entry.getKey();
            Object conditionValue = entry.getValue();

            if(!condition.check(conditionValue, player)) return false;
        }

        return true;
    }
}
