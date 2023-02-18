1、如何运行
解压文件到文件夹中，打开idea，左上角open->选择项目文件夹打开
idea左上角Flie->setting->Build... ->Buil Tools -> Maven -> 右边Maven home directory选择Bundled，其他默认
左上角File->setting->Build,Execution,Deployment->Compiler->Java Compiler 右边点击'+'添加模块，Project bytecode version选择8 点击apply
File->Project Structure->Modules->右边Sources->Language level选择8->Dependencies中的ModuleSDK选择Project SDK，没有的话选择8

点击navicat连接本地3306端口，右键选择新建数据库，名称填book点击确定。
右键数据库运行SQL文件，选择文件夹中的bill_system.sql

找到src.main.java下的application,点击窗口中的绿色三角形运行即可

