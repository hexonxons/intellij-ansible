package lv.kid.vermut.intellij.yaml.parser;

import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.YamlLanguage;

public class NeonElementType extends IElementType {
    public NeonElementType(@NotNull String debugName) {
        super(debugName, YamlLanguage.INSTANCE);
    }

    public String toString() {
        return "[Yaml] " + super.toString();
    }
}
