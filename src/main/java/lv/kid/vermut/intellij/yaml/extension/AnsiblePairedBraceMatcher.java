package lv.kid.vermut.intellij.yaml.extension;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lv.kid.vermut.intellij.yaml.AnsibleTokenTypes;

public final class AnsiblePairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] BRACE_PAIRS = {
            new BracePair(AnsibleTokenTypes.LEFT_BRACE, AnsibleTokenTypes.RIGHT_BRACE, true),
            new BracePair(AnsibleTokenTypes.LEFT_DOUBLE_BRACE, AnsibleTokenTypes.RIGHT_DOUBLE_BRACE, true),
            new BracePair(AnsibleTokenTypes.LEFT_SQUARE_BRACE, AnsibleTokenTypes.RIGHT_SQUARE_BRACE, true)
    };

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return BRACE_PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType,
            @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
