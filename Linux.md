# linux操作系统学习

## 源码结构

linux系统下，内核源代码一般在/usr/src/linux目录下。

- arch 包含了此核心源代码所支持的硬件体系结构相关的核心代码。如对于X86平台就是i386。
- include 包括了核心的大多数include文件。另外对于每种支持的体系结构分别有一个子目录。
- init 包含核心启动代码。
- mm 包含了所有的内存管理代码。与具体硬件体系结构相关的内存管理代码位于arch/*/mm目录下，如对应于X86的就是arch/i386/mm/fault.c 。
- drivers 系统中所有的设备驱动都位于此目录中。它又进一步划分成几类设备驱动，每一种也有对应的子目录，如声卡的驱动对应于drivers/sound。
- ipc 包含了核心的进程间通讯代码。
- modules 包含已建好可动态加载的模块。
- fs Linux支持的文件系统代码。不同的文件系统有不同的子目录对应，如ext2文件系统对应的就是ext2子目录。
- kernel 主要核心代码。同时与处理器结构相关代码都放在arch/*/kernel目录下。
- net 核心的网络部分代码。里面的每个子目录对应于网络的一个方面。
- lib 包含了核心的库代码。与处理器结构相关库代码被放在arch/*/lib/目录下。
- scripts 包含用于配置核心的脚本文件。
- Documentation 参考文档。

## 文件IO

文件I/O通常为不带缓冲的I/O。大多数文件I/O只需用到5个函数：*open、read、write、lseek*以及*close*。

### 文件描述符

对于内核而言，所有打开的文件都通过文件描述符引用。文件描述符是一个非负整数。当打开或创建一个现有文件或创建一个新文件时，内核向进程返回一个文件描述符。

### 函数open和openat

调用*open*或*openat*函数可以打开或创建一个文件。

```c
#include <fcntl.h>

int open(const char *path, int oflag, ... /*mode_t mode */);
int openat(int fd, const char *path, int oflag, ... /* mode_t mode */);
//return : 文件描述符 or -1
```

### 函数create

调用*creat*函数创建一个新文件*。

```c
#include <fnctl.h>

int creat(const char *path, mode_t mode);
//return: 只写打开的文件描述符 or -1
```

此函数等效于*open(path, O_WRITELY | O_CREAT | O_TRUNC, mode)*;

### 函数close

可调用*close*函数关闭一个打开的文件。

```c
#include <unistd.h>

int close(int fd);
//return : 0 or -1
```

### 函数lseek

每个打开的文件都有一个与其相关联的“当前文件偏移量”。它通常是一个非负整数，用以度量从文件开始处计算的字节数。通常，读写都从当前的文件偏移量处开始，并使偏移量增加所读写的字节数。

可调用*lseek*函数显式地为一个打开文件设置偏移量。

```c
#include <unistd.h>

off_t lseek(int fd, off_t offset, int whence);
// return : 新的文件偏移量 or -1
```

### 函数read

调用read函数从打开文件中读数据。

```c
#include <unistd.h>

ssize_t read(int fd, void *buf, size_t nbytes);
// return : 读到的字节数；若已到文件尾，返回0；出错返回-1.
```

### 函数write

调用*write*函数向打开文件写数据。

```c
#include <unistd.h>

ssize_t write(int fd, const void *buf, size_t nbytes);
// return : 已写字节数 or -1
```

### 函数pread和pwrite

*pread*函数和*pwrite*函数允许原子性地定位并执行I/O。

```c
#include <unistd.h>

ssize_t pread(int fd, void *buf, size_t nbytes, off_t offset);
// return : 读到的字节数；若已到文件尾，返回0；出错返回-1。
ssize_t pwrite(int fd, const void *buf, size_t nbytes, off_t offset);
// return : 已写的字节数 or -1
```

### 函数dup与dup2

*dup*函数与*dup2*函数用来复制一个现有的文件描述符。

```c
#include <unistd.h>

int dup(int fd);
int dup2(int fd, int fd2);
//return : 新的文件描述符 or -1
```

### 函数sync、fsync和fdatasync

上述三个函数将缓冲区中的数据同步到磁盘中。

```c
# include <unistd.h>

