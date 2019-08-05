[TOC]

# 常用软件安装笔记

## Anaconda安装相关问题（win10）

### 镜像设置

- 显示原来的镜像源  conda config --show

- 添加新镜像源

  - 添加清华镜像1：https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/

    ```
    conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
    ```

  - 添加清华镜像2：https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/

    ```
    conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/
    ```

  - 添加中科大镜像：https://mirrors.ustc.edu.cn/anaconda/pkgs/free/

    ```
    conda config --add channels https://mirrors.ustc.edu.cn/anaconda/pkgs/free/
    ```

- 设置显示源  conda config  --setshow_channel_urls yes

- 检查channels是否成功 conda config  --show channels

- 删除旧镜像源 conda config --remove channels https://...

### 更改python版本

- 改变anaconda默认python版本（3.7）：

- 首先创建一个名为python36的环境，指定的Python版本是3.6

  ```html
  conda create --name python36 python=3.6
  ```

- 安装好后，使用activate激活某个环境

  ```python
  activate python36 # for Windows
  source activate python36 # for Linux & Mac
  ```

- 返回默认的python 3.7环境，运行

```python
deactivate # for Windows
source deactivate python36 # for Linux & Mac
```

- 删除一个已有的环境：

```python
conda remove --name python36 --all
```



## Tensorflow安装相关问题(win10)

CUDA版本与显卡驱动版本对应表

![https://img2018.cnblogs.com/blog/762236/201903/762236-20190329185056065-1634030853.png](https://img2018.cnblogs.com/blog/762236/201903/762236-20190329185056065-1634030853.png)