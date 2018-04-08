1、下载hadoop的包，解压
参照此：http://blog.csdn.net/fengshuiyue/article/details/50886840
2、讲目录下的项目导入eclipse
hadoop-1.2.1\src\contrib\eclipse-plugin
3、如果导入插件不显示
http://blog.csdn.net/davidhsing/article/details/5758428
eclipse/configuration/org.eclipse.update 删除掉
插件删掉
工作空间下面的插件文件也删掉WorkSpace\WS_Maven\.metadata\.plugins\org.apache.hadoop.eclipse
然后
cmd -->cd 到eclipse的home -->输入命令：eclipse –clean --> 重启eclipse


注意点：
特别注意 行的逗号,
Bundle-ClassPath: classes/,
 lib/hadoop-core.jar,
 lib/