int fsync(int fd);
int fdatasync(int fd);
// return : 0 or -1
int sync(void);
```

*sync*函数将所有修改过的块缓冲区排入写队列，然后返回，并不等待实际写磁盘操作结束。

*fsync*函数只对由文件描述符fd指定的一个文件起作用，并且等待写磁盘操作结束才返回。

*fdatasync*函数类似于*fsync*函数，但它只影响文件的数据部分。而*fsync*函数还会同步更新文件的属性。

### 函数fcntl

*fcntl*函数可以改变已经打开文件的属性。

```c
#include <fcntl.h>

int fcntl(int fd, int cmd, ... /* int arg */);
```

## 标准IO库

### 流和FILE对象

Linux中的标准IO库对文件的操作围绕**流**进行。当使用其打开或创建一个文件时，会使一个流与一个文件相关联。

使用以下函数可以获得一个流的描述符。

```c
#include <stdio.h>
int fileno(FILE *fp);
```

流的定向决定所读、写的字符是单字节还是多字节。一个流最初被创建时，其状态为未定向。

- 宽定向：在一个未定向流上使用一个多字节I/O函数，则将该流设置为宽定向。
- 字节定向：在一个未定向流上使用一个单字节I/O函数，则将该流设置为字节定向。

仅有两个函数可改变流的定向。*freopen*函数清除一个流的定向；*fwide*函数设置流的定向。*fwide*函数并不改变已定向流的定向，且无出错返回。

```c
#include <stdio.h>
#include <wchar.h>
int fwide(FILE *fd, int mode);
//return : >0 if 宽定向，<0 if 字节定向， 0 if 未定向
```

*fopen*函数用于打开一个流，并返回一个指向FILE对象的指针。该对象包含了标准IO库为管理该流需要的所有信息。包括文件描述符、指向用于该流的缓冲区的指针、缓冲区长度、当前在缓冲区中的字符以及出错标志等。

### 标准输入、标准输出和标准错误

对一个进程预定义3个流，其引用的文件与文件描述符STDIN_FILENO、SYDOUT_FILENO和STDERR_FILENO所引用的相同。并通过预定义文件指针stdin、stdout和stderr加以引用。

### 缓冲

提供缓冲的目的在于尽可能减少使用read和write的次数。同时它也为每个I/O流自动地进行缓冲管理。标准I/O库提供了3种类型的缓冲：

1. 全缓冲。在填满标准I/O缓冲区后才进行实际I/O操作。对磁盘上的文件通常采用全缓冲。在一个流上执行第一次操作时，相关标准I/O函数通常调用malloc获得需使用的缓冲区。缓冲区可自动冲洗（当填满一个缓冲区时），或者调用函数fflush冲洗一个流。flush（冲洗）将缓冲区中的内容写到磁盘上。
2. 行缓冲。当在输入和输出中遇到换行符时，标准I/O库执行I/O操作。当流涉及一个终端时，通常使用行缓冲。
3. 不带缓冲。不对字符进行缓冲存储。stderr通常为不带缓冲流。

可使用*setbuf*和*setvbuf*更改缓冲类型。

```c
#include <stdio.h>
void setbuf(FILE *restrict fp, char *restrict buf);
int setvbuf(FILE *restrict fp, char *restrict buf, int mode, size_t size);
// return: 0 if success, !0 if fail.
int fflush(FILE *fp);
//return: 0 if success, EOF if fail.
```

### 打开流

下列3个函数打开一个标准I/O流。

```c
#include <stdio.h>

FILE *fopen(const char *restrict pathname, const char *restrict type);
FILE *freopen(const char *restrict pathname, const char *restrict type, FILE *restrict fp);
FILE *fdopen(int fd, const char *type);
// return FILE* or NULL
```

- *fopen*函数打开一个路径名为pathname的一个指定文件。
- *freopen*函数在一个指定流上打开一个指定的文件，若流已经打开，则先关闭流。若该流已定向，则清除该定向。此函数一般用于将一个指定的文件打开为一个预定义的流：stdin、stdout或stderr。
- *fdopen*函数取一个已有的文件描述符，并使一个标准的I/O流与该描述符相结合。此函数常用于由创建管道或网络通信通道函数返回的描述符。

使用*fclose*函数关闭一个打开的流。

```c
#include <stdio.h>

