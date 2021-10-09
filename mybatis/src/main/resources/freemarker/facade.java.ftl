package ${package.Controller};

import ${package.ServiceImpl}.${table.serviceImplName};
import org.springframework.stereotype.Component;

/**
 * <p>
 * 表名：${table.name} 业务类
 * </p>
 <#--* @author ${author}-->
 <#--* @since ${date}-->
 */
@Component
<#--<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>-->
public class ${entity}Facade extends ${table.serviceImplName} {

}<#--</#if>-->