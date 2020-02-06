package lv.kid.vermut.intellij.yaml.reference;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.util.ArrayUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lv.kid.vermut.intellij.yaml.psi.NeonKey;
import lv.kid.vermut.intellij.yaml.psi.NeonKeyValPair;

/**
 * Created by Vermut on 16/05/2015.
 */
public class AnsiblePropertiesContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<NeonKeyValPair> properties = AnsibleUtil.findAllProperties(project);
        List<String> names = new ArrayList<String>(properties.size());
        for (NeonKeyValPair property : properties) {
            if (property.getKeyText() != null && property.getKeyText().length() > 0) {
                names.add(property.getKeyText());
            }
        }
        return ArrayUtil.toStringArray(names);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<NeonKey> properties = AnsibleUtil.findAllProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
