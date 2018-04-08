配置JAVA_HOME不生效
参考
http://blog.csdn.net/qq_32620163/article/details/78132703
http://blog.csdn.net/tooky_poom/article/details/60768458
1.可以删除文件
C:\Windows\System32
or
C:\ProgramData\Oracle\Java\javapath
java.exe、javaw.exe、javaws.exe
2.我的做法是将环境变量path里面的值位置调整
主要是将%JAVA_HOME%\bin;调整到C:\ProgramData\Oracle\Java\javapath;前面
原因是oracle是后来装的
%ANT_HOME%\bin;%M2_HOME%\bin;%JAVA_HOME%\bin;%HADOOP_HOME%\bin;C:\ProgramData\Oracle\Java\javapath;C:\Python27;C:\app\cWX385453\product\11.1.0\db_1\bin;C:\Program Files (x86)\Common Files\NetSarang;%CATALINA_HOME%\bin;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem;%SYSTEMROOT%\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Citrix\ICAService\;C:\Program Files (x86)\Citrix\System32\;C:\Program Files\TortoiseSVN\bin;C:\Program Files (x86)\Subversion\bin;C:\Program Files\TortoiseGit\bin
