package ${package.Mapper};

import ${superMapperClassPackage};
import ${package.Entity}.${entity};

/**
 * <p>
 * 表名：${table.name} Mapper接口
 * </p>
<#--* @author ${author}-->
<#--* @since ${date}-->
 */
<#--<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>-->
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}<#--</#if>-->