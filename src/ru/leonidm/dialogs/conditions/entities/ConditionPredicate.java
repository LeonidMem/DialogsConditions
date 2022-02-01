package ru.leonidm.dialogs.conditions.entities;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface ConditionPredicate<T> {

    boolean check(T condition, Player player);
}
