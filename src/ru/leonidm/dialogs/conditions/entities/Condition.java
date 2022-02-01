package ru.leonidm.dialogs.conditions.entities;

import com.google.common.collect.ImmutableList;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Condition<T> {

    public static final Condition<Double>
            MIN_LEVEL = new Condition<>("minLevel", (v, p) -> v <= p.getLevel(), Double.class),
            MAX_LEVEL = new Condition<>("maxLevel", (v, p) -> v >= p.getLevel(), Double.class);

    public static final ImmutableList<Condition<?>> CONDITIONS;

    static {
        ImmutableList<Condition<?>> outConditions;

        try {
            List<Condition<?>> conditions = new ArrayList<>();

            for(Field field : Condition.class.getDeclaredFields()) {
                if(Modifier.isStatic(field.getModifiers()) && field.getDeclaringClass() == Condition.class) {
                    Object out = field.get(null);
                    if(out != null) conditions.add((Condition<?>) out);
                }
            }

            outConditions = ImmutableList.copyOf(conditions);
        } catch(Exception e) {
            e.printStackTrace();
            outConditions = ImmutableList.copyOf(new Condition<?>[] {});
        }

        CONDITIONS = outConditions;
    }
    private final ConditionPredicate<T> predicate;
    private final Class<T> genericClass;
    private final String toStringValue;

    @Override
    public String toString() {
        return toStringValue;
    }

    private Condition(String toStringValue, ConditionPredicate<T> predicate, Class<T> genericClass) {
        this.toStringValue = toStringValue;
        this.predicate = predicate;
        this.genericClass = genericClass;
    }

    public boolean check(Object condition, Player player) {
        if(!genericClass.isInstance(condition)) return false;

        return predicate.check((T) condition, player);
    }

    public Class<T> getGenericClass() {
        return genericClass;
    }
}
