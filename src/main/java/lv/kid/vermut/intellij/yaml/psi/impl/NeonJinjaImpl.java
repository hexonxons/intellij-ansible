package lv.kid.vermut.intellij.yaml.psi.impl;

import com.intellij.lang.ASTNode;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.psi.NeonJinja;

/**
 * Created by Pavels.Veretennikovs on 2015.05.20..
 */
public class NeonJinjaImpl extends NeonPsiElementImpl implements NeonJinja {
    public NeonJinjaImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Yaml Jinja2";
    }
}
