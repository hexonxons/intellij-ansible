package lv.kid.vermut.intellij.yaml.editor;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Icon;

import lv.kid.vermut.intellij.yaml.YamlIcons;
import lv.kid.vermut.intellij.yaml.psi.NeonArray;
import lv.kid.vermut.intellij.yaml.psi.NeonFile;
import lv.kid.vermut.intellij.yaml.psi.NeonKey;
import lv.kid.vermut.intellij.yaml.psi.NeonKeyValPair;
import lv.kid.vermut.intellij.yaml.psi.NeonValue;

/**
 *
 */
public class NeonStructureViewElement extends PsiTreeElementBase<PsiElement> {

    public NeonStructureViewElement(PsiElement element) {
        super(element);
    }

    private void addArrayElements(List<StructureViewTreeElement> elements, PsiElement firstValue) {
        for (PsiElement child : firstValue.getChildren()) {
            elements.add(new NeonStructureViewElement(child));
        }
    }

    @Override
    @Nullable
    public String getPresentableText() {
        PsiElement element = getElement();
        if (element instanceof NeonFile) {
            return ((NeonFile) element).getName();
        } else if (element instanceof NeonArray) {
            return "array";
        } else if (element instanceof NeonKeyValPair) {
            return ((NeonKeyValPair) element).getKeyText();
        } else if (element instanceof NeonKey) {
            return ((NeonKey) element).getKeyText();
        } else if (element instanceof NeonValue) {
            return element.getText();
        } else {
            return "? YAML";
        }
    }

    @Nullable
    @Override
    public Icon getIcon(boolean open) {
        return YamlIcons.FILETYPE_ICON;
    }

    @Override
    @Nullable
    public String getLocationString() {
        PsiFile containingFile = getElement().getContainingFile();

        return "("
                + containingFile.getParent().getParent().getName() + "/"
                + containingFile.getParent().getName() + "/"
                + containingFile.getName() + ")";
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        List<StructureViewTreeElement> elements = new ArrayList<StructureViewTreeElement>();
        PsiElement element = getElement();

        if (element instanceof NeonFile) {
            if (element.getChildren().length == 1 && element.getChildren()[0] instanceof NeonArray) { // top level array -> show it's elements
                addArrayElements(elements, element.getChildren()[0]);
            } else { // file children directly
                addArrayElements(elements, element);
            }
        } else if (element instanceof NeonKeyValPair && ((NeonKeyValPair) element).getValue() instanceof NeonArray) {
            addArrayElements(elements, ((NeonKeyValPair) element).getValue());
        } else if (element instanceof NeonArray) {
            addArrayElements(elements, element);
        }

        return elements;
    }
}
