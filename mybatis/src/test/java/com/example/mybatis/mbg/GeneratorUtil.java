package com.example.mybatis.mbg;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GeneratorUtil
 * @Description:
 * @Author songxl
 * @Date 2021/7/5
 * @Version 1.0
 */
public class GeneratorUtil {
    /**
     * 作者
     */
    private static String AUTHOR = "songxl";
    private static DbType DB_TYPE = DbType.MYSQL;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";

    //生成文件根目录
    private static String projectPath = "D://code";
    //是否将根目录更新为工程的根目录【true会覆盖该项目对应目录下的文件、慎用】
    private static boolean updatePath = true;
    //模块名称
    private static String moduleName = "mybatis";

    // 文件生成开关

    //facade生成开关
    private static boolean facadeFlag = true;
    //service生成开关
    private static boolean serviceFlag = true;
    //serviceImpl生成开关
    private static boolean serviceImplFlag = true;
    //mapper.java
    private static boolean mapperJFlag = true;
    //mapperParent.java(数据业务数据库CRUD接口)生成开关
    private static boolean mapperParentJFlag = false;
    //mapper.xml （实体类bean与数据库表字段映射）生成开关
    private static boolean baseMapperXFlag = true;
    //mapper.xml(数据库CRUD sql)生成开关
    private static boolean mapperXFlag = true;
    //entity（表实体类）生成开关
    private static boolean entityFlag = true;

    /**
     * 父包名路径(文件输出路径,也是导包的路径)
     */
    private static String PARENT_PACKAGE_PATH = "/com/example/mybatis";
    /**
     * 生成文件父类包package
     */
    private static String packageName = "com.example.mybatis";


    // 各层包名
    private static String ENTITY_PATH = "/entity/";
    private static String FACADE_PATH = "/facade/";
    private static String MAPPER_PATH = "/mapper/";
    private static String XML_PATH = "/resources/mapper/";
    private static String SERVICE_PATH = "/service/";
    private static String SERVICE_IMPL_PATH = "/service/impl/";
    private static String CONTROLLER_PATH = "/web/";


    // 自定义输出模板和位置
    /**
     * entity输出模板
     */
    private static String ENTITY_TEMPLATE = "freemarker/entity.java.ftl";
    private static String ENTITY_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + ENTITY_PATH;
    /**
     * facade输出模板
     */
    private static String FACADE_TEMPLATE = "freemarker/facade.java.ftl";
    private static String FACADE_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + FACADE_PATH;
    /**
     * mapper.xml输出模板
     */
    private static String XML_TEMPLATE = "freemarker/baseMapper.xml.ftl";
    private static String XML_OUTPUT_PATH = "/src/main" + XML_PATH + "/base/";
    /**
     * mapperExt.xml输出模板
     */
    private static String XMLEXT_TEMPLATE = "freemarker/mapper.xml.ftl";
    private static String XMLEXT_OUTPUT_PATH = "/src/main" + XML_PATH;
    /**
     * mapper.java输出模板
     */
    private static String MAPPER_TEMPLATE = "freemarker/mapper.java.ftl";
    private static String MAPPER_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + MAPPER_PATH;

    /**
     * mapperParent.java输出模板
     */
    private static String BASEMAPPER_TEMPLATE = "freemarker/mapperParent.java.ftl";
    private static String BASEMAPPER_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + MAPPER_PATH + "/parent/";

