package lv.kid.vermut.intellij.yaml.extension;

import org.jetbrains.yaml.YAMLCodeStyleSettingsProvider;

import lv.kid.vermut.intellij.yaml.YamlLanguage;

public final class AnsibleCodeStyleSettingsProvider extends YAMLCodeStyleSettingsProvider {
    @Override
    public String getConfigurableDisplayName() {
        return YamlLanguage.INSTANCE.getDisplayName();
    }
}
