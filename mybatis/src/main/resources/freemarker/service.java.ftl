package ${package.Service};

import ${superServiceClassPackage};
import ${package.Entity}.${entity};

/**
 * <p>
 * ${table.comment!} Service类
 * </p>
 <#--* @author ${author}-->
 <#--* @since ${date}-->
 */
<#--<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>-->
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}<#--</#if>-->