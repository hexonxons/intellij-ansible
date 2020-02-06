package lv.kid.vermut.intellij.yaml.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.lexer.NeonLexer;
import lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonArrayImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonEntityImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonFileImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonJinjaImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonKeyImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonKeyValPairImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonPsiElementImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonReferenceImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonScalarImpl;
import lv.kid.vermut.intellij.yaml.psi.impl.NeonSectionImpl;

public class NeonParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new NeonLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new NeonParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return NeonElementTypes.FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return NeonTokenTypes.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return NeonTokenTypes.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return NeonTokenTypes.STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        IElementType type = node.getElementType();

        if (type == NeonElementTypes.KEY_VALUE_PAIR) {
            return new NeonKeyValPairImpl(node);
        } else if (type == NeonElementTypes.KEY) {
            return new NeonKeyImpl(node);
        } else if (type == NeonElementTypes.COMPOUND_VALUE) {
            return new NeonArrayImpl(node);
        } else if (type == NeonElementTypes.ARRAY) {
            return new NeonArrayImpl(node);
        } else if (type == NeonElementTypes.SEQUENCE) {
            return new NeonSectionImpl(node);
        } else if (type == NeonElementTypes.SCALAR_VALUE) {
            return new NeonScalarImpl(node);
        } else if (type == NeonElementTypes.ENTITY) {
            return new NeonEntityImpl(node);
        } else if (type == NeonElementTypes.JINJA) {
            return new NeonJinjaImpl(node);
        } else if (type == NeonElementTypes.REFERENCE) {
            return new NeonReferenceImpl(node);
        } else if (type == NeonElementTypes.ARGS) {
            return new NeonArrayImpl(node); // FIXME: will it work?
        } else {
            return new NeonPsiElementImpl(node);
        }
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new NeonFileImpl(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
