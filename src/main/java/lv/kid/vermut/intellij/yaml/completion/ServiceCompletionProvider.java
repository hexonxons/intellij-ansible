package lv.kid.vermut.intellij.yaml.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import lv.kid.vermut.intellij.yaml.psi.NeonArray;
import lv.kid.vermut.intellij.yaml.psi.NeonFile;
import lv.kid.vermut.intellij.yaml.psi.NeonKey;
import lv.kid.vermut.intellij.yaml.psi.NeonReference;
import lv.kid.vermut.intellij.yaml.psi.NeonSection;
import lv.kid.vermut.intellij.yaml.psi.NeonValue;

/*
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPropertyImpl;
import com.jetbrains.php.lang.psi.elements.Field;
import com.jetbrains.php.lang.psi.elements.PhpClass;
*/

/**
 * Complete keywords
 */
public class ServiceCompletionProvider extends CompletionProvider<CompletionParameters> {
    // current element
    PsiElement curr;

    @Override
    protected void addCompletions(@NotNull CompletionParameters params,
            @NotNull ProcessingContext context,
            @NotNull CompletionResultSet result) {
        curr = params.getPosition().getOriginalElement();
        if (curr.getParent() instanceof NeonReference) {
            for (String service : getAvailableServices()) {
                result.addElement(LookupElementBuilder.create(service));
            }
        }
    }

    /**
     * Find all available services
     */
    private List<String> getAvailableServices() {
        List<String> services = new LinkedList<String>();

        getAvailableServicesFromSystemContainer(services);

        if (curr.getContainingFile() instanceof NeonFile) {
            getServicesFromNeonFile(services, (NeonFile) curr.getContainingFile());
        }

        return services;
    }

    /**
     * Scans class SystemContainer to find all services in it
     */
    private void getAvailableServicesFromSystemContainer(List<String> result) {
/*		PhpClass container = PhpIndex.getInstance(curr.getProject()).getClassByName("SystemContainer");
		if (container != null) {
			for (Field field : container.getFields()) {
				if (field instanceof PhpDocPropertyImpl) {
					result.add( field.getName() );
				}
			}
		}*/
    }

    /**
     *
     */
    private void getServicesFromNeonFile(List<String> result, NeonFile file) {
        for (NeonSection section : file.getSections().values()) {
            // without sections, i.e. the section is actually an extension
            if (section.getName().equals("services") && (section.getValue() instanceof NeonArray)) {
                addServiceFromNeonArray(result, (NeonArray) section.getValue());
            }

            if (section.getValue() instanceof NeonArray) {
                HashMap<String, NeonValue> map = ((NeonArray) section.getValue()).getMap();
                if (map.containsKey("services")) {
                    addServiceFromNeonArray(result, (NeonArray) map.get("services"));
                }
            }
        }
    }

    private void addServiceFromNeonArray(List<String> result, NeonArray hash) {
        for (NeonKey key : hash.getKeys()) {
            result.add(key.getKeyText());
        }
    }
}
