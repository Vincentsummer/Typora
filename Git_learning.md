**初始化一个Git仓库：**

​	使用git init命令。

**添加文件到Git仓库，分两步：**

1. 使用命令`git add <file>`，注意，可反复多次使用，添加多个文件；
2. 使用命令`git commit -m <message>`，完成。

**关联远程库：**

​	`git remote add origin git@server-name:path/repo-name.git`；

​	关联后，使用命令`git push -u origin master`第一次推送master分支的所有内容；

​	此后，每次本地提交后，只要有必要，就可以使用命令`git push origin master`推送最新修改；

**克隆远程库：**

​	`git clone git@server-name:path/repo-name.git`；

**git本地仓库分三个区来区别操作：**
	**工作区：**
		就是一个多了.git文件的文件夹，不要想太多，就按Linux操作文件夹的方法正常操作

​		工作区撤销：git checkout -- filename

​	**暂存区：**
​		暂时讲了两种操作暂存区的方法：
​			1.修改暂存区
​				git add filename
​				git rm filename
​			2.递交暂存区
​				git commit -m "log"

​			3.撤销暂存区

​				git reset HEAD filename

​      **版本库：**

​		git commit以后的最终版本存入地方，git最重要的一个地方，因为只有版本库的修改才可以跟踪

​	 git status 查看当前git仓库与上一次commit之后的版本库的一切修改，包括工作区的修改和暂存区的修改。

​	

**查看历史提交记录：**`git log`  （可选 `--pretty=oneline`）

- `HEAD`指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令`git reset --hard commit_id`。
- 穿梭前，用`git log`可以查看提交历史，以便确定要回退到哪个版本。
- 要重返未来，用`git reflog`查看命令历史，以便确定要回到未来的哪个版本。

**管理修改：**

​	`git diff HEAD -- readme.txt`命令可以查看工作区和版本库里面最新版本的区别

​	修改 -> `git add` -> 修改 -> `git add` -> `git commit`

**撤销修改：**

​	场景1：当改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令`git checkout -- file`。

​	场景2：当不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令`git reset HEAD <file>`，就回到了场景1，第二步按场景1操作。

​	场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，则版本回退，不过前提是没有推送到远程库。

**windows下生成ssh key:**

1. 键入命令：ssh-keygen -t rsa -C "email@email.com","email@email.com"是目的github账号。
2. 在C:\Users\admin\.ssh这个路径下会生成两个文件：id_rsa和id_rsa.pub，打开id_rsa.pub文件，复制内容，在github.com的网站上到ssh密钥管理页面，添加新公钥，取个名字，内容粘贴刚才复制的内容。



