--Linux-----------
--Ubuntu
sudo passwd root 修改root密码

#ps -e | grep ssh 如果服务已经启动，则可以看到“sshd”，否则表示没有安装服务，或没有开机启动

Ubuntu安装ssh服务，输入命令：#sudo apt-get install openssh-server 

SSH服务器拒绝了密码，xshell连不上虚拟机怎么办
应该是sshd的设置不允许root用户用密码远程登录
修改 vim /etc/ssh/sshd_config
找到# Authentication:
LoginGraceTime 120
PermitRootLogin without passwd
StrictModes yes
改成
# Authentication:
LoginGraceTime 120
PermitRootLogin yes
StrictModes yes
重启虚拟机 or /etc/init.d/ssh restart

按照JDK
javac失败后有提示
apt-get install openjdk-7-jdk 
javac成功

设置JDK环境变量
vim /etc/profile
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

修改主机名
/etc/hostname与/etc/hosts

下载hadoop
wget 下载路径
--hadoop
下载路径
http://mirrors.shu.edu.cn/apache/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz

mv hadoop-1.2.1.tar.gz /opt/
cd /opt/
tar -zxvf hadoop-1.2.1.tar.gz		解压缩
cd hadoop-1.2.1/conf			里面有四个文件需要配置的 hadoop-env.sh core-site.xml hdfs-site.xml mapred-site.xml

1、hadoop-env.sh
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/

2、core-site.xml
<!-- core-site.xml -->
<property>
<!-- hadoop的工作目录-->
<name>hadoop.tmp.dir</name>
<value>/hadoop</value>
</property>
<property>
<!-- 所有元数据的目录-->
<name>dfs.name.dir</name>
<value>/hadoop/name</value>
</property>
<property>
<!-- 文件系统的访问路径-->
<name>fs.default.name</name>
<value>hdfs://cwp-vm:9000</value>
</property>

3、hdfs-site.xml
<property>
<!--文件系统数据存放目录-->
<name>dfs.data.dir</name>
<value>/hadoop/data</value>
</property>

4、mapred-site.xml
<configuration>
<property>
<!-- 任务调度器如何访问-->
<name>mapred.job.tracker</name>
<value>cwp-vm:9001</value>
</property>
</configuration>

5、添加hadoop_home的环境变量
/etc/profile
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export JRE_HOME=$JAVA_HOME/jre
export HADOOP_HOME=/opt/hadoop-1.2.1
export CLASSPATH=$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$PATH

6、source /etc/profile

7、hadoop namenode -format 先格式化namenode节点

8、start-all.sh  启动hadoop -查看有哪些进程在可以使用命令jps

9、hadoop fs -ls /	看文件系统中有哪些目录


--Maven--
1、安装tomcat，下载解压
tar -zxvf apache-tomcat-8.0.49
2、配置环境变量
vim /etc/profile
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export JRE_HOME=$JAVA_HOME/jre
export HADOOP_HOME=/opt/hadoop-1.2.1
export TOMCAT_HOME=/opt/apache-tomcat-8.0.49
export CLASSPATH=$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$TOMCAT_HOME/bin:$PATH

source profile

-----------------------------------------------
-----Hadoop安装---
下载hadoop的url：http://mirror.bit.edu.cn/apache/hadoop/common/ 
or https://mirrors.tuna.tsinghua.edu.cn/apache/hadoop/common/

eclipse的hadoop插件下载：https://github.com/winghc/hadoop2x-eclipse-plugin 
把hadoop-eclipse-plugin-2.6.0.jar放到eclipse的目录下eclipse\plugins 重启eclipse
上面需要注意插件的版本要和hadoop的版本一致
http://blog.csdn.net/fengshuiyue/article/details/50886840
可以自己构建
https://www.cnblogs.com/zhangchao0515/p/7099002.html

windows下安装Hadoop
https://www.cnblogs.com/jun1019/p/6271303.html
安装：http://blog.csdn.net/u011118321/article/details/68957835

windows下安装2.8.3
http://blog.csdn.net/rav009/article/details/70214788
http://blog.csdn.net/a327919006/article/details/73741819

下载https://github.com/chenweipeng1993/winutils 有对应的版本
配置JAVA_HOME、HADOOP_HOME、path
复制D:\hadoop\winutils-master\hadoop-2.8.3\bin到D:\hadoop\hadoop-2.8.3\bin 下替换
复制D:\hadoop\winutils-master\hadoop-2.8.3\bin\hadoop.dll到C:\Windows\System32下
修改D:\hadoop\hadoop-2.8.3\etc\hadoop下的文件五个hadoop-env.cmd、core-site.xml、hdfs-site.xml、mapred-site.xml、yarn-site.xml
1、hadoop-env.cmd
修改--特別要注意一下jdk的位數，在64為的windows上就要配置jdk也要是64位的
set JAVA_HOME=C:\PROGRA~1\Java\jdk1.8.0_45
注意用 "C:\PROGRA~1" 来表示 "C:\Program Files", 否则会报错, 这是一个坑.最好文件夹不用空格
2、core-site.xml
<configuration>
	<property>    
       <name>fs.default.name</name>  
       <value>hdfs://localhost:9000</value>  
    </property> 
      
    <property>  
       <name>hadoop.tmp.dir</name>  
       <value>/D:/hadoop/hadoop-2.8.3/hadoop/tmp</value>  
    </property>  
    <property>  
        <name>dfs.name.dir</name>  
        <value>/D:/hadoop/hadoop-2.8.3/hadoop/name</value>  
    </property>  
</configuration>

or

