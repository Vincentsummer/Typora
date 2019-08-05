# 计算机视觉笔记

## 图像基本知识

### 对比度

- 一副图像中，各种不同颜色最亮处和最暗处之间的差别，差别越大对比度越高，这个跟分辨率没有多少关系，只跟最暗和最亮有关系，对比度越高一个图像给人的感觉就越刺眼，更加鲜亮，突出；越低则给人感觉变化不明显，反差就越小。即指一幅图像灰度反差的大小。

### 亮度

- 一副图像给人的一种直观感受，如果是灰度图像，则跟灰度值有关，灰度值越高则图像越亮。

### 饱和度

- 彩色图像的概念，饱和度为0的话，图像表现为灰度图像；饱和度越高颜色表现出种类越多，颜色表现更丰富，反之亦然。饱和度是指色彩的鲜艳程度，也称色彩的纯度。饱和度取决于该色中含色成分和消色成分（灰色）的比例。含色成分越大，饱和度越大；消色成分越大，饱和度越小。

### 图像的颜色空间

#### RGB

- RGB(red,green,blue)颜色空间最常用的用途就是显示器系统（计算机、电视机等都是采用RGB颜色空间来进行图像显示）。一般来说，电脑，电视机等是利用三个电子枪分别发射R分量，G分量，B分量的电子束，以此来激发屏幕上的RGB三种颜色的荧光粉，从而发出不同颜色、不同亮度的像素、进而组成了一幅图像；很明显，RGB颜色空间利用了物理学中的三原色叠加从而组成产生各种不同颜色的原理。在RGB颜色空间中，R、G、B三个分量的属性是独立的。也即是说，RGB颜色可以表示为（Red, Green, Blue)。其中，各个分量的数值越小，亮度越低。数值越大，亮度越高；如：（0,0,0）表示黑色，（255，255，255）表示白色；

