package lv.kid.vermut.intellij.yaml.completion.insert;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiDocumentManager;
// import com.jetbrains.php.lang.psi.elements.PhpClass;

/**
 * Adds full namespace when a class name is entered by code completion
 */
public class PhpReferenceInsertHandler implements InsertHandler<LookupElement> {
    private static final PhpReferenceInsertHandler instance = new PhpReferenceInsertHandler();

    public static PhpReferenceInsertHandler getInstance() {
        return instance;
    }

    @Override
    public void handleInsert(InsertionContext context, LookupElement lookupElement) {
        Object object = lookupElement.getObject();
        String classNamespace;

/*		if (object instanceof PhpClass) {
			classNamespace = ((PhpClass) object).getNamespaceName();

		} else */
        classNamespace = "";

        if (!classNamespace.isEmpty()) {
            String fqn = classNamespace;
            if (fqn.startsWith("\\")) {
                fqn = fqn.substring(1);
            }
            context.getDocument().insertString(context.getStartOffset(), fqn);
            PsiDocumentManager.getInstance(context.getProject()).commitDocument(context.getDocument());
        }
    }
}
