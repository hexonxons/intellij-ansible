package lv.kid.vermut.intellij.yaml.editor;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings.IndentOptions;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.YamlLanguage;

/**
 * Code style settings (tabs etc)
 */
public class YamlLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "product:\n  name: Yaml\n  version: 4\n  vendor: vermut@kid.lv\n  url: \"https://github.com/vermut/intellij-ansible/\"";
    }

    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings defaultSettings = new CommonCodeStyleSettings(YamlLanguage.INSTANCE);
        IndentOptions indentOptions = defaultSettings.initIndentOptions();
        indentOptions.INDENT_SIZE = 2;
        indentOptions.TAB_SIZE = 2;
        indentOptions.USE_TAB_CHARACTER = false;
        indentOptions.SMART_TABS = false;

        return defaultSettings;
    }

    @Override
    @NotNull
    public Language getLanguage() {
        return YamlLanguage.INSTANCE;
    }

    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new IndentOptionsEditor();
    }
}