<configuration>
	<property>    
       <name>fs.default.name</name>  
       <value>hdfs://localhost:9000</value>  
    </property> 
    <property>  
       <name>hadoop.tmp.dir</name>  
       <value>/D:/hadoop/hadoop-2.8.3/dfs/tmp</value>  
    </property>  
</configuration>
3、hdfs-site.xml
<configuration>
	<property>  
		<name>dfs.replication</name>  
		<value>1</value>  
    </property>  
    <property>  
        <name>dfs.data.dir</name>  
        <value>/D:/hadoop/hadoop-2.8.3/hadoop/data</value>  
    </property>
</configuration>

or 在hadoop所在盘符下的hadoop/data/dfs/目录下

<configuration>
	<property>  
        <name>dfs.replication</name>  
        <value>1</value>  
    </property>
	<property>
		<name>dfs.namenode.name.dir</name>
		<value>file:/hadoop/hadoop-2.8.3/dfs/namenode</value>
	</property>
	<property>
		<name>dfs.datanode.data.dir</name>
		<value>file:/hadoop/hadoop-2.8.3/dfs/datanode</value>
	</property>
</configuration>
4、mapred-site.xml
<configuration>
	<property>  
       <name>mapreduce.framework.name</name>  
       <value>yarn</value>  
    </property>
	<property>
		<!-- 任务调度器如何访问-->
		<name>mapred.job.tracker</name>
		<value>localhost:9001</value>
	</property>	
</configuration>
5、yarn-site.xml
<configuration>
	<property>  
       <name>yarn.nodemanager.aux-services</name>  
       <value>mapreduce_shuffle</value>  
    </property>  
    <property>  
       <name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>  
       <value>org.apache.hadoop.mapred.ShuffleHandler</value>  
    </property>  
      
    <property>  
       <name>yarn.resourcemanager.hostname</name>  
       <value>localhost</value>  
    </property>
</configuration>

执行hadoop namenode -format
cd ..\sbin
start-all.cmd

打开的窗口不要关闭，关闭了就会将进程结束的，可以通过jps查看
D:\hadoop\hadoop-2.8.3\sbin>jps
1088 ResourceManager
5664 DataNode
9700 Jps
9736 NodeManager
1116 NameNode

关闭stop-all.cmd

前台通过http://localhost:8088/cluster/

hadoop=hdfs+mapreduce
hdfs分布式存储 mapreduce分布式计算
hdfs 数据块 namenode datanode 三个组成
数据块 默认64MB 2.x版本是128MB
namenode 主 1个 存储datanode的映射和目录 是管理节点，存放文件元数据的
1、文件与数据块的映射表 2、数据块与数据节点的映射表
datanode 从 多个 存储和检索数据块 工作节点，存放数据块

yarn 资源管理器 调度mapreduce
分为三部resourcemanager、applicationmaster、nodemanager
1、resourcemanager 
1.1、分配和调度资源 1.2、启动和监控applicationmaster 1.3、监控nodemanager
2、applicationmaster
2.1、为MR类型的程序申请资源，并分配给内部任务 2.2、负责数据的切分 2.3、监控任务的执行和容错
3、nodemanager
3.1、管理单个节点的资源 3.2、处理来自resourcemanager的命令 3.3、处理来自applicationmaster的命令

mapreduce编程模型 并行处理框架 实现任务分解和调度
例如
输入一个大文件，通过split之后，将其分为多个分片
每个文件分片由单独的机器去处理，这就是map方法
将各个机器计算的结果进行汇总并得到最终的结果，这就是reduce方法

hbase 高可靠 分布式数据库 nosql 无事务 面向列 可扩展 存储非结构化和半结构化的数据 也支持结构化的数据
rowkey：数据唯一标识，按字典排序待补充
Column family：列族，多个列的集合，最多不要超过三个
可以用来存储hdfs不存储的小文件-需要进行开发的
TimeStamp时间戳：支持多版本数据同时存在

Spark 待补充
大数据分布式框架 hadoop和stream storm？
基于内存计算的大数据并行计算框架
Spark是MapReduce的替代方案，兼容HDFS，HIVE等数据源 和 常见的数据源Oracle
抽象出分布式内存存储数据结构 弹性分布式数据集RDD
基于时间驱动，通过线程池服用线程提高性能

hive sql -> hadoop任务

eclipse环境准备
http://blog.csdn.net/qq_33813365/article/details/70214484
JDK版本的要求
Hadoop 2.7 以及之后的版本，需要JDK 7；
Hadoop 2.6 以及之前的版本，支持JDK 6；

对于Hadoop1.x.x版本，只需要引入1个jar：
hadoop-core

对于Hadoop2.x.x版本，需要引入4个jar：
hadoop-common
hadoop-hdfs
hadoop-mapreduce-client-core
hadoop-client
jdk.tools（一般需要引入，否则报错）

通过命令创建文件夹-要注意根目录/
hadoop fs -mkdir /input 创建文件夹
hadoop fs -ls /
hadoop fs -rm -r /input 删除文件夹

案例WordCount
详解：https://www.cnblogs.com/jun1019/p/6271303.html
http://blog.csdn.net/litianxiang_kaola/article/details/71154302

FAQ汇总
1、Hadoop已经启动好了，但我在配置Eclipse开发环境时，DFS Locations下面的文件夹总是显示为0
解决方法：使用eclipse-jee-oxygen-2-win32-x86_64版本，低版本的可能有问题

2、执行wordcount失败，没有日志，只是返回结果false
解决方法：类导包错误，Text.class 是org.apache.hadoop.io下面的

























