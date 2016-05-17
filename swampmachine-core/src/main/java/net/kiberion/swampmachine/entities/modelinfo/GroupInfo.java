/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.entities.modelinfo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 *
 * @author kibertoad
 */
public class GroupInfo extends CommonModelEntityDescriptor {

    @Getter
    private final Map<String, GroupInfo> subGroups = new HashMap<>();

    @Getter
    @Setter
    private ActionInfo defaultAction;
    
    @Setter
    @Getter
    private String defaultActionCode; //used to lazily load actions

}
