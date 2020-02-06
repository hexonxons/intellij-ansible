package lv.kid.vermut.intellij.yaml;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes;

/**
 * Matches starting-closing braces in neon
 */
public class YamlBraceMatcher implements PairedBraceMatcher, NeonTokenTypes {
    private static final BracePair[] PAIRS = {
            new BracePair(NEON_LBRACE_JINJA, NEON_RBRACE_JINJA, true), // {{ }}
            new BracePair(NEON_LBRACE_CURLY, NEON_RBRACE_CURLY, true),  // {}
            new BracePair(NEON_LBRACE_SQUARE, NEON_RBRACE_SQUARE, true), // []
    };

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
