package lv.kid.vermut.intellij.yaml.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.editor.NeonStructureViewElement;
import lv.kid.vermut.intellij.yaml.psi.NeonScalar;

/**
 *
 */
public class NeonScalarImpl extends NeonPsiElementImpl implements NeonScalar {
    public NeonScalarImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Yaml scalar";
    }

    @Override
    public String getValueText() {
        return getNode().getText();
    }

    @Override
    public ItemPresentation getPresentation() {
        return new NeonStructureViewElement(this);
    }

    @Override
    public String getName() {
        return getValueText();
    }
}
