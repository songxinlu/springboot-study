package ${package.Mapper}.parent;

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * <p>
    * ${table.comment!} MapperParent 接口
    * </p>
 *
 * @author ${author}
<#--* @since ${date}-->
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName}Parent extends ${superMapperClass}<${entity}> {

}
</#if>