![img](https://img-blog.csdn.net/20180520172027777)

#### HSV

- HSV(hue,saturation,value)表示色相、饱和度和亮度。该颜色空间可以用一个圆锥来表示。

![img](https://img-blog.csdn.net/20180520172109456)

##### 色调H

​		用角度度量，取值范围为0°～360°，从红色开始按逆时针方向计算，红色为0°，绿色为120°,蓝色为240°。它们的补色是：黄色为60°，青色为180°,品红为300°；

##### 饱和度S

​		饱和度S表示颜色接近光谱色的程度。一种颜色，可以看成是某种光谱色与白色混合的结果。其中光谱色所占的比例愈大，颜色接近光谱色的程度就愈高，颜色的饱和度也就愈高。饱和度高，颜色则深而艳。光谱色的白光成分为0，饱和度达到最高。通常取值范围为0%～100%，值越大，颜色越饱和。

##### 明度V

​		明度表示颜色明亮的程度，对于光源色，明度值与发光体的光亮度有关；对于物体色，此值和物体的透射比或反射比有关。通常取值范围为0%（黑）到100%（白）。

#### RGB转换HSV算法

#### RGB转HSV

1.  $V = max(R, G, B) / 255.0f$，即**亮度V就是RGB值中最大的那个值进行归一化**。

   推论：**纯色的RGB中肯定有一个是255。**同时RGB值也不可能有3个255，因为3个255为白色，白色为对于任何色彩H，V=1而S=0时的产物。而V=1，S=0并不是纯色。同时如果V=0，那么RGB三者中的最大值是0，即RGB都为0，也就是说该像素是黑色。

2. $S = (max(R, G, B) - min(R, G, B))/(float)max(R, G, B)$，即**饱和度S是RGB中最大值和最小值的差值与最大值的比值。**

   推论：

   - **纯色(S=1，V=1)的RGB值中必定有一个0。**
   - 当S=1时，$max(R, G, B) - min(R, G, B) = max(R, G, B)$，即 $RGB_{min}=0$ 。这也说明了白色(RGB(255,255,255)并不是纯色)。
   - 当S=0时，$RGB_{max}-RGB_{min}=0$，即$R=G=B$。此时颜色呈不同程度的灰色（由白到黑，亮度由V决定，因为$V=RGB_{max}*100/255$，V越高，$RGB_{max}=R=G=B$ 就越高，灰色越亮）。
   - **因此对于纯色来说，RGB中必有一个255和一个0。**

   公式换算：
   $$
   H =
   \begin{cases} 
   0^\circ ,\qquad\qquad\qquad\qquad\qquad\ \ if\quad max==min  \\
   60^\circ \times \frac{G-B}{max-min}+0^\circ, \qquad \quad if\quad max==R\quad and\quad G \ge B \\
   60^\circ \times \frac{G-B}{max-min}+360^\circ, \quad \quad if\quad max==R\quad and \quad G < B \\
   60^\circ \times \frac{B-R}{max-min}+120^\circ,\quad \quad if\quad max==G \\ 
   60^\circ \times \frac{B-R}{max-min}+240^\circ,\quad \quad if\quad max==B
   \end{cases} \\
   \ \\
   S = (max-min)/max/255 \qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad\ \\
   \ \\
   V = max/255 \qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad\qquad
   $$

#### HSV转RGB

$$
h_i = [\frac{h}{60}](mod6) \\
f = \frac{h}{60}-h_i \qquad \\
p = v \times (1-S) \quad \\
q = v \times (1-f \times S) \\
t = v \times (1-(1-f) \times S)
\ \\
\ \\
(R,G,B) =
\begin{cases}
(v,t,p), \qquad if \quad h_i == 0 \\
(q,v,p), \qquad if \quad h_i == 1 \\
(p,v,t), \qquad if \quad h_i == 2 \\
(p,q,v), \qquad if \quad h_i == 3 \\
(t,p,v), \qquad if \quad h_i == 4 \\
(v,p,q), \qquad if \quad h_i == 5 \\
\end{cases}
$$



### 图像信噪比

#### 均方差(MSE)

- 设有两个 m×n 的单色图像 $I$ 和 $K$，如果一个为另一个的噪声相似，那么它们的均方差定义为：

$$
MSE = \frac{1}{mn}\sum_{i=0}^{m-1} \sum_{i=0}^{n-1}
\begin{Vmatrix}
I(i,j)-K(i,j)
\end{Vmatrix}^2
$$



#### 信噪比(SNR)

$$
SNR(dB) =
10 \cdot \log_{10}
\begin{bmatrix}
\frac{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(f(x,y))^2}{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(f(x,y)-\hat{f}(x,y))^2}
\end{bmatrix}
$$

#### 峰值信噪比(PSNR)

$$
PSNR(dB) = 10 \cdot \log_{10}(\frac{MAX_I^2}{MSE})=20 \cdot \log_{10}(\frac{MAX_I}{\sqrt{MES}})
$$

或
$$
\begin{align}
PSNR(dB) & =
10 \cdot \log_{10}
\begin{bmatrix}
\frac{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(255)^2}{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(f(x,y)-\hat{f}(x,y))^2}
\end{bmatrix} \\
& = 10 \cdot \log_{10}
\begin{bmatrix}
\frac{255^2}{MSE}
\end{bmatrix}
\end{align}
$$

#### PSNR与SNR的关系

$$
PSNR(dB)=SNR(dB)+10 \cdot \log_{10}
\begin{bmatrix}
\frac{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(255)^2}{\sum_{x=1}^{N_x}\sum_{y=1}^{N_y}(f(x,y))^2}
\end{bmatrix}
$$



### Gamma校正

- gamma校正就是对图像的伽玛曲线进行编辑，以对图像进行非线性色调编辑的方法，检出图像信号中的深色部分和浅色部分，并使两者比例增大，从而提高图像的对比度。

- gamma大于1时，直方图将向左移动，输出图像将比输入图像更暗；伽玛小于1时，直方图将向右移动，输出图像将比输入图像亮。

![img](https://img-blog.csdn.net/20171123181501248)

- 假设图像中有一个像素，值是 200 ，那么对这个像素进行校正必须执行如下步骤： 

  　　1. 归一化 ：将像素值转换为 0 ～ 1 之间的实数。 算法如下 : $( i + 0. 5)/256$ 这里包含 1 个除法和 1 个加法操作。对于像素 A 而言  , 其对应的归一化值为  0. 783203 。 
        　　2. 预补偿 ：求出像素归一化后的数据以 $1 /gamma $ 为指数的对应值。这一步包含一个求指数运算。若 $gamma$  值为 2. 2 ,  则 $1 /gamma$ 为 0. 454545 , 对归一化后的 A 值进行预补偿的结果就是 0. 783203^0. 454545 = 0. 894872 。 
　　3. 反归一化 ：将经过预补偿的实数值反变换为 0 ～ 255 之间的整数值。具体算法为：$ f*256 - 0. 5$ 此步骤包含一个乘法和一个减法运算。将 A 的预补偿结果 0. 894872  代入上式  , 得到 A 预补偿后对应的像素值为 228 , 这个 228 就是最终结果。

## 图像的频域操作



## 图像分割

### 边缘检测

- 边缘检测原理：边缘是图像上灰度级变化很快的点的集合。使用微分计算这些变化率很快的点。

- 差分：因为图像是离散的，通常使用差分求变化率。

  差分的定义：$f^\prime(x) = f(x+1)-f(x)$。按先后排列有，$f^\prime(x)=-f(x)+f(x+1)$。提出系数[-1,1]作为滤波模板，跟原图做卷积即可检测边缘。

  模板通常是奇数。（偶数模板的卷积结果位于两像素位置中间，不方便表示。）

  

![https://upload-images.jianshu.io/upload_images/2764502-93a51ed054646e22.png](https://upload-images.jianshu.io/upload_images/2764502-93a51ed054646e22.png)

#### Prewitt边缘检测算子

- 使用 $f^ \prime(x)=-f(x-1)+f(x+1)$近似计算一阶差分。即模板为

$$
\begin{bmatrix}
-1, \ 0 , \ 1 \ \\
-1, \ 0 , \ 1 \ \\
-1, \ 0 , \ 1 \ \\
\end{bmatrix}
$$



#### Sobel边缘检测算子

- sobel算子运算图像亮度函数的梯度之近似值。在图像的任何一点使用此算子，将会产生对应的梯度矢量或是其法矢量。与Prewitt算子相比，Sobel算子对于象素的位置的影响做了加权，可以降低边缘模糊程度，因此效果更好。

偏x方向模板：$G_x$
$$
\begin{bmatrix}
-1, \ 0 , \ 1 \ \\
-2, \ 0 , \ 2 \ \\
-1, \ 0 , \ 1 \ \\
\end{bmatrix}
$$
偏y方向模板：$G_y$
$$
\begin{bmatrix}
-1, \ -2 , \ -1 \ \\
0, \ \ \ \ \ 0 , \ \ \ \ \ 0 \ \\
1,  \ \ \ \ \ 2 , \ \ \ \ 1 \ \\
\end{bmatrix}
$$
​	图像的每一个像素的横向及纵向梯度近似值可用以下的公式结合，来计算梯度的大小:
$$
G=\sqrt{G_x^2+G_y^2}
$$
然后可用以下公式计算梯度方向:
$$
\theta=tan^{-1}(\frac{G_y}{G_x})
$$
如果以上的角度 $\theta$ 等于零，即代表图像该处拥有纵向边缘，左方较右方暗。



#### Laplace边缘检测算子

- 拉普拉斯使用二阶差分计算边缘，连续函数的情况下：
  - 在一阶微分图中极大值或极小值处，认为是边缘。
  - 在二阶微分图中极大值和极小值间的过0点，被认为是边缘。

![https://upload-images.jianshu.io/upload_images/2764502-7410dddc5d82bf8d.png](https://upload-images.jianshu.io/upload_images/2764502-7410dddc5d82bf8d.png)

- 拉普拉斯算子推导：

  一阶差分：$f^\prime(x)=f(x)-f(x-1)$

  二阶差分：$f^\prime(x) = (f(x+1)-f(x))-(f(x)-f(x-1))=f(x-1)-2f(x)+f(x+1)$

  提取系数：[1，-2，1]

  二维情况下，同理可得：

  $f(x,y)=-4f(x,y)+f(x-1,y)+f(x+1,y)+f(x,y-1)+f(x,y+1)$

  提取系数，则模板为：
  $$
  \begin{bmatrix}
  0, \ \ \  1 , \ \ \ 0 \ \\
  1, \ -4 , \ 1 \ \\
  0, \ \ \ 1 , \ \ \ 0 \ \\
  \end{bmatrix}
  $$
  考虑两个斜对角的情况：
  $$
  \begin{bmatrix}
  1, \ \ \  1 , \ \ \ 1 \ \\
  1, \ -8 , \ 1 \ \\
  1, \ \ \ 1 , \ \ \ 1 \ \\
  \end{bmatrix}
  $$

#### Canny边缘检测算子

- canny计算过程：

  1. 高斯滤波器平滑图像。（去噪）

  2. 一阶差分偏导计算梯度值和方向。（OpenCV中使用sobel算子计算）

  3. 对梯度值不是极大值的地方进行抑制。（不是极值的点全部置0，去掉了大部分弱的边缘，所以图像边缘会变细。）

  4. 用双阈值连接图上的联通点。

     - 双阈值t1，t2。$t1 \le t2$

     - 大于 t2 的点肯定是边缘。

     - 小于 t1 的点肯定不是边缘。

     - 在二者之间的点，通过已确定的边缘点，进行8邻域方向的搜索（广搜），图中可达的是边缘，不可达的点不是边缘。
     - 最后得到canny边缘图。

#### 各算子区别

- sobel产生的边缘有强弱，抗噪性好。
- laplace对边缘敏感，可能有些噪声的边缘也被计算。
- canny产生的边缘很细，可能就一个像素那么细，没有强弱之分。

#### 一阶差分与二阶差分的差别

1. 一阶差分通常会产生较粗的边缘。 
2. 二阶差分对精细细节，如细线、孤立点和噪声有较强的响应。
3. 二阶差分在灰度斜坡和灰度台阶过渡处会产生双边沿响应。
4. 二阶差分的符号可以确定边缘的过渡是从亮到暗还是从暗到亮。
5. 二阶差分对细节更敏感。

一般的，**提取边缘之前最好先做下图像平滑处理**，因为导数对噪声比较敏感。 

![https://upload-images.jianshu.io/upload_images/2764502-8c411fce4c452a8c.png](https://upload-images.jianshu.io/upload_images/2764502-8c411fce4c452a8c.png)

​																					sobel算子

![https://upload-images.jianshu.io/upload_images/2764502-95e81bc31ba99bab.jpeg](https://upload-images.jianshu.io/upload_images/2764502-95e81bc31ba99bab.jpeg)

​																				   canny算子

![https://upload-images.jianshu.io/upload_images/2764502-f100024801c9bef6.jpeg](https://upload-images.jianshu.io/upload_images/2764502-f100024801c9bef6.jpeg)

​																					laplace算子