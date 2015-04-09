package me.shreyasr.charms;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.Set;

public class ComplexPreferences implements SharedPreferences {

    private static ComplexPreferences complexPreferences;
    private static Gson GSON = new GsonBuilder().registerTypeAdapter(Charm.class, new CharmAdapter()).create();

    public static ComplexPreferences getComplexPreferences(Context context) {
        if (complexPreferences == null)
            complexPreferences = new ComplexPreferences(context);
        return complexPreferences;
    }

    private SharedPreferences preferences;

    private ComplexPreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = preferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            return GSON.fromJson(gson, a);
        }
    }

    @Override
    public ComplexPreferenceEditor edit() {
        return new ComplexPreferenceEditor();
    }

    @Override
    public boolean contains(String key) {
        return preferences.contains(key);
    }

    @Override
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return preferences.getStringSet(key, defValues);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    class ComplexPreferenceEditor implements Editor {

        private SharedPreferences.Editor editor;

        private ComplexPreferenceEditor() {
            editor = preferences.edit();
        }

        public ComplexPreferenceEditor putObject(String key, Object object, Class c) {
            if (object == null)
                throw new IllegalArgumentException("object is null");
            if (key.equals(""))
                throw new IllegalArgumentException("key is empty or null");
            editor.putString(key, GSON.toJson(object, c));
            return this;
        }

        @Override
        public ComplexPreferenceEditor putString(String key, String value) {
            editor.putString(key, value);
            return this;
        }

        @Override
        public ComplexPreferenceEditor putStringSet(String key, Set<String> values) {
            editor.putStringSet(key, values);
            return this;
        }

        @Override
        public ComplexPreferenceEditor putInt(String key, int value) {
            editor.putInt(key, value);
            return this;
        }

        @Override
        public ComplexPreferenceEditor putLong(String key, long value) {
            editor.putLong(key, value);
            return this;
        }

        @Override
        public ComplexPreferenceEditor putFloat(String key, float value) {
            editor.putFloat(key, value);
            return this;
        }

        @Override
        public ComplexPreferenceEditor putBoolean(String key, boolean value) {
            editor.putBoolean(key, value);
            return this;
        }

        @Override
        public ComplexPreferenceEditor remove(String key) {
            editor.remove(key);
            return this;
        }

        @Override
        public ComplexPreferenceEditor clear() {
            editor.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return editor.commit();
        }

        @Override
        public void apply() {
            editor.apply();
        }
    }
}
