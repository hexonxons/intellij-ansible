package lv.kid.vermut.intellij.yaml.editor;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

import org.jetbrains.annotations.NotNull;

import lv.kid.vermut.intellij.yaml.psi.NeonFile;

/**
 * Structure view
 */
public class NeonStructureViewFactory implements PsiStructureViewFactory {
    @Override
    public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
        if (!(psiFile instanceof NeonFile)) {
            return null;
        }

        return new TreeBasedStructureViewBuilder() {
            @NotNull
            @Override
            public StructureViewModel createStructureViewModel(Editor editor) {
                return new StructureViewModelBase(psiFile, new NeonStructureViewElement(psiFile));
            }

            @NotNull
            public StructureViewModel createStructureViewModel() {
                return new StructureViewModelBase(psiFile, new NeonStructureViewElement(psiFile));
                //				return new NeonStructureViewModel(file);
            }
        };
    }
}
