/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.entities.modelinfo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 *
 * @author kibertoad
 */
public class GroupDescriptor extends CommonModelEntityDescriptor {

    @Getter
    private final Map<String, GroupDescriptor> subGroups = new HashMap<>();

}
