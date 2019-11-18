# ROS学习

## 基础操作

### 管理环境

```bash
# 配置脚本环境，rosname表示ros的版本
source /opt/ros/<rosname>/setup.bash
```

### 创建ROS工作空间

```bash
mkdir -p ~/catkin_ws/src
cd ~/catkin_ws/src
cd ~/catkin_ws/
catkin_make
source devel/setup.bash
```

### 编译ROS程序包

```bash
# source 环境配置文件
source /opt/ros/<rosname>/setup.bash
# 在catkin工作空间下
catkin_make [make_targets] [-DCMAKE_VARIABLES=...]
```

### 启动ros

```bash
roscore
```

### 运行launch文件

```bash
roslaunch <rosbag_name> <rosbag_launch>.launch
```

### 播放rosbag文件

```bash
rosbag play --clock *.bag
```

### 使用rxplot画时间趋势曲线

rxplot命令就能够看到随时间变化的值的曲线。

```bash
rqt_plot /topic/data #单个话题，单个数据
rqt_plot /topic/x:y:z # 单个话题，向量数据
rqt_plot /topic/x /topic/y /topic/z
rosrun rqt_plot rqt_plot
```

### 运行rviz

```bash
rosrun rviz rviz
```

### 查看tf_tree

```bash
rosrun rqt_tf_tree rqt_tf_tree
```

### 查看话题和节点

```bash
# 观察节点与话题的关系 绿色和蓝色的是节点 红色的是话题
rosrun rqt_graph rqt_graph
```



## Gazebo仿真

### 启动Gazebo并加载机器人、环境模型

```bash
roslaunch turtlebot_gazebo turtlebot_world.launch
```

### 启动键盘遥控节点

```bash
roslaunch turtlebot_teleop keyboard_teleop.launch --screen
```

### 运行算法包（gmapping）

```bash
roslaunch turtlebot_gazebo gmapping_demo.launch
```

### 开启rviz

```bash
roslaunch turtlebot_rviz_launchers view_navigation.launch
```

## TurtleBot3

### 建图

```bash
export TURTLEBOT3_MODEL=burger #每开一个新的终端都要设置一下这个环境变量，除了burger也可以是waffle或waffle_pi
roslaunch turtlebot3_gazebo turtlebot3_house.launch #这里也可以选择其他的环境，或者自己搭建一个虚拟环境
roslaunch turtlebot3_teleop turtlebot3_teleop_key.launch #建图时要切换到这个终端，用键盘控制turtlebot运动
roslaunch turtlebot3_slam turtlebot3_slam.launch slam_methods:=karto #这里也可以选择其他的slam方法
```

### 导航 

```bash
roslaunch turtlebot3_gazebo turtlebot3_house.launch
roslaunch turtlebot3_navigation turtlebot3_navigation.launch map_file:=/home/jay/maps/housemap/housemap.yaml
```

### 保存地图

```bash
mkdir -p ~/maps/housemap
rosrun map_server map_saver -f ~/maps/housemap/housemap
```

### 保存成rosbag文件

当发布话题的节点运行后，可以通过`rostopic list` 列出当前运行的话题，然后记录。当消息记录完成后，结束ctrl+c终止record的命令行，在新建的bagfile文件中会生成`年-月-日-时-分-秒.bag`文件。

```bash
mkdir bagfile
cd bagfile
# 记录所有的话题
rosbag record -a 
# 重现
cd bagfile
rosbag info <file_name>
# 回放
cd bagfile
rosbag play <bagfile_name>
# 选定话题记录
rostopic list -v
rosbag record -O subset /turtle1/cmd_vel /turtle1/pose
# bag文件转txt
rostopic echo -b file_name.bag -p /topic_name > Txt_name.txt
# 过滤topic
rosbag filter <your bagfile> new.bag 'topic == "/turtle1/command_velocity"'
```

