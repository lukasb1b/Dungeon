package contrib.configuration;

import com.badlogic.gdx.Input;

import core.configuration.ConfigKey;
import core.configuration.ConfigMap;
import core.configuration.values.ConfigIntValue;

@ConfigMap(path = {"keyboard"})
public class KeyboardConfig {

    public static final ConfigKey<Integer> MOVEMENT_UP =
            new ConfigKey<>(new String[] {"movement", "up"}, new ConfigIntValue(Input.Keys.W));
    public static final ConfigKey<Integer> MOVEMENT_DOWN =
            new ConfigKey<>(new String[] {"movement", "down"}, new ConfigIntValue(Input.Keys.S));
    public static final ConfigKey<Integer> MOVEMENT_LEFT =
            new ConfigKey<>(new String[] {"movement", "left"}, new ConfigIntValue(Input.Keys.A));
    public static final ConfigKey<Integer> MOVEMENT_RIGHT =
            new ConfigKey<>(new String[] {"movement", "right"}, new ConfigIntValue(Input.Keys.D));
    public static final ConfigKey<Integer> INVENTORY_OPEN =
            new ConfigKey<>(new String[] {"inventory", "open"}, new ConfigIntValue(Input.Keys.I));
    public static final ConfigKey<Integer> CLOSE_UI =
            new ConfigKey<>(new String[] {"ui", "close"}, new ConfigIntValue(Input.Keys.ESCAPE));
    public static final ConfigKey<Integer> INTERACT_WORLD =
            new ConfigKey<>(new String[] {"interact", "world"}, new ConfigIntValue(Input.Keys.E));
    public static final ConfigKey<Integer> FIRST_SKILL =
            new ConfigKey<>(new String[] {"skill", "first"}, new ConfigIntValue(Input.Keys.Q));
    public static final ConfigKey<Integer> SECOND_SKILL =
            new ConfigKey<>(new String[] {"skill", "second"}, new ConfigIntValue(Input.Keys.R));

    public static final ConfigKey<Integer> DEBUG_TOGGLE_KEY =
            new ConfigKey<>(new String[] {"debug", "activate"}, new ConfigIntValue(Input.Keys.B));

    public static final ConfigKey<Integer> DEBUG_ZOOM_IN =
            new ConfigKey<>(new String[] {"debug", "zoom_in"}, new ConfigIntValue(Input.Keys.K));

    public static final ConfigKey<Integer> DEBUG_ZOOM_OUT =
            new ConfigKey<>(new String[] {"debug", "zoom_out"}, new ConfigIntValue(Input.Keys.L));

    public static final ConfigKey<Integer> DEBUG_TOGGLE_LEVELSIZE =
            new ConfigKey<>(
                    new String[] {"debug", "toggle_levelsize"}, new ConfigIntValue(Input.Keys.Z));

    public static final ConfigKey<Integer> DEBUG_SPAWN_MONSTER =
            new ConfigKey<>(
                    new String[] {"debug", "spawn_monster"}, new ConfigIntValue(Input.Keys.X));

    public static final ConfigKey<Integer> DEBUG_TELEPORT_TO_START =
            new ConfigKey<>(
                    new String[] {"debug", "teleport_Start"}, new ConfigIntValue(Input.Keys.J));

    public static final ConfigKey<Integer> DEBUG_TELEPORT_TO_END =
            new ConfigKey<>(
                    new String[] {"debug", "teleport_end"}, new ConfigIntValue(Input.Keys.H));

    public static final ConfigKey<Integer> DEBUG_TELEPORT_ON_END =
            new ConfigKey<>(
                    new String[] {"debug", "teleport_onEnd"}, new ConfigIntValue(Input.Keys.G));

    public static final ConfigKey<Integer> DEBUG_TELEPORT_TO_CURSOR =
            new ConfigKey<>(
                    new String[] {"debug", "teleport_cursor"}, new ConfigIntValue(Input.Keys.O));

    public static final ConfigKey<Integer> QUESTLOG =
            new ConfigKey<>(new String[] {"menue", "questlog"}, new ConfigIntValue(Input.Keys.M));
    public static final ConfigKey<Integer> PAUSE =
            new ConfigKey<>(new String[] {"pause", "pause_game"}, new ConfigIntValue(Input.Keys.P));
}