int fclose(FILE *fp);
// return: 0 if success, EOF if fail.
```

在该文件被关闭之前，冲洗缓冲中的输出数据。缓冲区中的任何输入数据被丢弃。

当一个进程正常终止时（直接调用*exit*函数，或从*main*函数返回），则所有带未写缓冲数据的标准I/O流都被冲洗，所有打开的标准I/O流都被关闭。

### 读和写流

一个打开的流，可在3中不同类型的非格式化I/O中进行选择，对其进行读写操作。

1. 每次一个字符的I/O。一次读或写一个字符，如果流是带缓冲的，则标准I/O函数处理所有缓冲。
2. 每次一行的I/O。使用*fgets*和*fputs*。每行都以一个换行符终止。
3. 直接I/O。*fread*和*fwrite*函数支持这种类型的I/O。每次I/O操作读或写某种数量的对象，而每个对象具有指定的长度。常用于从二进制文件中每次读或写一个结构。

#### 输入函数

```c
#include <stdio.h>
int getc(FILE *fp);
int fgetc(FILE *fp);
int getchar(void);
// return: next char or EOF
```

*getchar*等同于*getc(stdin)*。*getc*可被实现为宏，*fgetc*不能实现为宏。

无论是出错还是达到文件尾，其返回相同的值。需使用以下两个函数进行区分。

```c
#include <stdio.h>

int ferror(FILE *fp);
int feof(FILE *fp);
// return: !0 or 0

void clearerr(FILE *fp);  // 清除标志（出错及文件结束标志）
```

从流中读取数据后，可调用*ungetc*将字符再压送回流中。

```c
#include <stdio.h>

int ungetc(int c, FILE *fp);
// return : c or EOF
```

#### 输出函数

对应上述3个输入函数有相同的输出函数。

```c
#include <stdio.h>

int putc(int c, FILE *fp);
int fputc(int c, FILE *fp);
int putchar(int c);
//return: c or EOF
```

### 每次一行I/O

以下两个函数提供每次输入一行的功能。

```c
#include <stdio.h>

char *fgets(char *restrict buf, int n, FILE *restrict fp);
char *gets(char *buf);
//return: buf or NULL
int fputs(const char *restrict str, FILE *restrict fp);
int puts(const char *str);
//return: >=0 or EOF
```

*gets*从标准输入读，不将换行符存入缓冲区；*fgets*则从指定的流读，并且将换行符存入缓冲区。*gets*不推荐使用，其没有指定缓冲区长度，可能造成缓冲区溢出。输出函数同理。

### 二进制I/O

使用下列两个函数执行二进制I/O操作。

```c
#include <stdio.h>

size_t fread(void *restrict ptr, size_t size, size_t nobj, FILE *restrict fp);
size_t fwrite(const void *restrict ptr, size_t size, size_t nobj, FILE *restrict fp);

// return : the number of object read/write 
```

### 定位流

有3种方法定位标准I/O流。

```c
#include <stdio.h>

long ftell(FILE *fp); 
// return: file current position if success, -1L if fail.
int fseek(FILE *fp, long offset, int whence);
// return : 0 if success, -1 if fail.

off_t ftello(FILE *fp);
// return : file current position if success, (off_t)-1 if fail.
int fseeko(FILE *fp, off_t offset, int whence);
// return : 0 if success, -1 if fail.

int fgetpos(FILE *restrict fp, fpos_t *restrict pos);
int fsetops(FILE *fp, const fpos_t *pos);
// return : 0 if success, -1 if fail.
void rewind(FILE *fp);
```

### 格式化I/O

#### 格式化输入

格式化输出由5个函数来处理。

```c
#include <stdio.h>
int printf(const char *restrict format, ...);
int fprintf(FILE *restrict fp, const char *restrict format, ...);
int dprintf(int fd, const char *restrict format, ...);
//return : 成功：输出字节数， 出错返回负值。
int sprintf(char *restrict buf, const char *restrict format, ...);
//return : 成功：存入数组的字符数；出错返回负值。
int snprintf(char *restrict buf, size_t n, const char *restrict format, ...);
//return : 或缓冲区足够大，返回将要存入数组的字符数；出错则返回负值。