    /**
     * service输出模板
     */
    private static String SERVICE_TEMPLATE = "freemarker/service.java.ftl";
    private static String SERVICE_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + SERVICE_PATH;
    /**
     * serviceImpl输出模板
     */
    private static String SERVICE_IMPL_TEMPLATE = "freemarker/serviceImpl.java.ftl";
    private static String SERVICE_IMPL_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + SERVICE_IMPL_PATH;
    /**
     * controller输出模板
     */
    private static String CONTROLLER_TEMPLATE = "freemarker/controller.java.ftl";
    private static String CONTROLLER_OUTPUT_PATH = "/src/main/java" + PARENT_PACKAGE_PATH + CONTROLLER_PATH;


    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                // 打开文件
                .setOpen(false)
                //                .setOutputDir("D://code")
                // 文件覆盖
                .setFileOverride(true)
                // 开启activeRecord模式
                .setActiveRecord(false)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap: mapper.xml生成查询映射结果
                .setBaseResultMap(true)
                // XML ColumnList: mapper.xml生成查询结果列
                .setBaseColumnList(false)
                // swagger注解; 须添加swagger依赖
                .setSwagger2(true)
                // 作者
                //                .setAuthor(AUTHOR)
                // 设置实体类名称
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper");
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig(String url, String username, String password) {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(DB_TYPE)
                // 连接驱动
                .setDriverName(driverClassName)
                // 地址
                .setUrl(url)
                // 用户名
                .setUsername(username)
                // 密码
                .setPassword(password)
                // 自定义数据库表字段类型转换【可选】
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //将数据库中datetime转换成date
                        if (fieldType.toLowerCase().contains("datetime")) {
                            return DbColumnType.DATE;
                        }
                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                    }
                });
    }

    /**
     * 策略配置
     */
    private static StrategyConfig strategyConfig(String[] TABLES, String[] ENTITY_IGNORE_PREFIX) {
        return new StrategyConfig()
                // 表名生成策略：下划线连转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表字段生成策略：下划线连转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(TABLES)
                // 生成controller
                .setRestControllerStyle(true)
                // 去除表前缀
                .setTablePrefix(ENTITY_IGNORE_PREFIX)
                // controller映射地址：驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // 是否启用builder 模式
                .setEntityBuilderModel(true)
                // 是否为lombok模型; 需要lombok依赖
                .setEntityLombokModel(true)
                // 生成实体类字段注解
                .setEntityTableFieldAnnotationEnable(true)
                //自定义父类
                .setSuperServiceImplClass(null)
                .setSuperMapperClass(null);
    }

    /**
     * 包配置
     * 设置包路径用于导包时使用，路径示例：com.path
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig()
                // 父包名
                .setParent(packageName)
                .setEntity("entity")
                .setMapper("mapper")
                .setXml("resources.mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("facade");
    }

    /**
     * 模板配置
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig()
                // 置空后方便使用自定义输出位置
                .setEntity(null)
                .setXml(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入配置
            }
        }
                // 判断是否创建文件
                .setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {

                        // 检查文件目录，不存在自动递归创建
                        checkDir(filePath);

                        // 指定需要覆盖的文件
                        // entity或mapper.xml覆盖
                        if (isExists(filePath)) {
                            if (filePath.contains("/entity") || filePath.contains("/service")
                                    || (filePath.endsWith(".xml") && filePath.contains("/base"))) {
                                return true;
                            }
                            return false;
                        }

                        return true;
                    }
                })
                // 自定义输出文件
                .setFileOutConfigList(fileOutConfigList());
    }

    /**
     * @Author songxl
     * @Description 自定义输出文件配置
     * @Date 2021/7/1
     * @Param []
     * @Return java.util.List<com.baomidou.mybatisplus.generator.config.FileOutConfig>
     * @MethodName fileOutConfigList
     */
    private static List<FileOutConfig> fileOutConfigList() {

        List<FileOutConfig> list = new ArrayList<>();
        // 当前项目路径
        if (updatePath) {
            projectPath = System.getProperty("user.dir") + "\\" + moduleName;
        }

        // 实体类文件输出
        if (entityFlag) {
            list.add(new FileOutConfig(ENTITY_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + ENTITY_OUTPUT_PATH);
                    return projectPath + ENTITY_OUTPUT_PATH + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                }
            });
        }
        // facade文件输出 不会覆盖
        if (facadeFlag) {
            list.add(new FileOutConfig(FACADE_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + FACADE_OUTPUT_PATH);
                    return projectPath + FACADE_OUTPUT_PATH + tableInfo.getServiceName().replaceAll("Service", "") + "Facade" + StringPool.DOT_JAVA;
                }
            });
        }

        // mapper xml文件输出
        if (baseMapperXFlag) {
            list.add(new FileOutConfig(XML_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + XML_OUTPUT_PATH);
                    return projectPath + XML_OUTPUT_PATH + "Base" + tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            });
        }

        //mapperExt xml文件输出
        if (mapperXFlag) {
            list.add(new FileOutConfig(XMLEXT_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + XML_OUTPUT_PATH);
                    return projectPath + XMLEXT_OUTPUT_PATH + tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            });
        }

        // mapper文件输出
        if (mapperJFlag) {
            list.add(new FileOutConfig(MAPPER_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + MAPPER_OUTPUT_PATH);
                    return projectPath + MAPPER_OUTPUT_PATH + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                }
            });
        }
        // mapperParent文件输出
        if (mapperParentJFlag) {
            list.add(new FileOutConfig(BASEMAPPER_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + MAPPER_OUTPUT_PATH);
                    return projectPath + BASEMAPPER_OUTPUT_PATH + tableInfo.getMapperName() + "Parent" + StringPool.DOT_JAVA;
                }
            });
        }


        // service文件输出
        if (serviceFlag) {
            list.add(new FileOutConfig(SERVICE_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + SERVICE_OUTPUT_PATH);
                    return projectPath + SERVICE_OUTPUT_PATH + tableInfo.getServiceName() + StringPool.DOT_JAVA;
                }
            });
        }

        // service impl文件输出
        if (serviceImplFlag) {
            list.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    makeDir(projectPath + SERVICE_IMPL_OUTPUT_PATH);
                    return projectPath + SERVICE_IMPL_OUTPUT_PATH + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                }
            });
        }

        // controller文件输出
        //        list.add(new FileOutConfig(CONTROLLER_TEMPLATE) {
        //            @Override
        //            public String outputFile(TableInfo tableInfo) {
        //                makeDir(projectPath + CONTROLLER_OUTPUT_PATH);
        //                return projectPath + CONTROLLER_OUTPUT_PATH + tableInfo.getControllerName() + StringPool.DOT_JAVA;
        //            }
        //        });

        return list;
    }

    /**
     * @Author songxl
     * @Description 创建文件夹
     * @Date 2021/7/1
     * @Param [path]
     * @Return void
     * @MethodName makeDir
     */
    private static void makeDir(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * @Author songxl
     * @Description 判断文件是否存在
     * @Date 2021/7/1
     * @Param [path]
     * @Return boolean
     * @MethodName isExists
     */
    private static boolean isExists(String path) {


        File file = new File(path);
        return file.exists();
    }

    /**
     * @Author songxl
     * @Description 自动生成类方法
     * @Date 2021/7/5
     * @Param [username, password, url, tables, entityIgnorePrefix]
     * @Return void
     * @MethodName create
     */
    public static void create(String username, String password, String url, String[] tables, String[] entityIgnorePrefix) {
        // 全局配置
        GlobalConfig globalConfig = globalConfig();
        // 数据源配置
        DataSourceConfig dataSourceConfig = dataSourceConfig(url, username, password);
        // 策略配置
        StrategyConfig strategyConfig = strategyConfig(tables, entityIgnorePrefix);
        // 包配置
        PackageConfig packageConfig = packageConfig();
        // 模板配置
        TemplateConfig templateConfig = templateConfig();
        // 自定义配置
        InjectionConfig injectionConfig = injectionConfig();

        // 执行
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                // 因为使用了自定义模板,所以需要把各项置空否则会多生成一次
                .setTemplate(templateConfig)
                // 使用的模板引擎，如果不是默认模板引擎则需要添加模板依赖到pom
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setCfg(injectionConfig)
                .execute();
    }
}
