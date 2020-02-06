package lv.kid.vermut.intellij.yaml.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lv.kid.vermut.intellij.yaml.reference.AnsibleReferenceContributor;
import lv.kid.vermut.intellij.yaml.reference.AnsibleUtil;

/**
 * Provides code completion
 */
public class NeonCompletionContributor extends CompletionContributor {
    public NeonCompletionContributor() {
        // extend(CompletionType.BASIC, StandardPatterns.instanceOf(PsiElement.class), new KeywordCompletionProvider());
        extend(CompletionType.BASIC, StandardPatterns.instanceOf(PsiElement.class), new ServiceCompletionProvider());
        // extend(CompletionType.BASIC, StandardPatterns.instanceOf(PsiElement.class), new ClassCompletionProvider());

        extend(CompletionType.BASIC,
                AnsibleReferenceContributor.roleRefPattern(),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                        List<String> names = AnsibleUtil.findRoleNames(parameters.getPosition().getProject(), AnsibleUtil.ALL);
                        for (String name : names) {
                            result.addElement(LookupElementBuilder.create(name));
                        }
                    }
                });
    }
}
