package lv.kid.vermut.intellij.yaml;

import com.intellij.psi.tree.IElementType;

public final class AnsibleTokenTypes {
    public static final IElementType LEFT_BRACE = new IElementType("{", YamlLanguage.INSTANCE);
    public static final IElementType RIGHT_BRACE = new IElementType("}", YamlLanguage.INSTANCE);

    public static final IElementType LEFT_DOUBLE_BRACE = new IElementType("{{", YamlLanguage.INSTANCE);
    public static final IElementType RIGHT_DOUBLE_BRACE = new IElementType("}}", YamlLanguage.INSTANCE);

    public static final IElementType LEFT_SQUARE_BRACE = new IElementType("[", YamlLanguage.INSTANCE);
    public static final IElementType RIGHT_SQUARE_BRACE = new IElementType("]", YamlLanguage.INSTANCE);
}
