package lv.kid.vermut.intellij.yaml.editor;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lv.kid.vermut.intellij.yaml.YamlLanguage;

/**
 *
 */
public class NeonCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public AnsibleCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new AnsibleCodeStyleSettings(settings);
    }

    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSetting) {
        return new CodeStyleAbstractConfigurable(settings, originalSetting, getConfigurableDisplayName()) {
            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }

            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new SimpleCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    @Override
    public String getConfigurableDisplayName() {
        return YamlLanguage.INSTANCE.getDisplayName();
    }

    private static class SimpleCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public SimpleCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(YamlLanguage.INSTANCE, currentSettings, settings);
        }
    }

    private class AnsibleCodeStyleSettings extends CustomCodeStyleSettings {
        public AnsibleCodeStyleSettings(CodeStyleSettings settings) {
            super("AnsibleCodeStyleSettings", settings);
        }
    }
}

