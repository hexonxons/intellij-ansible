package lv.kid.vermut.intellij.yaml.editor;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import lv.kid.vermut.intellij.yaml.lexer.NeonHighlightingLexer;
import lv.kid.vermut.intellij.yaml.lexer.NeonLexer;

import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_ASSIGNMENT;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_COMMENT;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_HEADER;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_IDENTIFIER;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_ITEM_DELIMITER;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_KEY;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_KEYWORD;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_LBRACE_CURLY;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_LBRACE_JINJA;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_LBRACE_SQUARE;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_RBRACE_CURLY;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_RBRACE_JINJA;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_RBRACE_SQUARE;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_STRING;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_TAG;
import static lv.kid.vermut.intellij.yaml.lexer.NeonTokenTypes.NEON_UNKNOWN;
import static lv.kid.vermut.intellij.yaml.parser.NeonElementTypes.JINJA;

public class NeonSyntaxHighlighter extends SyntaxHighlighterBase {

    // Static container
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();
    public static final String UNKNOWN_ID = "Bad character";
    public static final TextAttributesKey UNKNOWN = TextAttributesKey.createTextAttributesKey(UNKNOWN_ID, HighlighterColors.BAD_CHARACTER);
    public static final String COMMENT_ID = "Comment";
    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(COMMENT_ID, DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final String IDENTIFIER_ID = "Identifier";
    public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(IDENTIFIER_ID, DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final String INTERPUNCTION_ID = "Interpunction";
    public static final TextAttributesKey INTERPUNCTION = TextAttributesKey.createTextAttributesKey(INTERPUNCTION_ID, DefaultLanguageHighlighterColors.DOT);
    public static final String NUMBER_ID = "Number";
    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(NUMBER_ID, DefaultLanguageHighlighterColors.NUMBER);
    public static final String KEYWORD_ID = "Keyword";
    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(KEYWORD_ID, DefaultLanguageHighlighterColors.KEYWORD);
    public static final String STRING_ID = "STRING";
    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(STRING_ID, DefaultLanguageHighlighterColors.STRING);
    public static final String TAG_ID = "TAG";
    public static final TextAttributesKey TAG = TextAttributesKey.createTextAttributesKey(TAG_ID, DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    public static final String REFERENCE_ID = "REFERENCE";
    public static final TextAttributesKey REFERENCE = TextAttributesKey.createTextAttributesKey(REFERENCE_ID, DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);
    public static final String JINJA_ID = "JINJA";
    public static final TextAttributesKey JINJA_CODE = TextAttributesKey.createTextAttributesKey(JINJA_ID, DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);
    // Groups of IElementType's
    public static final TokenSet sBAD = TokenSet.create(NEON_UNKNOWN);
    public static final TokenSet sCOMMENTS = TokenSet.create(NEON_COMMENT);
    public static final TokenSet sIDENTIFIERS = TokenSet.create(NEON_KEY);
    public static final TokenSet sINTERPUNCTION = TokenSet.create(NEON_LBRACE_CURLY, NEON_RBRACE_CURLY, NEON_LBRACE_SQUARE, NEON_RBRACE_SQUARE, NEON_ITEM_DELIMITER, NEON_ASSIGNMENT);
    public static final TokenSet sKEYWORDS = TokenSet.create(NEON_KEYWORD);
    public static final TokenSet sSTRINGS = TokenSet.create(NEON_STRING);
    public static final TokenSet sTAGS = TokenSet.create(NEON_TAG, NEON_HEADER);
    public static final TokenSet sJINJA = TokenSet.create(JINJA, NEON_LBRACE_JINJA, NEON_RBRACE_JINJA, NEON_IDENTIFIER);

    // Fill in the map
    static {
        fillMap(ATTRIBUTES, sBAD, UNKNOWN);
        fillMap(ATTRIBUTES, sCOMMENTS, COMMENT);
        fillMap(ATTRIBUTES, sIDENTIFIERS, IDENTIFIER);
        fillMap(ATTRIBUTES, sINTERPUNCTION, INTERPUNCTION);
        fillMap(ATTRIBUTES, sKEYWORDS, KEYWORD);
        fillMap(ATTRIBUTES, sSTRINGS, STRING);
        fillMap(ATTRIBUTES, sTAGS, TAG);
        fillMap(ATTRIBUTES, sJINJA, JINJA_CODE);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new NeonHighlightingLexer(new NeonLexer());
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        return pack(ATTRIBUTES.get(type));
    }
}
