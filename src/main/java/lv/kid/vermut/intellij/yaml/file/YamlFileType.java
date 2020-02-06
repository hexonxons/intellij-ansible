package lv.kid.vermut.intellij.yaml.file;

import com.intellij.openapi.fileTypes.LanguageFileType;

import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

import lv.kid.vermut.intellij.yaml.Yaml;
import lv.kid.vermut.intellij.yaml.YamlIcons;
import lv.kid.vermut.intellij.yaml.YamlLanguage;

public class YamlFileType extends LanguageFileType {
    public static final YamlFileType INSTANCE = new YamlFileType();
    public static final String DEFAULT_EXTENSION = "yaml";
    public static final String EXTENSIONS = "yml;yaml";

    protected YamlFileType() {
        super(YamlLanguage.INSTANCE);
    }

    @Override
    @NotNull
    public String getName() {
        return Yaml.LANGUAGE_NAME;
    }

    @Override
    @NotNull
    public String getDescription() {
        return Yaml.LANGUAGE_DESCRIPTION;
    }

    @Override
    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    @NotNull
    public Icon getIcon() {
        return YamlIcons.FILETYPE_ICON;
    }
}