```

下面5中printf族的变体类似于上面5种，但是将可变参数表（...）替换成了arg。

```c
#include <stdarg.h>
#include <stdio.h>
int vprintf(const char *restrict format, va_list arg);
int vfprintf(FILE *restrict fp, const char *restrict format, va_list arg);
int vdprintf(int fd, const char *restrict format, va_list arg);
//return : 成功：输出字节数， 出错返回负值。
int vsprintf(char *restrict buf, const char *restrict format, va_list arg);
//return : 成功：存入数组的字符数；出错返回负值。
int vsnprintf(char *restrict buf, size_t n, const char *restrict format, va_list arg);
//return : 或缓冲区足够大，返回将要存入数组的字符数；出错则返回负值。
```

#### 格式化输出

格式化输出由3个函数处理。

```c
#include <stdio.h>

int scanf(const char *restrict format, ...);
int fscanf(FILE *fp, const char *restrict format, ...);
int sscanf(const char *restrict buf, const char *restrict format, ...);
//return: 赋值的输入项数；若出错或在任一转换前已达到文件尾端，返回EOF。
```

同理还有一下变体。

```c
#include <stdarg.h>
#include <stdio.h>

int scanf(const char *restrict format, va_list arg);
int fscanf(FILE *fp, const char *restrict format, va_list arg);
int sscanf(const char *restrict buf, const char *restrict format, va_list arg);
//return: 赋值的输入项数；若出错或在任一转换前已达到文件尾端，返回EOF。
```



## Linux进程间通信

linux下的进程通信手段基本上是从Unix平台上的进程通信手段继承而来的。如下图所示：

<img src="/home/vincent/Typora/Linux.assets/Linux1.png" style="zoom:67%;" />

linux下进程间通信的几种主要手段简介：

- 管道（Pipe）及有名管道（named pipe）：管道可用于具有亲缘关系进程间的通信，有名管道克服了管道没有名字的限制，因此，除具有管道所具有的功能外，它还允许无亲缘关系进程间的通信；
- 信号（Signal）：信号是比较复杂的通信方式，用于通知接受进程有某种事件发生，除了用于进程间通信外，进程还可以发送信号给进程本身；linux除了支持Unix早期信号语义函数sigal外，还支持语义符合Posix.1标准的信号函数sigaction（实际上，该函数是基于BSD的，BSD为了实现可靠信号机制，又能够统一对外接口，用sigaction函数重新实现了signal函数）；
- 报文（Message）队列（消息队列）：消息队列是消息的链接表，包括Posix消息队列system V消息队列。有足够权限的进程可以向队列中添加消息，被赋予读权限的进程则可以读走队列中的消息。消息队列克服了信号承载信息量少，管道只能承载无格式字节流以及缓冲区大小受限等缺点。
- 共享内存：使得多个进程可以访问同一块内存空间，是最快的可用IPC形式。是针对其他通信机制运行效率较低而设计的。往往与其它通信机制，如信号量结合使用，来达到进程间的同步及互斥。
- 信号量（semaphore）：主要作为进程间以及同一进程不同线程之间的同步手段。
- 套接口（Socket）：更为一般的进程间通信机制，可用于不同机器之间的进程间通信。起初是由Unix系统的BSD分支开发出来的，但现在一般可以移植到其它类Unix系统上：Linux和System V的变种都支持套接字。

一般来说，linux下的进程包含以下几个关键要素：

- 有一段可执行程序；

- 有专用的系统堆栈空间；

- 内核中有它的控制块（进程控制块），描述进程所占用的资源，这样，进程才能接受内核的调度；

- 具有独立的存储空间。

进程和线程有时候并不完全区分，而往往根据上下文理解其含义。

## 问题

### read系统调用与标准I/O库fread