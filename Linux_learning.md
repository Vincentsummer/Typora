# Linux学习笔记

## linux常用命令总结（Ubuntu）

### 常用软件更新命令

- apt-get install package 安装package
- apt-get install package --reinstall 重新安装包package
- apt-get -f install 修复安装
- apt-get update 更新源
- apt-get upgrade 更新已安装的包
- apt-get dist-upgrade 升级系统
- apt-get remove package 删除包
- apt-get remove package --purge 删除包，包括配置文件等
- apt-get clean && sudo apt-get autoclean 清理无用的包

### 查看Ubuntu系统版本信息

- cat /proc/version	查看linux内核、gcc编译器和Ubuntu版本号
- uname -a	显示linux的内核版本和系统位数
- lsb_release -a	查看ubuntu发行版本号

### 进程相关命令

- ps [pid]	查看进程（特定pid的进程）

- ps a	显示现行终端机下的所有程序，包括其他用户的程序。

- ps -A	显示所有程序。

- kill [pid]	杀死特定进程 （eg：kill －9 324，-9为强制杀死）

  